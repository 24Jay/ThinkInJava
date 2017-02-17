package concurrency;

import java.util.concurrent.Callable;

/***
 * 没有特别的目的,单纯想尝试使用静态内部类来写,使得对比更加清晰<br>
 * 
 * @author jay
 *
 */
public class RunnableThreadCallable
{

	static class LiftThread extends Thread
	{
		private int countdown = 10;
		private static int taskCount = 0;
		private final int ID = taskCount;

		public LiftThread()
		{
			taskCount++;
		}

		public String status()
		{
			return "#Thread#" + ID + "-" + (countdown > 0 ? (countdown) : "(LiftOff!)\n") + "  ";
		}

		@Override
		public void run()
		{
			while (countdown-- > 0)
			{
				System.out.print(status());
				// Thread.yield();
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	static class LiftRun implements Runnable
	{
		private int countdown = 10;
		private static int taskCount = 0;
		private final int ID = taskCount;

		public LiftRun()
		{
			taskCount++;
		}

		public String status()
		{
			return "#Runnable#" + ID + "-" + (countdown > 0 ? (countdown) : "(LiftOff!)\n") + "  ";
		}

		@Override
		public void run()
		{
			while (countdown-- > 0)
			{
				System.out.print(status());
				// Thread.yield();
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	public static class LiftCallable implements Callable
	{
		private int countdown = 10;
		private static int taskCount = 0;
		private final int ID = taskCount;

		public LiftCallable()
		{
			taskCount++;
		}

		public String status()
		{
			return "#Runnable#" + ID + "-" + (countdown > 0 ? (countdown) : "(LiftOff!)\n") + "  ";
		}

		@Override
		public Object call() throws Exception
		{
			while (countdown-- > 0)
			{
				System.out.print(status());
				// Thread.yield();
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			return "#Callable#" + ID + "-(LiftOff!!!!)\n";
		}

	}

}
