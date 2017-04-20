package concurrency;

import java.util.concurrent.TimeUnit;

public class RunAndStart
{
	public static void main(String[] ar) throws InterruptedException
	{

		/***
		 * 1.只有start()才真正实现了多线程运行。<br>
		 * 这时无需等待run方法体代码执行完毕，不同线程的run()方法交替执行；<br>
		 * 通过调用Thread类的start()方法来启动一个线程， 这时此线程是处于就绪状态， 并没有运行。<br>
		 * 然后通过此Thread类调用方法run()来完成其运行操作的， <br>
		 * 这里方法run()称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程终止。 <br>
		 * <br>
		 * <br>
		 * 2.如果只是调用run()方法那么程序还是同步执行。<br>
		 * 程序还是要顺序执行，要等待一个run()执行完毕后，才可继续执行下面的代码；<br>
		 * 程序执行路径还是只有一条， 这样就没有达到写线程的目的。
		 */
		for (int i = 0; i < 3; i++)
		{
			MyThread t = new MyThread();
			t.run();
			// t.start();
		}

		while (true)
		{
			System.out.println("main thread.....");
			TimeUnit.SECONDS.sleep(1);
		}
	}
}

class MyThread extends Thread
{
	private static int count = 0;

	private int NO = 0;

	public MyThread()
	{
		this.NO = count++;
	}

	@Override
	public void run()
	{
		long time = System.currentTimeMillis();
		while (System.currentTimeMillis() - time < 5000)
		{
			System.out.println(NO + "——Running...");
			try
			{
				// Thread.sleep(1000);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
