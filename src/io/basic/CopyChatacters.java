package io.basic;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyChatacters
{
	public static void main(String[] args) throws IOException
	{

		FileReader inputStream = null;
		FileWriter outputStream = null;

		/**
		 * However, in CopyCharacters, the int variable holds a character value
		 * in its last 16 bits; in CopyBytes, the int variable holds a byte
		 * value in its last 8 bits.
		 */
		try
		{
			inputStream = new FileReader("love.txt");
			outputStream = new FileWriter("characteroutput.txt");

			int c;
			while ((c = inputStream.read()) != -1)
			{
				outputStream.write(c);
				System.out.println("int c=" + c + ", char c=" + (char) c);
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
