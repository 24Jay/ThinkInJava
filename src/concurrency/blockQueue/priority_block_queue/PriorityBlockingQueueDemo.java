package concurrency.blockQueue.priority_block_queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo
{

	public static void main(String[] a)
	{
		ExecutorService exe = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		exe.execute(new PrioritizedTaskProducer(queue, exe));
		exe.execute(new PrioritizedTaskConsumer(queue));
	}
}
