package io.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

class Data implements Serializable
{
	/**
	 * This serialVersionuId is quite imortant for RMI
	 */
	private static final long serialVersionUID = 1L;
	private int n;

	public Data(int n)
	{
		this.n = n;
	}

	public String toString()
	{
		return Integer.toString(n);
	}
}

public class Worm implements Serializable
{
	/**
	 * This id is quite important for RMI, but not necessary here
	 */
	private static final long serialVersionUID = 1L;

	private static Random rand = new Random(47);

	private Data[] d =
		{ new Data(rand.nextInt(10)), new Data(rand.nextInt(10)), new Data(rand.nextInt(10)) };

	private Worm next;

	private char c;

	public Worm(int i, char x)
	{
		System.out.println("Worm contructor : " + i);
		c = x;
		if (--i > 0)
			next = new Worm(i, (char) (x + 1));
	}

	public Worm()
	{
		System.out.println("Default constructor!");
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder(":");
		builder.append(c);
		builder.append("(");
		for (Data dat : d)
		{
			builder.append(dat);
		}
		builder.append(")");

		if (next != null)
			builder.append(next);

		return builder.toString();
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Worm worm = new Worm(6, 'a');
		System.out.println("worm = " + worm);

		System.out.println("**************************************");
		/**
		 * write file
		 */
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
		out.writeObject("Worm Storage\n");
		out.writeObject(worm);
		out.close();

		/**
		 * read file
		 */
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
		String string = (String) in.readObject();
		Worm w2 = (Worm) in.readObject();
		System.out.print("string=" + string);
		System.out.println("worm=" + w2);
		//close
		in.close();
		System.out.println("**************************************");
		/**
		 * write ByteArray
		 */
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out2 = new ObjectOutputStream(byteOut);
		out2.writeObject("Write another object!\n");
		out2.writeObject(worm);
		out2.flush();

		/**
		 * Read ByteArray
		 */
		ObjectInputStream in2 = new ObjectInputStream(
				new ByteArrayInputStream(byteOut.toByteArray()));
		System.out.print("string=" + (String) in2.readObject());
		System.out.print("worm=" + (Worm) in2.readObject());
		in2.close();
	}

}
