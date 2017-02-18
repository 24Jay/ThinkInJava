package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import jdk.internal.dynalink.beans.StaticClass;

public class TestLock
{
	private ReentrantLock lock = new ReentrantLock();

	public void untimed()
	{
		/**
		 * 尝试获取锁
		 */
		boolean captured = lock.tryLock();
		try
		{
			System.out.println("Untimed TryLock : " + captured);
		}
		finally
		{
			if (captured)
			{
				System.out.println("Untimed has get the lock! But then released.");
				lock.unlock();
			}
		}
	}

	public void timed()
	{
		boolean captured = false;
		try
		{
			captured = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("Timed TryLock : " + captured);
		}
		catch (InterruptedException e)
		{
//			e.printStackTrace();
			System.out.println("InterruptedException = " + e);
		}
		finally
		{
			if (captured)
			{
				System.out.println("Timed has get the lock! But then released.");
				lock.unlock();
			}
		}

	}

	public static void main(String[] ar)
	{
		final TestLock l = new TestLock();
		l.untimed();
		l.timed();

		/***
		 * 注意这种方式
		 */
		new Thread()
		{
			{
				setDaemon(true);
			}

			@Override
			public void run()
			{
				setDaemon(true);
				l.lock.lock();
			}

		}.start();
		Thread.yield();
		l.untimed();
		l.timed();
	}
}
