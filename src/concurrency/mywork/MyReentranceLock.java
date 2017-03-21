package concurrency.mywork;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyReentranceLock implements Lock
{
	private boolean isLocked = false;

	private Thread lockedBy = null;

	private int lockedCount = 0;

	@Override
	public synchronized void lock()
	{
		Thread callingThread = Thread.currentThread();
		try
		{
			while (isLocked && lockedBy != callingThread)
			{
				wait();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		isLocked = true;
		lockedCount++;
		lockedBy = callingThread;
	}

	@Override
	public synchronized void unlock()
	{
		if (Thread.currentThread() == lockedBy)
		{
			/*
			 * lockedCount--; if (lockedCount == 0) { isLocked = false; lockedBy
			 * = null; notify(); }
			 */

			/***
			 * 保证调用一次就释放锁
			 */
			lockedCount = 0;
			isLocked = false;
			lockedBy = null;
			notify();
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tryLock()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
