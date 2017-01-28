package io.compress;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipCompress
{
	public static void main(String[] ar) throws IOException
	{
		/**
		 * Read a file and compress with GZIP
		 */
		// char oriented
		BufferedReader reader = new BufferedReader(new FileReader("./README.md"));

		// byte oriented
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream("README.gz")));
		int c = 0;
		while ((c = reader.read()) != -1)
		{
			out.write(c);
		}
		out.close();
		reader.close();

		/**
		 * Read the compressed file
		 */
		//char oriented
		BufferedReader in = new BufferedReader(
				new InputStreamReader(new GZIPInputStream(new FileInputStream("README.gz"))));
		String b;
		while ((b = in.readLine()) != null)
		{
			System.out.println(b);
		}
		in.close();

		//byte oriented
		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		GZIPInputStream byt = new GZIPInputStream(new FileInputStream("README.gz"));
		int v;
		while ((v = byt.read()) != -1)
		{
			System.out.print((char) v);
		}
		byt.close();
	}
}
