package basic.clone;

import java.util.Arrays;

import basic.MyObject;

/**
 * 注意区分shallow copy 和deep copy, java中的clone实现的是shallow clone<br>
 * 所知道的实现deep clone的方法有两种:<br>
 * 1, 序列化Serializable,但是这种需要MyObject实现了Serializable接口<br>
 * 2, 将对象序列化为JSON,通过JSON来实现deep copy<br>
 * 
 */
public class TestClone
{
	public static void main(String[] ar)
	{
		MyObject[] a =
			{ new MyObject(1, "Jay"), new MyObject(2, "Miao") };
		System.out.println("Initial a = " + Arrays.toString(a));

		/***
		 * 这里是自己用序列化的方法实现的一个deep clone
		 */
		DeepClone<MyObject> cln = new DeepClone<MyObject>();
		MyObject[] c = cln.deepClone(a);
		System.out.println("Initial c = " + Arrays.toString(c));
		/***
		 * 这里说明了什么叫shallow copy[仅仅是copy了引用的值,没有copy所指向的对象], not a deep copy!<br>
		 * 虽然b是a的一个copy，但是可以看到修改b[0]所指向的对象依然影响到了a<br>
		 */
		MyObject[] b = a.clone();
		b[0].setNo(33233);
		System.out.println("Changed b = " + Arrays.toString(b));
		System.out.println("Changed a = " + Arrays.toString(a));
		System.out.println("Changed c = " + Arrays.toString(c));
	}

}
