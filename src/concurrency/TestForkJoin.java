package concurrency;

public class TestForkJoin
{
	public static void main(String[] ar)
	{
		System.out.println(Thread.currentThread().getName());
		for (int i = 0; i < 10; i++)
		{
			new Thread("thread" + i)
			{

				@Override
				public void run()
				{
					System.out.println(Thread.currentThread().getName());
				}

			}.start();

		}
	}
}
