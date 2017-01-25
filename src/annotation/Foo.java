package annotation;

public class Foo
{

	@Test
	public static void m1()
	{
		System.out.println("Foo.m1()");
	}

	public static void m2()
	{
		System.out.println("Foo.m2()");
	}

	@Test
	public static void m3()
	{
		System.out.println("Foo.m3()");
		throw new RuntimeException("Boom");
	}

	public static void m4()
	{
		System.out.println("Foo.m4()");
	}

	@Test
	public static void m5()
	{
		System.out.println("Foo.m5()");
	}

	public static void m6()
	{
		System.out.println("Foo.m6()");
	}

	@Test
	public static void m7()
	{
		System.out.println("Foo.m7()");
		throw new RuntimeException("Crash");
	}

	public static void m8()
	{
		System.out.println("Foo.m8()");
	}
}
