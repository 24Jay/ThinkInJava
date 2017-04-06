package network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocktetTest
{
	public static void main(String[] ar) throws UnknownHostException
	{
		Socket socket = null;
		
		try
		{
			socket = new Socket("127.0.0.1", 31544);
//			socket = new Socket("182.92.155.48", 31543);
			OutputStream out = socket.getOutputStream();
			out.write("HelloServer!".getBytes());
			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
