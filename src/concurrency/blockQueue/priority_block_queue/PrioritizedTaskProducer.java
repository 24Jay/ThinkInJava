package concurrency.blockQueue.priority_block_queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PrioritizedTaskProducer implements Runnable
{
	private Random rand = new Random(24);

	private PriorityBlockingQueue<Runnable> queue;

	private ExecutorService exe;

	public PrioritizedTaskProducer(PriorityBlockingQueue<Runnable> q, ExecutorService e)
	{
		this.queue = q;
		this.exe = e;
	}

	@Override
	public void run()
	{

		for (int i = 0; i < 10; i++)
		{
			PrioritizedTask t = new PrioritizedTask(rand.nextInt(10));
			queue.add(t);
			System.out.println("***Produce a task " + t.toString());
			Thread.yield();
		}

		try
		{

			for (int i = 0; i < 10; i++)
			{
				TimeUnit.MILLISECONDS.sleep(250);
				PrioritizedTask t = new PrioritizedTask(10);
				queue.add(t);
				System.out.println("***Produce a task " + t.toString());
			}
			for (int i = 0; i < 10; i++)
			{
				PrioritizedTask t = new PrioritizedTask(i);
				queue.add(t);
				System.out.println("***Produce a task " + t.toString());
			}

			/***
			 * 最后添加一个终结者
			 */
			queue.add(new PrioritizedTask.EndSentinel(exe));
		}
		catch (InterruptedException e)
		{
		}
		System.out.println("Finished PrioritizedTaskProducer.");
	}

}
