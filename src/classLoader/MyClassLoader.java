package classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/***
 * 自己编写一个ClassLoader
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
