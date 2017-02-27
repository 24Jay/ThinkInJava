package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GardenEntrance
{

	static class Entrance implements Runnable
	{
		private static Count count = new Count();

		private static List<Entrance> entrances = new ArrayList<Entrance>();

		private int number = 0;

		private static volatile boolean canceled = false;

		private final int id;

		private static void cancel()
		{
			canceled = true;
		}

		public Entrance(int id)
		{
			this.id = id;
			entrances.add(this);
		}

		@Override
		public void run()
		{
			while (!canceled)
			{
				/**
				 * 保证在单个线程里,此入口自己的数据是正确的<br>
				 */
				synchronized (this)
				{
					++number;
				}
				System.out.println(this + " Total: " + count.increment());

				try
				{
					TimeUnit.MILLISECONDS.sleep(100);
				}
				catch (Exception e)
				{
					System.out.println("SLeep interrupted!");
				}
			}
			System.out.println("Stop " + this);

		}

		public synchronized int getValue()
		{
			return number;
		}

		public String toString()
		{
			return "Entrance " + id + ":" + getValue();
		}

		public static int getTotalCount()
		{
			return count.value();
		}

		public static int sumEntances()
		{
			int sum = 0;
			for (Entrance e : entrances)
				sum += e.getValue();
			return sum;
		}
	}

	public static void main(String[] ar) throws InterruptedException
	{
		ExecutorService exe = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++)
			exe.execute(new Entrance(i));
		/**
		 * 让上面的5个线程运行5秒
		 */
		TimeUnit.SECONDS.sleep(5);
		Entrance.cancel();
		exe.shutdown();

		if (!exe.awaitTermination(250, TimeUnit.MILLISECONDS))
			System.out.println("Some tasks were not terminated!");
		/***
		 * 虽然任务已经终止了,但是这些对象存储在List中,因此下面的函数依然可用<br>
		 */
		System.out.println("Total = " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances = " + Entrance.sumEntances());
	}
}

class Count
{
	private int count = 0;
	private Random random = new Random(47);
	private ReentrantLock lock = new ReentrantLock();

	/**
	 * 这里应该可以不用加锁<br>
	 * 
	 * @return
	 */
	public int value()
	{
		return count;
	}

	/**
	 * 使用lock或者synchronized<br>
	 * 
	 * @return
	 */
	public int increment()
	{
		lock.lock();
		int temp = count;
		if (random.nextBoolean())
			Thread.yield();

		count = ++temp;
		lock.unlock();
		return count;
	}
}
