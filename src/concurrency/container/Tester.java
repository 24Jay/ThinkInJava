package concurrency.container;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 测试各种容器<br>
 * 模板方法模式<br>
 * 
 * @author jay
 *
 * @param <C>
 */
public abstract class Tester<C>
{
	static int testReps = 1000;

	static int testCycles = 1000;

	static int containerSize = 7;

	abstract C containerInitializer();

	abstract void startReaderAndWriter();

	C container;

	String testID;

	int nReaders;

	int nWriters;

	volatile long readResult = 0;

	volatile long readTime = 0;

	volatile long writeTime = 0;

	CountDownLatch endLatch;

	static ExecutorService exe = Executors.newCachedThreadPool();

	static Integer[] writeData ={3,45,66,771,1,3,1,21,22};

	public Tester(String id, int nR, int nW)
	{
		this.testID = id;
		this.nReaders = nR;
		this.nWriters = nW;

		for (int i = 0; i < testReps; i++)
		{
			runTest();
			readTime = 0;
			writeTime = 0;
		}
	}

	void runTest()
	{
		endLatch = new CountDownLatch(nReaders + nWriters);
		container = containerInitializer();
		startReaderAndWriter();
		try
		{
			endLatch.await();
		}
		catch (InterruptedException e)
		{
			System.out.println("EndLatch interrupted.");
		}

		System.out.println(
				"TestID=" + testID + ", ReadTime=" + readTime + ", WriteTime=" + writeTime);
	}

	abstract class TestTask implements Runnable
	{
		abstract void test();

		abstract void putResult();

		long duration;

		@Override
		public void run()
		{
			long start = System.nanoTime();
			test();
			duration = System.nanoTime() - start;
			synchronized (Tester.this)
			{
				putResult();
			}
			endLatch.countDown();
		}

	}

}
