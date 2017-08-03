package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.sun.xml.internal.ws.developer.StreamingAttachment;

public class NonblockingServer
{

	static class MultipleTimeServer implements Runnable
	{

		private Selector selector;

		private ServerSocketChannel serverChannel;

		private volatile boolean stop = false;

		public MultipleTimeServer()
		{
			try
			{
				selector = Selector.open();
				serverChannel = ServerSocketChannel.open();
				serverChannel.configureBlocking(false);
				serverChannel.socket().bind(new InetSocketAddress("localhost", 9999));
				serverChannel.register(selector, SelectionKey.OP_ACCEPT);

				System.out.println("Time server is started in port 9999!");
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.exit(1);
			}

		}

		@Override
		public void run()
		{

			while (!stop)
			{
				try
				{
					selector.select(1000);
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> it = keys.iterator();
					SelectionKey key = null;

					while (it.hasNext())
					{
						key = it.next();
						it.remove();
						handleInput(key);

					}

				}
				catch (Exception e)
				{
					if (selector != null)
					{
						try
						{
							selector.close();
						}
						catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}
				}
			}
		}

		private void handleInput(SelectionKey key) throws IOException
		{
			if (key.isValid())
			{
				if (key.isAcceptable())
				{
					ServerSocketChannel sc = (ServerSocketChannel) key.channel();
					SocketChannel ch = sc.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
				}

				if (key.isReadable())
				{
					SocketChannel sc = (SocketChannel) key.channel();
					ByteBuffer bf = ByteBuffer.allocate(1024);
					int readBytes = sc.read(bf);
					if (readBytes > 0)
					{
						bf.flip();
						byte[] bytes = new byte[bf.remaining()];
						bf.get(bytes);
						String body = new String(bytes, "UTF-8");
						System.out.println("The time server recieve order: " + body);

						String currentTime = "Query Time Order".equalsIgnoreCase(body)
								? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
						System.out.println(currentTime);
					}

				}
			}
		}

		public void stop()
		{
			this.stop = true;
		}

	}

	public static void main(String[] ar) throws IOException
	{

		MultipleTimeServer ts = new MultipleTimeServer();
		new Thread(ts, "NIO-MultipleTimeServer-001").start();

	}

}
