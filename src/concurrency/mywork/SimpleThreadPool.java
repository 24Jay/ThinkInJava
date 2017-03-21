package concurrency.mywork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SimpleThreadPool
{
	private BlockingQueue queue = null;

	private List<Thread> threads = new ArrayList<Thread>();

	private boolean stop = false;

	public SimpleThreadPool(int count, int maxTasks)
	{
		this.queue = new LinkedBlockingDeque<>(maxTasks);

		for (int i = 0; i < count; i++)
		{
			threads.add(new TaskThread());
		}

	}
}

class TaskThread implements Runnable
{

	@Override
	public void run()
	{
		
	}
	
}