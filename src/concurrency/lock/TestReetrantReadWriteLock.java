package concurrency.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReetrantReadWriteLock
{	
	 public static void main(String []ar)
	 {
		 ReadWriteLock lock = new ReentrantReadWriteLock(false);
	 }
}
