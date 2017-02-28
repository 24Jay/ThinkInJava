package concurrency.blockQueue.priority_block_queue;

import java.util.concurrent.PriorityBlockingQueue;

public class PrioritizedTaskConsumer implements Runnable
{
	private PriorityBlockingQueue<Runnable> q;

	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> w)
	{
		this.q = w;
	}

	@Override
	public void run()
	{

		try

		{
			while (!Thread.interrupted())
				q.take().run();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Finish PrioritizedTaskConsumer.");
	}

}
