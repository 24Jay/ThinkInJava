package concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CachedThreadPool, FixedThreadPool,SingleThreadExecutor<br>
 * 
 *
 */
public class TestExecutor
{

	@SuppressWarnings("unchecked")
	public static void main(String[] ar)
	{
		/***
		 * 工厂模式?
		 */
		ExecutorService cached = Executors.newCachedThreadPool();
		ExecutorService fixed = Executors.newFixedThreadPool(5);
		ExecutorService single = Executors.newSingleThreadScheduledExecutor();

		ArrayList<Future<String>> result = new ArrayList<Future<String>>();

		for (int i = 0; i < 4; i++)
		{
			/**
			 * 命令模式
			 */
			cached.execute(new RunnableThreadCallable.LiftThread());
			fixed.execute(new RunnableThreadCallable.LiftRun());
			single.execute(new RunnableThreadCallable.LiftRun());
			// Callable
			result.add(fixed.submit(new RunnableThreadCallable.LiftCallable()));
		}

		for (Future<String> f : result)
		{
			try
			{
				String s = f.get();
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$CallableResult = " + s);
			}
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
			finally
			{

			}
		}

		cached.shutdown();
		fixed.shutdown();
		single.shutdown();
		System.out.println("************shutdown**********************");
	}
}
