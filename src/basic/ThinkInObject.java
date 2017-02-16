package basic;

public class ThinkInObject extends Object
{

	public static void main(String[] ar)
	{
		testNotify();
		testHash();
	}

	private static void testNotify()
	{
		int[] a =
			{ 1, 2, 3 };

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					System.out.println("A thread begin..." + this.getClass());
					// Thread.sleep(1000);
					synchronized (a)
					{
						a.wait();
						System.out.println("A thread continue..." + this.getClass());
						// this.finalize();
					}
				}
				catch (Throwable e)
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
				try
				{
					System.out.println("B thread begin..." + this.getClass());
					// Thread.sleep(1000);
					synchronized (a)
					{
						// Thread.sleep(1000);
						a.wait(50);
						System.out.println("B thread continue..." + this.getClass());
						// this.finalize();
					}
				}
				catch (Throwable e)
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
				synchronized (a)
				{
					System.out.println("C thread begin...notify" + this.getClass());
					// a.notifyAll();
					a.notify();
				}
			}
		}).start();
	}
	
	
	private static void testHash()
	{
		MObject m1 = new MObject(1, "Jay");
		MObject m2 = new MObject(1, "MM");
		System.out.println(m1.equals(m2));
		System.out.println(m1.hashCode());
	}
}

class MObject
{
	int id;
	String name;

	public MObject(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode()
	{
		return id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof String)
		{
			return this.name == (String) obj;
		}
		return false;
	}

}
