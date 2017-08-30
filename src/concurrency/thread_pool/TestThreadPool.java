package concurrency.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool
{
	public static void main(String[] ar)
	{
		ThreadPoolExecutor pool1 = new ThreadPoolExecutor(2, 16, 60, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());

		ThreadPoolExecutor pool2 = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(5), new MyRejectedHandler());

		ThreadPoolExecutor pool3 = new ThreadPoolExecutor(2, 16, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());

		for (int i = 0; i < 30; i++)
			pool2.execute(new Runner());

		System.out.println("ActiveCount: " + pool2.getActiveCount());
		System.out.println("MaximumPoolSize: " + pool2.getMaximumPoolSize());
		System.out.println("Core: " + pool2.getCorePoolSize());
		System.out.println("CompletedTaskCount: " + pool2.getCompletedTaskCount());
	}

}

class Runner implements Runnable
{

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run()
	{
		try
		{
			System.out.println("working thread..." + (count.incrementAndGet()));
			Thread.sleep(1000000);

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}

class MyRejectedHandler implements RejectedExecutionHandler
{

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
	{
		System.out.println("Rejected Thread NO." + count.incrementAndGet());

	}

}
