package io.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class ScannerTest
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new BufferedReader(new FileReader("love.txt")));
			scanner.useDelimiter("\n");
			scanner.useDelimiter("S");
			scanner.useDelimiter(",\\s*");

			while (scanner.hasNext())
			{
				System.out.println(scanner.next());
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			/**
			 * Even though a scanner is not a stream, you need to close it to
			 * indicate that you're done with its underlying stream.
			 */
			if (scanner != null)
				scanner.close();
		}

		scanNumber();

	}

	public static void scanNumber() throws FileNotFoundException
	{

		Scanner s = null;
		double sum = 0;

		try
		{
			s = new Scanner(new BufferedReader(new FileReader("usnumber.txt")));
			s.useLocale(Locale.US);

			while (s.hasNext())
			{
				if (s.hasNextDouble())
				{
					double d = s.nextDouble();
					sum += d;
					System.out.println("number d" + "= " + d);
				}
				else
				{
					s.next();
				}
//				if (s.hasNextInt())
//				{
//					int dd = s.nextInt();
//					System.out.println("Int Number = " + dd);
//
//				}
			}
		}
		finally
		{
			s.close();
		}

		System.out.println("Sum of all numbers = " + sum);
	}
}
