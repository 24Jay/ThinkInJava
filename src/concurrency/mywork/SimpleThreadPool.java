package concurrency.mywork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.prism.paint.Stop;

public class SimpleThreadPool
{
	private BlockingQueue queue = null;

	private List<Thread> threads = new ArrayList<Thread>();

	private boolean stop = false;

	ExecutorService exee = Executors.newScheduledThreadPool(4);
	ExecutorService exe = Executors.newCachedThreadPool();

	public SimpleThreadPool(int count, int maxTasks)
	{
		this.queue = new LinkedBlockingDeque<>(maxTasks);

		for (int i = 0; i < count; i++)
		{
		}

	}

	Random rang = new Random();
}

class TaskThread implements Runnable
{
	private BlockingQueue tasks = null;

	private boolean stop = false;

	public TaskThread(BlockingQueue qu)
	{
		this.tasks = qu;
	}

	@Override
	public void run()
	{
		while (!stop)
		{

		}
	}

}