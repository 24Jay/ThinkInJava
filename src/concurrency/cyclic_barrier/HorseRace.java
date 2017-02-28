package concurrency.cyclic_barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorseRace
{
	static final int FINISH_LINE = 100;

	private List<Horse> horses = new ArrayList<Horse>();

	private ExecutorService exe = Executors.newCachedThreadPool();

	private CyclicBarrier barrier;
	private int countDown =9;

	public HorseRace(int n, final int pause)
	{
		barrier = new CyclicBarrier(n, new Runnable()
		{

			@Override
			public void run()
			{
				while (countDown-- > 0)
				{
					System.out.println("CountDown : " + countDown);
					try
					{
						Thread.sleep(500);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}

				StringBuilder s = new StringBuilder();
				/**
				 * 打印跑道
				 */
				for (int i = 0; i < FINISH_LINE; i++)
					s.append("=");
				System.out.println(s);

				/**
				 * 打印每一匹马的轨迹
				 */
				for (Horse h : horses)
					System.out.println(h.tracks());

				/**
				 * 判断名次
				 */
				boolean first = false;
				for (Horse h : horses)
				{
					if (!first && h.getStrides() >= FINISH_LINE)
					{
						System.out.println(h + " won!!!");
						first = true;
						continue;
					}
					if (first && h.getStrides() >= FINISH_LINE)
					{
						System.out.println(h + " won the second place!!!");
						exe.shutdownNow();
						return;
					}

				}

				try
				{
					TimeUnit.MILLISECONDS.sleep(pause);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		for (int i = 0; i < n; i++)
		{
			Horse h = new Horse(barrier);
			horses.add(h);
			exe.execute(h);
		}

	}

	public static void main(String[] ar)
	{
		int n = 8;
		int p = 1000;
		new HorseRace(n, p);
	}
}
