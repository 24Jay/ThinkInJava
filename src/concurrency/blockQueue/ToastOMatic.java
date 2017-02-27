package concurrency.blockQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ToastOMatic
{
	public static void main(String[] ar) throws InterruptedException
	{
		ToastQueue dry = new ToastQueue(), butter = new ToastQueue(), jammed = new ToastQueue();

		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new Toaster(dry));
		exe.execute(new Butter(dry, butter));
		exe.execute(new Jammer(butter, jammed));
		exe.execute(new Eater(jammed));
		TimeUnit.SECONDS.sleep(15);
//		exe.shutdown();
		exe.shutdownNow();
	}
}
