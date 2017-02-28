package concurrency.notify;

public class WaitPerson implements Runnable
{
	private Restaurant restaurant;

	public WaitPerson(Restaurant r)
	{
		this.restaurant = r;
	}

	@Override
	public void run()
	{

		try
		{
			while (!Thread.interrupted())
			{
				synchronized (this)
				{
					while (restaurant.meal == null)
							wait();
				}
				System.out.println("Waitperson got..." + restaurant.meal);

				synchronized (restaurant.chef)
				{
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		}
		catch (InterruptedException e)
		{

		}
	}

}
