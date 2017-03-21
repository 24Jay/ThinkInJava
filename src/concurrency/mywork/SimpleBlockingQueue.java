package concurrency.mywork;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T>
{

	private Queue<T> queue = new LinkedList<T>();

	private int limit = 10;

	public SimpleBlockingQueue(int li)
	{
		this.limit = li;
	}

	public SimpleBlockingQueue()
	{
		this(10);
	}

	public synchronized void enqueue(T e) throws InterruptedException
	{
		while (this.limit == queue.size())
		{
			wait();
		}
		/**
		 * 这里是很重要的:<br >
		 * 如果之前queue是空的,dequeue线程会阻塞;<br>
		 * 当queue里已经有了2个元素的时候,就唤醒dequeue线程<br>
		 */
		if (queue.size() == 2)
		{
			notifyAll();
		}
		this.queue.add(e);
	}

	public synchronized T dequeue() throws InterruptedException
	{
		while (queue.size() == 0)
		{
			wait();
		}
		/***
		 * 这里是重要的: 当queue里的元素只剩下一般的时候,唤醒enqueue线程<br>
		 */
		if (queue.size() == limit / 2)
		{
			notifyAll();
		}
		return this.queue.remove();
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder("[");
		for (T t : queue)
		{
			builder.append(t + "\t");
		}
		builder.append("]\n");
		return builder.toString();
	}

	public static void main(String[] ar)
	{
		SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				int i = 0;

				try
				{
					while (true)
					{
						queue.enqueue(i++);
						System.out.println("Enqueue " + i + " times : " + queue.toString());
						Thread.sleep(300);
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						System.out.println("Dequeue = " + queue.dequeue() + "\n");
						Thread.sleep(500);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
