package concurrency.synchronize;

import java.util.concurrent.TimeUnit;

public class ThinkInSynchronized_2
{
	private static class Demo
	{
		private static volatile int a = 0;
		private static volatile int b = 0;

		/****
		 * 虽然printA和printB锁在了两个不同的方法中,但是由于锁的是同一个对象,那么其实同时只有一个能够运行<br>
		 * 
		 * @throws InterruptedException
		 */
		public synchronized void printA() throws InterruptedException
		{
				System.out.println("a = " + a++);

		}

		public synchronized void printB() throws InterruptedException
		{

				System.out.println("b = " + b++);
		}
	}

	public static void main(String[] ar)
	{
		Demo demo = new Demo();
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{

				try
				{
					while (true)
					{
						demo.printA();
						Thread.sleep(1000);
//						Thread.yield();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					while (true)
					{
						demo.printB();
//						Thread.sleep(1000);
						Thread.yield();
					}
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
