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

	/**
	 * Firstly, read data from file with channel and buffer ;then, write data to
	 * the buffer,then to the channel(file)
	 * 
	 * @param ar
	 * @throws IOException
	 */
	public static void main(String[] ar) throws IOException
	{
		RandomAccessFile file = new RandomAccessFile("./src/io/data.txt", "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(40);
		System.out.println("Buffer Info: remain=" + buf.remaining() + ", pos=" + buf.position()
				+ ", limit=" + buf.limit());
		buf.limit(15);
		System.out
				.println("Channel Info : size= " + channel.size() + ", pos= " + channel.position());

		/**
		 * channel.read(buf) function will read a sequence of data, and return
		 * the count of these datas. if no data read, return -1.
		 */
		int a = channel.read(buf);

		System.out.println("a === " + a);
		/**
		 * set position to 0 and flip() do the same thing here. During the
		 * previous write procedure, position will increase. Then if you want to
		 * read those datas in the buffer, you have to set position back to the
		 * first:use flip(), shift write to read.
		 */
		// buf.position(0);
		// The flip() switches the buffer from writing mode to reading mode
		buf.flip();
		System.out.println("Buffer Info: remain=" + buf.remaining() + ", pos=" + buf.position()
				+ ", limit=" + buf.limit());
		while (buf.hasRemaining())
		{
			a = buf.get();
			System.out.println("position = " + buf.position() + ", (char)a=" + (char) a);
		}
		System.out.println("Write some bytes back to the buffer!");
		buf.limit(40);
		buf.put("899z hello world\n".getBytes());
		buf.flip();
		while (buf.hasRemaining())
		{
			System.out.println("position=" + buf.position() + ", byte=" + (char) (buf.get()));
		}

		// if you want to write back, first switches to write mode
		buf.flip();
		System.out.println("Write back to channel : " + channel.write(buf));
		/**
		 * clear the buffer
		 */
		// or use buf.compact()
		buf.clear();

		System.out.println("Buffer Info: remain=" + buf.remaining() + ", pos=" + buf.position()
				+ ", limit=" + buf.limit());

		channel.close();
		file.close();
	}
}
