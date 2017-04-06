package concurrency.synchronize;

import java.util.concurrent.locks.ReentrantLock;


public class CannotRelease
{

	static class OB
	{
		private static int count = 0;

		private ReentrantLock lock = new ReentrantLock();

		public void incre()
		{
			try
			{
				System.out.println("Execute incre()..........");
				lock.lock();
				System.out.println("Have get the lock!!!!");
			}
			finally
			{
				lock.unlock();
			}

		}

		public void cannotReleaseWhenException()
		{
			lock.lock();
			int[] arr = new int[2];
			System.out.println(arr[2]);
			/***
			 * 這裏必須要ｆｉｎａｌｌｙ裏面釋放鎖，否則發生異常，鎖便不能釋放
			 */
			// finally
			// {

			System.out.println("unlllock");
			lock.unlock();
			// }
		}

		public synchronized void willReseaseWhenException()
		{

			int[] arr = new int[2];
			System.out.println(arr[2]);

			System.out.println(" willReseaseWhenException() endddddding ");
		}
	}

	public static void main(String[] ar)
	{
		OB on = new OB();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				/*
				 * &*&** 先执行，并且获取了锁之后异常退出，没有释放锁<br>
				 */
//				on.cannotReleaseWhenException();
				on.willReseaseWhenException();
			}
		}).start();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				/***
				 * 后执行incre(),但是一直无法获取锁
				 */
				on.incre();
			}
		}).start();

	}
}
