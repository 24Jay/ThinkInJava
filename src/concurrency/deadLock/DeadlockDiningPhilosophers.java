package concurrency.deadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadlockDiningPhilosophers
{

	public static void main(String[] ar) throws InterruptedException
	{
		int ponder = 550;
		int size = 5;

		ExecutorService exe = Executors.newCachedThreadPool();
		Chopstick[] chopsticks = new Chopstick[5];
		for (int i = 0; i < size; i++)
		{
			chopsticks[i] = new Chopstick();
		}
		
		for(int i=0;i<size;i++)
		{
			exe.execute(new Philosopher(chopsticks[i], chopsticks[(i+1)%size], i, ponder));
		}
		TimeUnit.SECONDS.sleep(15);
		exe.shutdown();
		
	}
}
