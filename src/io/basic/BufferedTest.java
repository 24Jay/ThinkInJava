package io.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/***
 * 装饰器设计模式
 * 减少程序运行过程中调用系统IO的次数，提升效率 
 * @author jay
 *
 */
public class BufferedTest
{
	public static void main(String[] args) throws IOException
	{

		BufferedReader inputStream = null;
		BufferedWriter outputStream = null;

		try
		{
			inputStream = new BufferedReader(new FileReader("love.txt"));
			outputStream = new BufferedWriter(new FileWriter("bufferedoutput.txt"));

			String l;
			int count = 0;
			while ((l = inputStream.readLine()) != null)
			{
				outputStream.write(l);
				System.out.println("count = " + (count++) + ", line = " + l);
			}
		}
		finally
		{
			if (inputStream != null)
			{
				inputStream.close();
			}
			if (outputStream != null)
			{
				outputStream.close();
			}
		}
	}
}
