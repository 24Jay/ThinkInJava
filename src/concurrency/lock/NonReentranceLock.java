package concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/***
 * 自己实现的一个不可以重入的lock
 * 
 * @author jay
 *
 */
public class NonReentranceLock implements Lock
{
	private boolean isLocked = false;

	@Override
	public void lockInterruptibly() throws InterruptedException
	{

	}

	@Override
	public boolean tryLock()
	{
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		return false;
	}

	@Override
	public Condition newCondition()
	{
		return null;
	}

	/***
	 * 自己来实现这个方法,加上synchronized
	 */
	@Override
	public synchronized void lock()
	{

		try
		{
			while (isLocked)
				this.wait();
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		isLocked = true;
	}

	/***
	 * 自己来实现这个方法,加上synchronized
	 */
	@Override
	public synchronized void unlock()
	{
		isLocked = false;
		notify();
	}
}
