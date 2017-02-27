package concurrency.blockQueue;

public class Jammer implements Runnable
{

	private ToastQueue butterQueue, finishedQueue;

	public Jammer(ToastQueue bq, ToastQueue fq)
	{
		this.butterQueue = bq;
		this.finishedQueue = fq;
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				Toast t = butterQueue.take();
				t.jammed();
				System.out.println("Jammed a toast, finish it.");
				finishedQueue.put(t);
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Jammer interrupted.");
		}
		System.out.println("Jammer off");
	}

}
