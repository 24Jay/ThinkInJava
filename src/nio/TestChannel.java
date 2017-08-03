package nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TestChannel
{
	public static void main(String []ar) throws FileNotFoundException
	{
		RandomAccessFile file = new RandomAccessFile("./TestChannel.java","rw");
		FileChannel channel = file.getChannel();
		
	}
}
