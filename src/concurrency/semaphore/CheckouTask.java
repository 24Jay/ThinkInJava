package concurrency.semaphore;

import java.util.concurrent.TimeUnit;

public class CheckouTask<T> implements Runnable
{

	private static int counter = 0;

	private final int id = counter++;

	private Pool<T> pool;

	public CheckouTask(Pool<T> p)
	{
		this.pool = p;
	}

	@Override
	public void run()
	{
		try
		{
			T item = pool.checkOut();
			System.out.println(this + "checked out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + "checked in " + item);
			pool.checkIn(item);
		}
		catch (InterruptedException e)
		{

		}
	}

	public String toString()
	{
		return "CheckoutTask" + id + " ";
	}

}
