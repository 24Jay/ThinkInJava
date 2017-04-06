package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress
{
	public static void main(String []ra) throws UnknownHostException
	{
		InetAddress  add =  InetAddress.getByName("localhost");
		InetAddress  addd =  InetAddress.getByName("zhangjie");
		InetAddress  taobao =  InetAddress.getByName("taobao.com");
		System.out.println(add);
		System.out.println(addd);
		System.out.println(taobao);
		
	}
}
