package basic;

public class TestString 
{
	public static void main(String[] ar)
	{
		String a = new String("zhangjie");
		String b = new String("zhangjie");
		String ac = "zhangjie";
		System.out.println("a.equals(b) : " + a.equals(b));
		System.out.println("a == b : " + (a == b));
		System.out.println("a == ac : " + (a == ac));

		System.out.println("String覆盖了equals方法，但是StringBuffer没有，因此二者会有区别！");

		StringBuffer c = new StringBuffer("zhangjie");
		StringBuffer d = new StringBuffer("zhangjie");
		System.out.println("c.equals(d) : " + c.equals(d));
		System.out.println("c == d : " + (c == d));

		System.out.println(
				"c.toString().equals(d.toString()) : " + (c.toString().equals(d.toString())));
		System.out.println("c.toString()==(d.toString()) : " + (c.toString() == (d.toString())));

		/**
		 * 第一条语句打印的结果为 false,第二条语句打印的结果为 true,这说明 javac 编译可以对
		 * 字符串常量直接相加的表达式进行优化,不必要等到运行期去进行加法运算处理,而是在编
		 * 译时去掉其中的加号,直接将其编译成一个这些常量相连的结果。
		 * 
		 */
		String s1 = "a";
		String s2 = s1 + "b";
		String s3 = "a" + "b";
		System.out.println("s2 == \"ab\" : " + (s2 == "ab"));
		System.out.println("s2 == \"ab\" : " + (s3 == "ab"));

		/***
		 * 
		 */
		compareEfficiency();
	}

	private static void compareEfficiency()
	{
		System.out.println("\nCompare Efficiency between String and StringBuffer.");
		long t1 = System.currentTimeMillis();
		String s = "";
		for (int i = 0; i < 10000; i++)
		{
			s += i;
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Time using String : " + (t2 - t1));

		long t3 = System.currentTimeMillis();
		StringBuffer ss = new StringBuffer("");
		for (int i = 0; i < 10000; i++)
		{
			ss.append(i);
		}
		long t4 = System.currentTimeMillis();
		System.out.println("Time using StringBuffer : " + (t4 - t3));
	}

}
