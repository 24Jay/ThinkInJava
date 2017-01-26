package io.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * All other stream types are built on byte streams
 * @author jay
 *
 */
public class CopyBytes
{
	public static void main(String[] args) throws IOException
	{

		FileInputStream in = null;
		FileOutputStream out = null;

		/**
		 * Reads the input stream and writes the output stream, one byte at a time
		 */
		try
		{
			in = new FileInputStream("love.txt");
			out = new FileOutputStream("outagain.txt");
			int c;
			while ((c = in.read()) != -1)
			{
				System.out.println("int = "+c+", char = "+(char)c);
//				System.out.print((char)c);
				out.write(c);
			}
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}
			if (out != null)
			{
				out.close();
			}
		}
	}
}