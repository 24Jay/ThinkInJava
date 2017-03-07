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
		/***
		 * 这是是为了初始化ThreadLocal,使得每一个线程有自己不同的初始值<br>
		 */
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

		exec.shutdownNow();
	}
}

class Accessor implements Runnable
{
	private final int id;

	public Accessor(int id)
	{
		this.id = id;
		System.out.println("--->>>Initial value#" + id + "#" + ThreadLocalVariableHolder.get());
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.currentThread().isInterrupted())
			{
				ThreadLocalVariableHolder.increment();
				System.out.println("#" + id + "#" + ThreadLocalVariableHolder.get());
				TimeUnit.MILLISECONDS.sleep(500);
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Thread interrupted by exe.shutdownNow(), exit from here!");
		}
	}

}
