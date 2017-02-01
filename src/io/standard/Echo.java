package io.standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Echo
{
	public static void main(String []ar) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s=in.readLine())!=null && s.length()!=0)
		{
			System.out.println(s);
		}
		in.close();
	}
}
