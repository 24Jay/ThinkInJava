package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.print.attribute.standard.PrinterName;

public class TextFile extends ArrayList<String>
{
	public TextFile(String fileName, String splitter)
	{
		super(Arrays.asList(read(fileName).split(splitter)));
		if (get(0).equals(""))
			remove(0);
	}

	public TextFile(String fileName)
	{
		this(fileName, "\n");
	}

	public static String read(String fileName)
	{
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			String s;
			while ((s = reader.readLine()) != null)
			{
				builder.append(s);
				builder.append("\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{

			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

	public static void write(String fileName, String text)
	{
		PrintWriter print;
		try
		{
			print = new PrintWriter(new File(fileName).getAbsoluteFile());
			print.print(text);
			// the file can be empty without flush, why?
			print.flush();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void write(String fileName)
	{
		PrintWriter print = null;
		try
		{
			print = new PrintWriter(new File(fileName).getAbsoluteFile());
			for (String item : this)
				print.print(item);
			print.flush();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			print.close();
		}

	}

	public static void main(String[] args)
	{
		String file = read("./src/io/TextFile.java");
		System.out.println(file);
		write("textfile.txt", file);

		TextFile textFile = new TextFile("./textfile.txt");
		textFile.write("./text2.txt");
	}
}
