package concurrency.evenchecker;

public class SynchronizedGenerator extends IntGenerator
{
	private int currentValue = 0;

	/***
	 * 使用同步保证不会出现不希望的线程访问
	 */
	@Override
	public synchronized int next()
	{
		currentValue++;
		Thread.currentThread().yield();
		currentValue++;
		return currentValue;
	}

	public static void main(String[] ar)
	{
		SynchronizedGenerator generator = new SynchronizedGenerator();
		/**
		 * 这里线程设置很多的时候很快就结束了
		 */
		EvenChecker.test(generator, 100);
	}
}
