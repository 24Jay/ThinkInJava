package concurrency.lock;

public class ReadWriteLock
{
	private int readers = 0;

	private int writers = 0;

	private int writeRequest = 0;

	public synchronized void lockRead()
	{
		while (writers > 0 || writeRequest > 0)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		readers++;
	}

	public synchronized void unlockRead()
	{
		readers--;
		notifyAll();
	}

	
	/***
	 * 当没有其他线程在写或者是读的时候就可以获得写锁<br>
	 */
	public synchronized void lockWrite()
	{
		writeRequest++;
		while (writers > 0 || readers > 0)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		writeRequest--;
		writers++;
	}

	public synchronized void unlockWrite()
	{
		writeRequest--;
		notifyAll();
	}

}
