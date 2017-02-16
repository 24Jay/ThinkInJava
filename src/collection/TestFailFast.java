package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestFailFast
{
	public static void main(String[] ar)
	{
		Collection<String> list = new ArrayList<String>();
		list.add("beijing");
		list.add("hangzhou");
		list.add("wuhan");
		Iterator<String> it = list.iterator();

		/**
		 * 一旦发现其他进程修改了容器,就会报错
		 */
		try
		{
			list.add("shenzhen");
			System.out.println(it.next());
		}
		catch (ConcurrentModificationException e)
		{
			System.out.println("Throw exception!");
		}

		/**
		 * 从下面的例子中可以看到,当两个线程同操作容器的时候:<br>
		 * 如果没有修改容器的结构(比如仅仅是修改了某个Key对应的value),那么不会抛出ConcurrentModificationException<br>
		 * 如果修改了容器的结构,那么就会抛异常<br>
		 */
		Map<Integer, String> c = new HashMap<Integer, String>();
		c.put(1, "wuhan");
		c.put(2, "hangzhou");
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 100; i++)
				{
					try
					{
						/**
						 * 
						 *这里改变了容器的结构,因此会和另外一个线程冲突<br> 
						*/
						c.put(i, "wuhan_" + i);
						Thread.sleep(10);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}

				}

				while (true)
				{
					/**
					 * 这里仅仅是修改了内容,而不会影响结构,因此不会有异常<br>
					 */
					c.put(1, "update");
					c.put(2, "update");
				}
			}
		}).start();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
					System.out.println(c);
			}
		}).start();

	}
}
