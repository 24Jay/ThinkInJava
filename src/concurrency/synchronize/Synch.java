package concurrency.synchronize;

public class Synch
{
	private static int count=0;

	private OBB ob;

	public void foo()
	{
		synchronized (ob)
		{
			System.out.println(ob);
		}
	}

	public synchronized void fooo()
	{
		System.out.println(ob);
	}

	public static void main(String[] ar)
	{
		System.out.println("xxx");
	}

}

class OBB
{
}