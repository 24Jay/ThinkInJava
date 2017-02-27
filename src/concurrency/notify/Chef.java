package concurrency.notify;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable
{
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant r)
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
					while (restaurant.meal != null)
						wait();
				}
				if (++count == 10)
				{
					System.out.println("Out of food, closing!");
					restaurant.exe.shutdownNow();
				}

				System.out.print("Order up!");
				synchronized (restaurant.person)
				{
					restaurant.meal = new Meal(count);
					restaurant.person.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("CHef interrupted!");
		}
	}

}
