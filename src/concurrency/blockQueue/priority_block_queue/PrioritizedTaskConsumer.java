package concurrency.blockQueue.priority_block_queue;

import java.util.concurrent.PriorityBlockingQueue;

public class PrioritizedTaskConsumer implements Runnable
{
	private PriorityBlockingQueue<Runnable> q;

	private static int counter = 0;

	private final int id = counter++;

	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> w)
	{
		this.q = w;
	}

	public String toString()
	{
		return "Consumer_" + id;
	}

	@Override
	public void run()
	{

		try

		{
			while (!Thread.interrupted())
			{
				System.out.print("------"+this);
				q.take().run();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Finish PrioritizedTaskConsumer.");
	}

}
