package concurrency.deadLock;

class Chopstick
{
	private int stickCount = 0;

	private boolean taken = false;

	public Chopstick()
	{

	}

	public synchronized void take() throws InterruptedException
	{
		while (taken)
			wait();
		taken = true;
	}

	public synchronized void drop()
	{
		taken = false;
		notifyAll();
	}
}
