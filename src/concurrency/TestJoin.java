package concurrency;

public class TestJoin
{
	public static void main(String[] ar)
	{
		/**
		 * 因为这里是static方法,如果Sleeper不是static的,将不能访问Sleeper<br>
		 */
		Sleeper sleepy = new Sleeper("Sleepy", 15000);
		Sleeper grumpy = new Sleeper("Grumpy", 15000);

		Joiner dopey = new Joiner("Dopey", sleepy);
		Joiner doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}

	public void test()
	{
		Sleeper sleeper = new Sleeper("Sleepy", 1500);
	}

	/***
	 * 这里必须为
	 * 
	 * @author jay
	 *
	 */
	static class Sleeper extends Thread
	{
		private int duration;

		public Sleeper(String name, int time)
		{
			super(name);
			duration = time;
			start();
		}

		@Override
		public void run()
		{
			try
			{
				sleep(duration);
			}
			catch (Exception e)
			{
				System.out
						.println(getName() + " was interrupted. Is interrupted?" + isInterrupted());
			}
			System.out.println(getName() + " has awakened.");
		}

	}

	static class Joiner extends Thread
	{
		private Sleeper sleeper;

		public Joiner(String name, Sleeper sl)
		{
			super(name);
			this.sleeper = sl;
			start();
		}

		@Override
		public void run()
		{
			try
			{
				sleeper.join();
			}
			catch (Exception e)
			{
				System.out.println("Joiner Interrupted.");

			}
			System.out.println(getName() + " join completed.");
		}

	}
}
