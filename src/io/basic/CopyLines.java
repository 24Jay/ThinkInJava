package io.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CopyLines
{
	public static void main(String[] args) throws IOException
	{

		BufferedReader inputStream = null;
		PrintWriter outputStream = null;

		try
		{
			inputStream = new BufferedReader(new FileReader("love.txt"));
			outputStream = new PrintWriter(new FileWriter("lineoutput.txt"));

			String l;
			int count = 0;
			while ((l = inputStream.readLine()) != null)
			{
				outputStream.println(l);
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
