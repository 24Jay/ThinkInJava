package concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class SimpleDaemon implements Runnable
{

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " : " + this);
			}
			catch (Exception e)
			{
				System.out.println("Sleep Interrupted!");
			}
		}
	}

	public static void main(String[] ar) throws InterruptedException
	{
		for (int i = 0; i < 10; i++)
		{
			Thread daemon = new Thread(new SimpleDaemon());
			/**
			 * 一旦这里设置为daemon,那么几遍这些daemon还未结束<br>
			 * 只要这个main()结束了,后台的daemon也会被杀死<br>
			 * 这里如果不设置为daemon,上面的run()任务将一直运行下去<br>
			 */
			daemon.setDaemon(true);
			daemon.start();
			
		}
		System.out.println("All Daemons Started!");
		/***
		 * 这些daemon运行1750ms就被杀死了
		 */
		TimeUnit.MILLISECONDS.sleep(1750);
	}

	static class DaemonFactory implements ThreadFactory
	{

		@Override
		public Thread newThread(Runnable r)
		{
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			return thread;
		}
	}
}
