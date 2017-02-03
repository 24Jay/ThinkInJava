package io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * A Java NIO Pipe is a one-way data connection between two threads. A Pipe has
 * a source channel and a sink channel. You write data to the sink channel. This
 * data can then be read from the source channel.
 * 
 * Ref : also:http://tutorials.jenkov.com/java-nio/pipe.html
 *
 */
public class PipeTest
{

	public static void main(String[] ar) throws IOException
	{
		newPipeTest();
		pipeExample();
	}

	private static void newPipeTest() throws IOException
	{
		String s = "This is a sink pipe! This string is going to be write to the sink. "
				+ System.currentTimeMillis();

		// create a pipe
		Pipe pp = Pipe.open();

		// get the sink pipe for writing
		final Pipe.SinkChannel sink = pp.sink();

		// get the source for reading
		final Pipe.SourceChannel source = pp.source();

		// create a thread to do the writing
		Thread write = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				ByteBuffer buf = ByteBuffer.allocate(500);
				buf.put(s.getBytes());
				buf.flip();

				while (buf.hasRemaining())
				{
					try
					{
						sink.write(buf);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					finally
					{
						try
						{
							sink.close();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		});

		// create another thread to do the reading
		Thread read = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				ByteBuffer bb = ByteBuffer.allocate(500);
				try
				{
					int b = source.read(bb);
					System.out.println("Bytes : " + b);
					bb.flip();
					while (bb.hasRemaining())
						System.out.print((char) bb.get());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						source.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}

			}
		});

		write.start();
		read.start();
	}

	private static void pipeExample() throws IOException
	{
		System.out.println("*********************************");

		final PipedOutputStream out = new PipedOutputStream();
		final PipedInputStream in = new PipedInputStream(out);

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					out.write("This is another pipe test with PipeStream!".getBytes());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						out.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					int d;
					System.out.println("\n\n");
					while ((d = in.read()) != -1)
						System.out.print((char) d);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						in.close();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

}
