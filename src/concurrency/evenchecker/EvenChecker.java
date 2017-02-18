package concurrency.evenchecker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable
{
	private IntGenerator generator;
	private final int id;

	public EvenChecker(IntGenerator g, int ident)
	{
		generator = g;
		id = ident;
	}

	@Override
	public void run()
	{

		while (!generator.isCanceled())
		{
			Thread.currentThread().yield();
			int val = generator.next();
			if (val % 2 != 0)
			{
				System.out.println("#"+id+"#"+val + " is not even!");
				generator.cancel();
			}
			else
			{
				System.out.println("#"+id+"#"+val + " is even!");
			}
		}
	}

	public static void test(IntGenerator g, int count)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++)
		{
			exec.execute(new EvenChecker(g, i));
		}
		exec.shutdown();
	}
}
