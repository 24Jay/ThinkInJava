package concurrency.copyonwrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class TestCopyOnWrite
{
	private static void testList(List<String> list) throws InterruptedException
	{
		// List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("linux");

		Thread t = new Thread(new Runnable()
		{
			int count = 0;

			@Override
			public void run()
			{

				try
				{
					while (true)
					{
						Thread.sleep(50);
						list.add("object_" + count++);
						System.out.println("Add_" + count);
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
		t.setDaemon(true);
		t.start();

		TimeUnit.SECONDS.sleep(3);
		/**
		 * 主线程遍历list,但是上述线程还在修改list,因此会抛出java.util.ConcurrentModificationException<br>
		 */
		for (String s : list)
		{
			Thread.sleep(50);
			System.out.println("ListHashCode = " + list.hashCode() + "--->>>" + s);
		}
	}

	public static void main(String[] ar) throws InterruptedException
	{
		List<String> list = new ArrayList<String>();
		// testList(list);

		/***
		 * 使用CopyOnWriteArrayList就没有这个问题了<br>
		 * 但是可以看到list的hashcode不断改变了,说明在此过程中新建了Object[]<br>
		 */
		List<String> copyonwrite = new CopyOnWriteArrayList<String>();
//		testList(copyonwrite);

		List<String> synchronizedList = Collections.synchronizedList(list);
		testList(synchronizedList);
	}
}
