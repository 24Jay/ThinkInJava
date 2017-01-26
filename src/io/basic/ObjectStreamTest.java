package io.basic;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamTest
{

	public static void main(String[] args) throws IOException
	{
		write();
		read();
	}

	static void write() throws IOException
	{
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("obj")));
			A a = new A(new B(4));
			out.writeObject(a);
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

	static void read()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("obj"));
			A a = (A) in.readObject();
			System.out.println("A = " + a);
			a.print();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
}

class A implements Serializable
{
	String desc = "This is class A";
	B b;

	public A(B b)
	{
		this.b = b;
	}

	public void print()
	{
		System.out.println("A desc = " + desc);
		System.out.println("B = " + b.toString());

	}
}

class B implements Serializable
{
	String desc = "This is object B";
	int t;
	
	B(int t)
	{
		this.t= t;
	}

	@Override
	public String toString()
	{
		return desc+", NO."+t;
	}
}