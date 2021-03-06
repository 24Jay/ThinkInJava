package concurrency.synchronize;

public class PossibleReordering
{
	private static int x = 0, y = 0;

	private static int a = 0, b = 0;

	public static void main(String[] ar) throws InterruptedException
	{
		Thread one = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				a = 1;
				x = b;
			}
		});

		Thread other = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				b = 1;
				y = a;
			}
		});

		one.start();
		other.start();
		one.join();
		other.join();
		System.out.println("(" + x + ", " + y + ")");
	}
}
