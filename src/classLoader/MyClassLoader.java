package classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



/***
 * 
 * 
 * 自己编写一个ClassLoader:
 * 
 * 
 * 
 * 类初始化时机：只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种：
 * 
 * – 创建类的实例，也就是new的方式
 * 
 * – 访问某个类或接口的静态变量，或者对该静态变量赋值
 * 
 * – 调用类的静态方法
 * 
 * – 反射（如Class.forName(“com.shengsiyuan.Test”)）
 * 
 * – 初始化某个类的子类，则其父类也会被初始化
 * 
 * – Java虚拟机启动时被标明为启动类的类（Java Test），直接使用java.exe命令来运行某个主类
 * 
 * <br>
 * 结束生命周期
 * 
 * •在如下几种情况下，Java虚拟机将结束生命周期
 * 
 * – 执行了System.exit()方法
 * 
 * – 程序正常执行结束
 * 
 * – 程序在执行过程中遇到了异常或错误而异常终止
 * 
 * – 由于操作系统出现错误而导致Java虚拟机进程终止
 * 
 * @author jay
 *
 */
public class MyClassLoader extends ClassLoader
{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] classData = loadClassData(name);
		if (classData == null)
		{
			throw new ClassNotFoundException();
		}
		else
		{
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] loadClassData(String name)
	{
		String fileName = "TestClassLoader.class";
		try
		{
			InputStream ins = new FileInputStream(fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = 0;
			while ((length = ins.read(buffer)) != -1)
			{
				baos.write(buffer, 0, length);
			}
			return baos.toByteArray();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] ar)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		ClassLoader loader = new MyClassLoader();
		Class<?> cl = loader.loadClass("classLoader.TestClassLoader");
		TestClassLoader t = (TestClassLoader) cl.newInstance();
		t.main(null);
		t.methodForTest();
	}

}
