package concurrency.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool
{
	public static void main(String[] ar)
	{
		ExecutorService poolExecutor = new ThreadPoolExecutor(2, 100, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		
		
	}
}
