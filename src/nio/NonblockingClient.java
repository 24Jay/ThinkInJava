package nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import com.sun.security.ntlm.Client;
import sun.security.krb5.internal.HostAddress;

public class NonblockingClient
{

	static class Client implements Runnable
	{

		private String host;

		private int port;

		private Selector selector;

		private SocketChannel sc;

		private volatile boolean stop;

		public Client()
		{
			this.host = host == null ? "127.0.0.1" : host;

			this.port = 9999;

			try
			{
				this.selector = Selector.open();

				this.sc = SocketChannel.open();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}

		@Override
		public String toString()
		{
			// TODO Auto-generated method stub
			return super.toString();
		}

		@Override
		public void run()
		{
			// TODO Auto-generated method stub

		}

	}

	public static void main(String []ar)
	{
	}

}
