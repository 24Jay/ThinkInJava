package concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import concurrency.mywork.MyReentranceLock;

/***
 * If a thread calls outer() there is no problem calling inner() from inside
 * outer(), since both methods (or blocks) are synchronized on the same monitor
 * object ("this"). <br>
 * If a thread already holds the lock on a monitor object, it has access to all
 * blocks synchronized on the same monitor object. This is called
 * reentrance.<br>
 * 
 * @author jay
 *
 */
public class ThinkInReentrance
{

	/***
	 * 这里是可重入的<br>
	 * 
	 * @author jay
	 *
	 */
	static class Reentrance_1
	{
		public void outer()
		{
			System.out.println("synchronized>>>THis is outer method!");
			inner();
		}

		public synchronized void inner()
		{
			System.out.println("synchronized is reentrant...");
		}
	}

	static class Reentrance_2
	{
		private Lock lock;

		public Reentrance_2(Lock lo)
		{
			this.lock = lo;
		}

		public void outer() throws InterruptedException
		{
			lock.lock();
			System.out.println("\nLock = " + lock.getClass().getSimpleName() + "---outer method");
			/***
			 * 如果不是可重入锁,这里会发生死锁
			 */
			inner();
			lock.unlock();
		}

		public boolean inner() throws InterruptedException
		{
			lock.lock();
			System.out.println(lock.getClass().getSimpleName() + " is reentrance lock!!!");
			lock.unlock();
			return true;
		}
	}

	public static void main(String[] ar) throws InterruptedException
	{
		Reentrance_1 l1 = new Reentrance_1();
		l1.outer();

		Reentrance_2 l3 = new Reentrance_2(new ReentrantLock());
		l3.outer();

		Reentrance_2 l4 = new Reentrance_2(new MyReentranceLock());
		l4.outer();

		Reentrance_2 l2 = new Reentrance_2(new NonReentranceLock());
		l2.outer();

	}
}
