package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo
{
	final static int size = 5;

	public static void main(String[] a) throws InterruptedException
	{
		/**
		 * 对象池中装载的对象为Fat, 数量为size
		 */
		final Pool<Fat> pool = new Pool<>(Fat.class, size);

		ExecutorService exe = Executors.newCachedThreadPool();

		/*
		 * for (int i = 0; i < size; i++) { exe.execute(new
		 * CheckouTask<Fat>(pool)); } System.out.
		 * println("***************************All checkedTasks created!");
		 */

		List<Fat> list = new ArrayList<Fat>();

		/***
		 * 这里如果不能再checkout了,线程就一直会被阻塞<br>
		 */
		for (int i = 0; i < size; i++)
		{
			Fat f = pool.checkOut();
			list.add(f);
			System.out.println(">>> main() thread checked out" + f + ", list=" + list);
			System.out.println();
		}

		/**
		 * 这里因为不能再checkout,因此这个线程会被阻塞,直到后面的cancel调用<br>
		 */
		Future<?> blocked = exe.submit(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					System.out.println("Unable to checkout, so this thread will be blocked....");
					pool.checkOut();
				}
				catch (InterruptedException e)
				{
					System.out.println("Blocked thread INterrupted!");
				}

			}
		});

		System.out.println("Sleep for 5 seconds to cancel blocked....");
		TimeUnit.SECONDS.sleep(5);
		blocked.cancel(true);

		for (Fat f : list)
			pool.checkIn(f);
		System.out.println("Check out another one: " + pool.checkOut());
		exe.shutdown();
	}
}
