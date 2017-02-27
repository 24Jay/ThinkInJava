package concurrency.blockQueue;

public class Butter implements Runnable
{

	private ToastQueue dryQueue, butteredQueue;

	public Butter(ToastQueue tq, ToastQueue bq)
	{
		this.dryQueue = tq;
		this.butteredQueue = bq;
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				Toast toast = dryQueue.take();
				toast.butter();
				System.out.println("Butter a toast and put it in ButteredQueue!");
				butteredQueue.put(toast);
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Buttered interrrupted.");
		}
		System.out.println("Butter off.");
	}

}
