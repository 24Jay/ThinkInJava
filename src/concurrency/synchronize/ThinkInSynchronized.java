package concurrency.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThinkInSynchronized
{

	static class Demo
	{
		private static volatile int a = 0;
		private static volatile int c = 0;
		private static volatile int b = 0;

		private static synchronized void lockClass()
		{
			System.out.println("a = " + a++);
			try
			{
				TimeUnit.MICROSECONDS.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		private synchronized void lockInstance()
		{
			System.out.println("b = " + b++);
			try
			{
				TimeUnit.MICROSECONDS.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		private void lockBlock()
		{
			synchronized(this) 
			{
				
			}
		}
	}

	public static void main(String[] ar) throws InterruptedException
	{
		ExecutorService exe = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++)
		{
			Thread thread = new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					Demo demo = new Demo();
					System.out.println(demo);
					demo.lockInstance();
					Demo.lockClass();
				}
			});
			exe.execute(thread);
		}
		TimeUnit.SECONDS.sleep(100);
		exe.shutdown();
		System.out.println("Demo.a=" + Demo.a + ", Demo.b=" + Demo.b);
	}
}
