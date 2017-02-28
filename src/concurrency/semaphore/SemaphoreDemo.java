package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class SemaphoreDemo
{
	final static int size = 25;

	public static void main(String[] a) throws InterruptedException
	{
		final Pool<Fat> pool = new Pool<>(Fat.class, size);

		ExecutorService exe = Executors.newCachedThreadPool();

		for (int i = 0; i < size; i++)
		{
			exe.execute(new CheckouTask<Fat>(pool));
		}

		System.out.println("All checkedTasks created!");
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < size; i++)
		{
			Fat f = pool.checkOut();
			System.out.println(i + ": main() thread checked out");
			f.operation();
			list.add(f);
		}

		Future<?> blocked = exe.submit(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					pool.checkOut();
				}
				catch (InterruptedException e)
				{
					System.out.println("Checkout INterrupted!");
				}

			}
		});

		TimeUnit.SECONDS.sleep(2);

		 blocked.cancel(true);

		System.out.println("Checked in objects in " + list);

		for (Fat f : list)
			pool.checkIn(f);

		for (Fat f : list)
			pool.checkIn(f);

		exe.shutdown();
	}
}
