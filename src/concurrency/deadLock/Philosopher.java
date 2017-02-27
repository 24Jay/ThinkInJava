package concurrency.deadLock;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Philosopher implements Runnable
{
	private Chopstick left;

	private Chopstick right;

	private final int id;

	private final int ponderFactor;

	private Random rand = new Random(47);

	private void pause() throws InterruptedException
	{
		if (ponderFactor == 0)
			return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt() * 250);
	}

	public Philosopher(Chopstick le, Chopstick ri, int id, int f)
	{
		this.left = le;
		this.right = ri;
		this.id = id;
		this.ponderFactor = f;
	}

	static class Chopstick
	{
		private int stickCount = 0;

		public Chopstick()
		{

		}

		public synchronized void take()
		{
			stickCount--;
		}

		public synchronized void drop()
		{
			stickCount++;
		}
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				System.out.println(this + " thinking...");
				pause();
				System.out.println(this + " grabbing right");
				right.take();

				System.out.println(this + " grabbing left");
				left.take();

				System.out.println(this + " eating!!");
				pause();
				right.drop();
				left.drop();
			}
		}
		catch (InterruptedException e)
		{
			System.out.println(this + " exiting via interrupted.");
		}
	}

	public String toString()
	{
		return "Philosopher " + id;
	}
}
