package basic.autobox;

import java.util.Comparator;

public class Test
{

	public static void main(String[] ar)
	{
		Comparator<Integer> ordre = new Comparator<Integer>()
		{

			@Override
			public int compare(Integer o1, Integer o2)
			{
				return o1 < o2 ? -1 : (o1 == o2) ? 0 : 1;
			}
		};
		
		Integer a = new Integer(2);
		int b = 2;
		Integer c = new Integer(2);
		Integer d = a;
		
		System.out.println(ordre.compare(a, b));
		System.out.println(ordre.compare(a, c));
		System.out.println(ordre.compare(2, 2));
		System.out.println(ordre.compare(a, d));
	}
}
