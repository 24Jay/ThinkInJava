package io.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamTest
{
	static final String dataFile = "invoicedata";

	static final double[] prices =
		{ 19.99, 9.99, 15.99, 3.99, 4.99 };
	static final int[] units =
		{ 12, 8, 13, 29, 50 };
	static final String[] descs =
		{ "Java T-shirt", "Java Mug", "Duke Juggling Dolls", "Java Pin", "Java Key Chain" };

	public static void main(String[] args) throws IOException
	{
		write();
		read();
	}

	static void write() throws IOException
	{
		DataOutputStream out = null;
		try
		{
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("invoice")));
			for (int i = 0; i < prices.length; i++)
			{
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
	}

	static void read() throws IOException
	{
		DataInputStream in = null;
		double price = 0;
		int unit;
		String desc = "";
		while (true)
		{
			try
			{
				in = new DataInputStream(new BufferedInputStream(new FileInputStream("invoice")));
				unit = in.readInt();
				// desc = in.readUTF();
				System.out.format("You ordered %d" + " units of %s at $%.2f%n", unit, desc, price);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (null != in)
					in.close();
			}

		}
	}
}
