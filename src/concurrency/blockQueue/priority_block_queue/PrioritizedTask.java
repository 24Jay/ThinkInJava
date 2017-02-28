package concurrency.blockQueue.priority_block_queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>
{


	private static int counter = 0;

	private final int id = counter++;

	private final int priority;

	public PrioritizedTask(int p)
	{
		this.priority = p;
	}

	@Override
	public int compareTo(PrioritizedTask o)
	{
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}

	@Override
	public void run()
	{
		try
		{
			TimeUnit.MILLISECONDS.sleep(250);
		}
		catch (InterruptedException e)
		{

		}
		System.out.println("------Do something here by " + this);
	}

	public String toString()
	{
		return "[Task-" + id + ", priority-" + priority + "]";
	}

	public String summary()
	{
		return "(" + id + ":" + priority + ")";
	}

	static class EndSentinel extends PrioritizedTask
	{
		private ExecutorService exe;

		public EndSentinel(ExecutorService s)
		{
			super(-1);
			this.exe = s;
		}

		@Override
		public void run()
		{
			System.out.println("&&&&&&&&&&&&&&& shutdown here");
			exe.shutdownNow();

		}

	}
}
