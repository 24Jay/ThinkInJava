package concurrency.evenchecker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedGenerator extends IntGenerator
{
	private int currentValue = 0;
	/**
	 * 加锁保证不会出现不希望的线程访问
	 */
	private Lock lock = new ReentrantLock();

	@Override
	public int next()
	{
		lock.lock();
		try
		{
			currentValue++;
			Thread.currentThread().yield();
			currentValue++;
			return currentValue;
		}
		finally
		{
			lock.unlock();
		}
	}

	public static void main(String[] ar)
	{
		LockedGenerator generator = new LockedGenerator();
		/**
		 * 这里线程设置很多的时候很快就结束了
		 */
		EvenChecker.test(generator, 100);
	}
}
