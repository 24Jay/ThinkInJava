package classLoader;

public class LoadedClass
{
	public int a;
	
	public String s;
	
	public boolean b;
	static
	{
		System.out.println("执行静态初始化块..");
	}

	public void test()
	{
		System.out.println("this is test method!");
	}
}
