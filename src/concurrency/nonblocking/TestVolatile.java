package concurrency.nonblocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

public class TestVolatile
{
	/***
	 * Java volatile variables are variables that are always read directly from
	 * main memory.
	 */
	private static volatile int a = 0;

	private static volatile int b = 0;

	private static volatile int c = 0;

	private static AtomicInteger atomic = new AtomicInteger(0);

	static class Increment implements Runnable
	{
		/****
		 * 注意区分是对class加锁,还是对instance加锁<br>
		 */
		@Override
		public void run()
		{
			// synchronized (Increment.class)

			for (int i = 0; i < 10; i++)
			{
				a = a + 1;
				atomic.incrementAndGet();
				System.out.println(Thread.currentThread().getName() + " >>>> atomic=" + atomic.get()
						+ " vs a=" + a);
				try
				{
					TimeUnit.MILLISECONDS.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	static class SyncIncb implements Runnable
	{

		/****
		 * 注意区分是对class加锁,还是对instance加锁<br>
		 */
		@Override
		public synchronized void run()
		{
			for (int i = 0; i < 10; i++)
			{
				b = b + 1;
				System.out.println(Thread.currentThread().getName() + "----- b=" + b);

				try
				{
					TimeUnit.MILLISECONDS.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	static class SyncIncc implements Runnable
	{

		/****
		 * 注意区分是对class加锁,还是对instance加锁<br>
		 */
		@Override
		public void run()
		{
			synchronized (SyncIncc.class)
			{

				for (int i = 0; i < 10; i++)
				{
					c = c + 1;
					System.out.println(Thread.currentThread().getName() + "----- c=" + c);

					try
					{
						TimeUnit.MILLISECONDS.sleep(10);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] ar) throws InterruptedException
	{

		ExecutorService exe = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++)
		{
			Increment in = new Increment();
			exe.execute(in);

			SyncIncb syn = new SyncIncb();
			exe.execute(syn);

			SyncIncc sync = new SyncIncc();
			exe.execute(sync);

		}
		TimeUnit.SECONDS.sleep(20);
		System.out.println("atomic = " + atomic);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);

		// exe.shutdownNow();
	}
}
