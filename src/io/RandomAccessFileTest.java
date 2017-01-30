package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest
{
	public static void main(String[] args) throws IOException
	{
		RandomAccessFile rf = new RandomAccessFile(new File("./src/io/RandomAccessFileTest.java"), "rw");
		String line;
		rf.seek(50);
		while ((line=rf.readLine())!=null)
		{
			System.out.println(line);
		}
		rf.close();
	}
}
