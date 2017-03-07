package classLoader;

import java.util.Arrays;

/***
 * 类加载有三种方式：
 * 
 * 1、命令行启动应用时候由JVM初始化加载
 * 
 * 2、通过Class.forName()方法动态加载
 * 
 * 3、通过ClassLoader.loadClass()方法动态加载
 * 
 * @author jay
 *
 */
public class TestClassLoader
{
	
	public void methodForTest()
	{
		System.out.println("This is a method for test.");
	}
	
	public static void main(String[] ar) throws InstantiationException, IllegalAccessException
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		/***
		 * 从输出的结果可以看出，没有获取到ExtClassLoader的父Loader，原因是Bootstrap
		 * Loader（引导类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。
		 */
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent() + "\n");

		/***
		 * Class.forName()和ClassLoader.loadClass()区别:<br>
		 * Class.forName()：将类的.class文件加载到jvm中，对类进行解释，执行类中的static块<br>
		 * ClassLoader.loadClass()：只干一件事情，将.class文件加载到jvm中，不执行static中的内容,<br>
		 * 只有在newInstance才会去执行static块。<br>
		 */
		try
		{
			/***
			 * 下面两种方法都可以执行static初始化块,但是如果同时存在,就只执行一次<br>
			 */
			Class<?> c = loader.loadClass("classLoader.LoadedClass");
			System.out.println(Arrays.toString(c.getDeclaredFields()));
			LoadedClass instance1 = (LoadedClass) c.newInstance();
			instance1.test();

			Class<?> l = Class.forName("classLoader.LoadedClass");
			LoadedClass instance = (LoadedClass) l.newInstance();
			instance.test();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
}
