package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder
{
	/**
	 * 又一个内部类
	 */
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>()
	{
		private Random random = new Random(47);

		protected synchronized Integer initialValue()
		{
			return random.nextInt(10000);
		}
	};

	public static void increment()
	{
		value.set(value.get() + 1);
	}

	public static int get()
	{
		return value.get();
	}

	public static void main(String[] ar) throws InterruptedException
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 9; i++)
		{
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(9);
		exec.shutdown();
	}
}

class Accessor implements Runnable
{
	private final int id;
	public Accessor(int id)
	{
		this.id = id;
	}
	@Override
	public void run()
	{

			while(!Thread.currentThread().isInterrupted())
			{
				ThreadLocalVariableHolder.increment();
				System.out.println(this);
				Thread.yield();
			}
	}
	
	
	public String toString()
	{
		return "#"+id+"#"+ThreadLocalVariableHolder.get();
	}
	
}
