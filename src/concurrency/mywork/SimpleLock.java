package concurrency.mywork;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

public class SimpleLock extends AbstractQueuedSynchronizer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleLock()
	{

	}

	@Override
	protected boolean tryAcquire(int arg)
	{
		if (compareAndSetState(0, 1))
		{
			setExclusiveOwnerThread(Thread.currentThread());
			return true;
		}
		return false;
	}

	@Override
	protected boolean tryRelease(int arg)
	{
		setExclusiveOwnerThread(null);
		setState(0);
		return true;
	}

	public void lock()
	{
		acquire(1);
	}

	public boolean tryLock()
	{
		return tryAcquire(1);
	}

	public void unlock()
	{
		release(1);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}

	/***
	 * 发现等待的线程是按照阻塞时的顺序依次获取到锁的<br>
	 * 这是因为AQS是基于一个叫CLH lock queue的一个变种来实现线程阻塞队列的
	 * 
	 * @param ar
	 */
	public static void main(String[] ar)
	{
		SimpleLock lock = new SimpleLock();
		lock.lock();
		System.out.println("Main thread locked!");

		for (int i = 0; i < 10; i++)
		{
			new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					lock.lock();
					System.out.println(Thread.currentThread().getName() + ">>>> acquire the lock!");
					lock.unlock();
				}
			}).start();

			/****
			 * 让线程按照for循环的顺序阻塞在lock上
			 */
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		lock.unlock();
		System.out.println("Main thread unlocked!");
	}

}
