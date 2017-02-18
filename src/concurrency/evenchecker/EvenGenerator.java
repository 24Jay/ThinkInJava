package concurrency.evenchecker;

public class EvenGenerator extends IntGenerator
{
	private int currentValue = 0;

	@Override
	public int next()
	{
		currentValue ++;
//		Thread.currentThread().yield();
		currentValue ++;
		return currentValue;
	}

	
	public static void main(String []ar)
	{
		EvenGenerator generator = new EvenGenerator();
		/**
		 * 这里线程设置很多的时候很快就结束了
		 */
		EvenChecker.test(generator, 50);
	}
}
