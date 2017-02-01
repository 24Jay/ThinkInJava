package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java NIO Channels are similar to streams with a few differences:
 * 
 * You can both read and write to a Channels. Streams are typically one-way
 * (read or write). Channels can be read and written asynchronously. Channels
 * always read to, or write from, a Buffer.
 * 
 * 
 *
 * 
 * @author jay Reference: http://tutorials.jenkov.com/java-nio/channels.html
 */
public class BasicChannelExample
{

	public static void main(String[] ar) throws IOException
	{
		RandomAccessFile file = new RandomAccessFile("./src/io/BasicChannelExample.java", "rw");
		FileChannel channel = file.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(20);
		System.out.println("Buffer Info remain= " + buf.remaining());
		System.out.println("Buffer Info pos= " + buf.position());
		

		System.out.println("Channel Info size= " + channel.size());
		System.out.println("Channel Info pos= " + channel.position());
		int a;

		try
		{
			while ((a = channel.read(buf)) != -1)
			{
				System.out.println(a);
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx");
				buf.flip();
				System.out.println("Buffer Info remain= " + buf.remaining());
				System.out.println("Buffer Info pos= " + buf.position());

				while (buf.hasRemaining())
				{
					System.out.println(buf.get());
				}
				System.out.println("YYYYYYYYYYYYYYYYYYYYYYYY");
				System.out.println("Buffer Info remain= " + buf.remaining());
				System.out.println("Buffer Info pos= " + buf.position());
				buf.clear();
//				break;
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
				channel.close();
				file.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
