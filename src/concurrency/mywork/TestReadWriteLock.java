package concurrency.mywork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestReadWriteLock
{
	public static void main(String[] ar)
	{
		ReadWriteLock lock = new ReadWriteLock();

		Thread t1 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				lock.lockRead();

				System.out.println("There are not ");
			}
		});

		Thread t2 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				lock.lockWrite();
				System.out.println("write");
				try
				{
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				lock.unlockWrite();
				System.out.println("unlock write!");
			}
		});

		Thread t3 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				while (!Thread.interrupted())
				{
					lock.lockRead();// .lockWrite();
					System.out.println("read");
				}
			}
		});

		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(t1);
		exe.execute(t3);
		exe.execute(t2);
	}
}
