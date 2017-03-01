package concurrency.container;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class ListTest extends Tester<List<Integer>>
{

	public ListTest(String id, int nR, int nW)
	{
		super(id, nR, nW);

	}

	class Reader extends TestTask
	{
		long result = 0;

		void test()
		{
			for (long i = 0; i < testCycles; i++)
			{
				for (int index = 0; index < containerSize; index++)
					result += container.get(index);
			}
		}

		void putResult()
		{
			readResult += result;
			readTime += duration;
		}
	}

	class Writer extends TestTask
	{

		void test()
		{
			for (long i = 0; i < testCycles; i++)
			{
				for (int index = 0; index < containerSize; index++)
					container.set(index, writeData[index]);
			}
		}

		@Override
		void putResult()
		{
			writeTime += duration;
		}

	}

	@Override
	void startReaderAndWriter()
	{
		for (int i = 0; i < nWriters; i++)
			exe.execute(new Writer());
		try
		{
			TimeUnit.SECONDS.sleep(0);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < nReaders; i++)
			exe.execute(new Reader());

	}

}
