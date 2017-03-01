package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T>
{
	private int size;

	private List<T> items = new ArrayList<T>();

	private volatile boolean[] checkOut;

	private Semaphore available;

	public Pool(Class<T> object, int size)
	{
		this.size = size;
		this.checkOut = new boolean[size];
		/**
		 * 
		 */
		this.available = new Semaphore(size, true);
		for (int i = 0; i < size; i++)
		{
			try
			{
				items.add(object.newInstance());

			}
			catch (Exception e)
			{
				throw new RuntimeException();
			}
		}

	}

	/***
	 * get a object from the pool<br>
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public T checkOut() throws InterruptedException
	{
		available.acquire();
		return getItem();
	}

	/***
	 * return a object to the pool<br>
	 * 
	 * @param x
	 */
	public void checkIn(T x)
	{
		if (releaseItem(x))
			available.release();
	}

	private synchronized T getItem()
	{
		for (int i = 0; i < size; i++)
			if (!checkOut[i])
			{
				checkOut[i] = true;
				return items.get(i);
			}
		return null;
	}

	private synchronized boolean releaseItem(T item)
	{
		int index = items.indexOf(item);
		if (index == -1)
			return false;

		if (checkOut[index])
		{
			checkOut[index] = false;
			return true;
		}
		return false;
	}
}
