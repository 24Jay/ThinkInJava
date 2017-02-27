package concurrency.blockQueue;

public class Eater implements Runnable
{

	private ToastQueue finishedQueue;

	private int counter = 0;

	public Eater(ToastQueue fq)
	{
		this.finishedQueue = fq;
	}

	@Override
	public void run()
	{

		try
		{
			while (!Thread.interrupted())
			{
				Toast t = finishedQueue.take();
				if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED)
				{
					System.out.println("Error occured!");
					System.exit(1);
				}
				else
				{
					System.out.println("Chomp : " + t+"\n");
				}
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Eater interrupted!");
		}

		System.out.println("Eater off!");
	}

}
