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

	/****
	 * 
	 * 站在Java虚拟机的角度来讲，只存在两种不同的类加载器：<br>
	 * 
	 * 启动类加载器：<br>
	 * 它使用C++实现（这里仅限于Hotspot，有其他的虚拟机是用Java实现的），是虚拟机自身的一部分；<br>
	 * 
	 * 所有其他的类加载器：<br>
	 * 这些类加载器都由Java语言实现，独立于虚拟机之外，并且全部继承自抽象类java.lang.ClassLoader，<br>
	 * 这些类加载器需要由启动类加载器加载到内存中之后才能去加载其他的类。<br>
	 * 
	 * 
	 * <br>
	 * <br>
	 * <br>
	 * <br>
	 * <br>
	 * <br>
	 * <br>
	 * <br>
	 * 
	 * 站在Java开发人员的角度来看，类加载器可以大致划分为以下三类：
	 * 
	 * 启动类加载器：BootstrapClassLoader <br>
	 * 负责加载存放在JDK\jre\lib(JDK代表JDK的安装目录，下同)下，<br>
	 * 或被-Xbootclasspath参数指定的路径中的，并且能被虚拟机识别的类库<br>
	 * （如rt.jar，所有的java.*开头的类均被Bootstrap<br>
	 * ClassLoader加载）。启动类加载器是无法被Java程序直接引用的。<br>
	 * 
	 * 扩展类加载器：ExtensionClassLoader<br>
	 * 该加载器由sun.misc.Launcher$ExtClassLoader实现，<br>
	 * 它负责加载DK\jre\lib\ext目录中，或者由java.ext.dirs系统变量指定的路径中的所有类库<br>
	 * （如javax.*开头的类），开发者可以直接使用扩展类加载器。<br>
	 * 
	 * 应用程序类加载器：ApplicationClassLoader<br>
	 * 该类加载器由sun.misc.Launcher$AppClassLoader来实现，<br>
	 * 它负责加载用户类路径（ClassPath）所指定的类，开发者可以直接使用该类加载器，<br>
	 * 如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。<br>
	 * 
	 * 
	 */
	public static void testLoader()
	{

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		/***
		 * 从输出的结果可以看出，没有获取到ExtClassLoader的父Loader，原因是Bootstrap
		 * Loader（引导类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。
		 */
		ClassLoader parent = loader.getParent();
		ClassLoader grand = parent.getParent();
		System.out.println("AppClassLoader : " + loader);
		System.out.println(System.getProperty("java.class.path"));
		System.out.println("ExtClassLoader : " + parent);
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println("BootstrapLoader : " + grand);
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println("****************");
		System.out.println("****************");
	}

	public static void main(String[] ar) throws InstantiationException, IllegalAccessException
	{
		testLoader();

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
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
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
