package concurrency.blockQueue.priority_block_queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/***
 * 带有优先级的阻塞队列<br>
 * 
 * @author jay
 *
 */
public class PriorityBlockingQueueDemo
{

	public static void main(String[] a)
	{
		ExecutorService exe = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		/***
		 * Producer向这个queue中添加Task,Consumer从这个queue中依据优先级读取Task<br>
		 * 因为PriorityBlokcingQueue的阻塞特性提供了所必需的同步,因此不需要显示的使用lock或者synchronized<br>
		 * 1个Producer,3个Consumer
		 */
		exe.execute(new PrioritizedTaskProducer(queue, exe));
		exe.execute(new PrioritizedTaskConsumer(queue));
		exe.execute(new PrioritizedTaskConsumer(queue));
		exe.execute(new PrioritizedTaskConsumer(queue));
	}
}
