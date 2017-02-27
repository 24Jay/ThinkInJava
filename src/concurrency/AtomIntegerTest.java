package concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomIntegerTest implements Runnable
{
	private AtomicInteger i = new AtomicInteger(0);

	public int getValue()
	{
		return i.get();
	}

	private void evenIncrement()
	{
		i.addAndGet(1);
		i.addAndGet(1);
	}

	@Override
	public void run()
	{
		while (true)
		{
			Thread.yield();
			evenIncrement();
		}
	}

	public static void main(String[] ar)
	{
		new Timer().schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				System.err.println("Aborting!");
				System.exit(0);
			}
		}, 5000);//exit after 5 seconds

		ExecutorService exec = Executors.newCachedThreadPool();
		AtomIntegerTest ait = new AtomIntegerTest();
		exec.execute(ait);
		while (true)
		{
			int val = ait.getValue();
			if (val % 2 != 0)
			{
				System.out.println(val);
				System.exit(0);
			}
			else
			{
				System.out.println(val + " is a even number!");
			}
		}

	}
}
