package concurrency.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestReetrantReadWriteLock
{	
	 public static void main(String []ar)
	 {
		 ReadWriteLock lock = new ReentrantReadWriteLock(false);
		 String a = new String("zhangjie");
		 String b = new String("zhangjie");
		 int i = System.identityHashCode(a);
		 int j = System.identityHashCode(b);
		 System.out.println(i+", a.hashcode = "+a.hashCode());
		 System.out.println(j+", b.hashcode = "+b.hashCode());

		 System.out.println("CPUs = "+Runtime.getRuntime().freeMemory());
		 System.out.println("CPUs = "+Runtime.getRuntime().totalMemory());
		 System.out.println("CPUs = "+Runtime.getRuntime().maxMemory());
		 System.out.println("CPUs = "+Runtime.getRuntime().availableProcessors());
		 System.out.println("CPUs = "+Runtime.getRuntime().getClass());
	 }
}
