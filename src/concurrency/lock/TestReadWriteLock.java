package concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			}
		});

		Thread t3 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				lock.lockRead();// .lockWrite();
				System.out.println("read");
			}
		});

		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(t1);
		exe.execute(t3);
		exe.execute(t2);
	}
}
