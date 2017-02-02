package network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest
{
	public static void main(String[] ar)
	{
		ServerSocket server = null;
		try
		{
			server = new ServerSocket(31544);
			boolean isStoped = false;
			while (!isStoped)
			{
				Socket socket = server.accept();
				InputStreamReader reader = new InputStreamReader(socket.getInputStream());
				int s;
				while ((s = reader.read()) != -1)
					System.out.println("ServerSOcket : "+(char)s);
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
				server.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
