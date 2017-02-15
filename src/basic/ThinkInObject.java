package basic;


public class ThinkInObject extends Object
{

	public static void main(String[] ar)
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
					System.out.println("A thread begin..."+this.getClass());
//					Thread.sleep(1000);
					synchronized (a)
					{
						a.wait();
						System.out.println("A thread continue..."+this.getClass());
//						this.finalize();
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
					System.out.println("B thread begin..."+this.getClass());
//					Thread.sleep(1000);
					synchronized (a)
					{
						// Thread.sleep(1000);
						a.wait(50);
						System.out.println("B thread continue..."+this.getClass());
//						this.finalize();
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
					System.out.println("C thread begin...notify"+this.getClass());
					// a.notifyAll();
					a.notify();
				}
			}
		}).start();
	}

	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable
	{
		// TODO Auto-generated method stub
		super.finalize();
	}

}
