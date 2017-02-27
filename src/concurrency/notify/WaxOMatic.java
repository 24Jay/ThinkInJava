package concurrency.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic
{

	public static void main(String[] ar) throws InterruptedException
	{
		Car car = new Car();
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new WaxOff(car));
		exe.execute(new WaxOn(car));
		exe.execute(new Water());
		TimeUnit.SECONDS.sleep(10);// .sleep(1);
		exe.shutdownNow();
		System.out.println("Having shutdown");
		if (!exe.isTerminated())
			System.out.println("Terminated Error!");
		exe.shutdown();
	}

}

class Water implements Runnable
{

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println("watering always....哗啦啦");
			}
			catch (InterruptedException e)
			{
//				e.printStackTrace();
				System.out.println("Executor has invoked shutdown, thus interrupt() for me!");
				System.exit(0);
			}

		}
	}
}

class WaxOn implements Runnable
{
	private Car car;

	public WaxOn(Car c)
	{
		this.car = c;
	}

	@Override
	public void run()
	{

		try
		{
			while (!Thread.interrupted())
			{
				System.out.println("Waxxing on for 200 millionseconds...");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Existing via interrupt.");
		}
		System.out.println("Ending wax-on task");
	}

}

class WaxOff implements Runnable
{
	private Car car;

	public WaxOff(Car c)
	{
		this.car = c;
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				car.waitForWaxing();
				System.out.println("Waxxing off for 200.....");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Exiting via interrupt~.");
		}
		System.out.println("Ending wax off task.");
	}

}

class Car
{
	private boolean waxOn = false;

	public synchronized void waxed()
	{
		waxOn = true;
		notifyAll();
	}

	public synchronized void buffed()
	{
		waxOn = false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException
	{
		while (waxOn == false)
			wait();
	}

	public synchronized void waitForBuffing() throws InterruptedException
	{
		while (waxOn == true)
			wait();
	}
}