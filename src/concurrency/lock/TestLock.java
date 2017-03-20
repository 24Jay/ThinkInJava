package concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock
{
	public static void main(String[] ar) throws InterruptedException
	{
		ReentrantLock lock = new ReentrantLock();
		lock.tryLock();
		System.out.println("do somthing ...");
		TimeUnit.SECONDS.sleep(1);
		
		lock.unlock();
		lock.unlock();
		AtomicInteger a = new AtomicInteger();
	}
}
