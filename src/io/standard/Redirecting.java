package io.standard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting
{
	public static void main(String[] ar) throws IOException
	{
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(
				new FileInputStream("./src/io/standard/Redirecting.java"));

		PrintStream out = new PrintStream(
				new BufferedOutputStream(new FileOutputStream("Redirecting.txt")));

		/**
		 * Redirect the standard IN, out, error
		 */
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String s;
		/**
		 * readLine() function will delete "\n" while reading from original file
		 */
		while ((s = reader.readLine()) != null)
		{

			/**
			 * System.out will not print these lines on the console because of
			 * Redirecting
			 */
			// System.out.print(s);
			System.out.println(s);
		}

		in.close();
		reader.close();
		out.flush();
		out.close();

		// back to default
		System.setOut(console);
		System.out.println("Back to default!");
	}
}
