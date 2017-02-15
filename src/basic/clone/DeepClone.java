package basic.clone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/****
 * 使用序列化的方式实现一个deep copy<br>
 * @author jay
 *
 * @param <T>
 */
public class DeepClone<T extends Serializable>
{

	public  <T> T deepClone(T a)
	{
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream(new File("./out.obj")));
			out.writeObject(a);
			out.flush();
			out.close();
			in = new ObjectInputStream(new FileInputStream(new File("./out.obj")));
			T ob = (T) in.readObject();
			return ob;
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				out.close();
				in.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
