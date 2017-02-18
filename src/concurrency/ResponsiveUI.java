package concurrency;

import java.io.IOException;
import java.util.Scanner;

public class ResponsiveUI extends Thread
{
	private static volatile double d = 1;

	public ResponsiveUI()
	{
		setDaemon(true);
		start();
	}

	@Override
	public void run()
	{
		try
		{
			sleep(100);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		while (true)
		{
			for (int i = 1; i < 10; i++)
			{
				d += i;
			}
			System.out.println(d);

		}
		// d = d + (Math.PI + Math.E) / d;

	}

	public static void main(String[] ar) throws IOException
	{
		new ResponsiveUI();
		while (true)
		{
			Scanner scanner = new Scanner(System.in);
			int in = scanner.nextInt();
			System.out.println(in);
			System.out.println(d);
		}
	}
}
