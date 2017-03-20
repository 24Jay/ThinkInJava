package concurrency.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA
{
	private static AtomicInteger at = new AtomicInteger(100);
	/***
	 * 
	 */
	private static AtomicStampedReference<Integer> sta = new AtomicStampedReference<Integer>(100,
			0);

	public static void main(String[] ar) throws InterruptedException
	{
		testAtomic();
		testStampedAtomic();
	}

	public static void testAtomic() throws InterruptedException
	{
		Thread t1 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				at.compareAndSet(100, 101);
				at.compareAndSet(101, 100);
			}
		});

		Thread t2 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					TimeUnit.SECONDS.sleep(5);

					boolean c = at.compareAndSet(100, 102);
					System.out.println("AtomicInteger = " + c);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	public static void testStampedAtomic()
	{
		Thread t1 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					TimeUnit.SECONDS.sleep(1);
				}
				catch (Exception e)
				{
				}
				sta.compareAndSet(100, 101, sta.getStamp(), sta.getStamp() + 1);
				sta.compareAndSet(101, 100, sta.getStamp(), sta.getStamp() + 1);
			}
		});

		Thread t2 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				int stamp = sta.getStamp();

				try
				{
					TimeUnit.SECONDS.sleep(5);

					boolean c3 = sta.compareAndSet(100, 102, stamp, stamp + 1);
					System.out.println("AtomicStampedRef = " + c3);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}

}
