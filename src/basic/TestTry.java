package basic;

public class TestTry
{
	public static void main(String[] ar)
	{
		/**
		 * :finally 中的代码比 return 和 break 语句后执行
		 * 
		 */
		System.out.println(new TestTry().test());
		System.out.println(get());
		System.out.println(test2());
		trySystemExit();
	}

	/***
	 * throw and throws
	 * 
	 * @throws Exception
	 */
	private static void testThrows() throws Exception
	{
		throw new Exception("throw and throws");
	}

	int test()
	{
		int x = 1;
		try
		{
			return x;
		}
		finally
		{
			++x;
		}
	}

	public static void trySystemExit()
	{
		try
		{
			System.out.println("finally will not excuted when using System.exit()");
		}
		finally
		{
			System.exit(1);
			System.out.println("finallyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
			return ;
		}
	}
	public static int get()
	{
		try
		{
			return 1;
		}
		finally
		{
			return 2;
		}
	}

	/***
	 * try 中的 return 语句调用的函数先于 finally 中调用的函数执行. <br>
	 * 也就是说return语句先执行,finally语句后执行,所以,返回的结果是2。<br>
	 * 但是return并不是让函数马上返回,而是return语句执行后,将把返回结果放置进函数栈中；<br>
	 * 此时函数并不是马上返回,它要执行finally语句后才真正开始返回。
	 * 
	 * @return
	 */
	static int test2()
	{
		try
		{
			return func1();
		}
		finally
		{
			return func2();
		}
	}

	static int func1()
	{
		System.out.println("func1");
		return 1;
	}

	static int func2()
	{
		System.out.println("func2");
		return 2;
	}

}
