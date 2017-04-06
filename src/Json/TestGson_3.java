package Json;

import java.net.CookieHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 字段过滤的几种方法<br>
 * 基于＠Expose注解<br>
 * 基于版本<br>
 * 基于访问修饰符号<br>
 * 基于策略<br>
 * ＰＯＪＯ与JSON字段映射规则<br>
 * 
 * @author jay
 *
 */
public class TestGson_3
{
	public static void main(String[] ar)
	{
//		Cookie cookie = new Cookie
		Object o = new Object();
		System.out.println(o);
		System.out.println(o.hashCode());
		
		int[] a = {1,2,3};
		int[] b= a.clone();
		System.out.println(a.equals(b));
		System.out.println(a==b);
		System.out.println(a.getClass()==b.getClass());
	}
}
