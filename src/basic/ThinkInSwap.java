package basic;

/**
 * Think about java basics
 * 
 * @author jay
 *
 */
public class ThinkInSwap
{

	public static void main(String[] ar)
	{
		swapInt();
		swapString();
		swapObject();

		System.out.println("xxxxxxxxxxxx分割线xxxxxxxxxxx");
		//a, b指向对象实例的首地址
		TestObject a = new TestObject(1, "zhangjie");
		TestObject b = new TestObject(2, "fanmiao");
		System.out.println("1, Object : a=" + a + ", b=" + b);
		//复制一个a,b. 但是依然指向上述对象实例
		swapObject(a, b);
		System.out.println("4, Object : a=" + a + ", b=" + b);
	}

	/**
	 * 
	 * @param a
	 * @param b
	 */
	private static void swapObject(TestObject aa, TestObject bb)
	{
		/**
		 * 这里的a,b是上述定义
		 */
		System.out.println("2, Object : aa=" + aa + ", bb=" + bb);
		/**
		 * 这里对aa的操作同样会对外面的a,b产生影响,因为他们指向相同的对象实例
		 */
		aa.setNo(-1);
		bb.setName("gggg");
		
		/**
		 * 这里交换的是指向的实例
		 */
		TestObject temp = aa;
		aa = bb;
		bb = temp;
		System.out.println("3, Object : aa=" + aa + ", bb=" + bb);
	}

	private static void swapObject()
	{
		TestObject a = new TestObject(1, "zhangjie");
		TestObject b= new TestObject(2, "fanmiao");
		TestObject temp = a;
		a = b;
		b = temp;
		System.out.println("Object : a=" + a + ", b=" + b);
	}

	/**
	 * String虽然是对象,但是与其他的对象不同,String是不可变对象,immutable
	 */
	private static void swapString()
	{
		String a = "Jay", b = "mm";
		String temp = a;
		a = b;
		b = temp;
		System.out.println("String : a=" + a + ", b=" + b);
	}

	private static void swapInt()
	{
		int a = 1, b = 2;
		int temp = a;
		a = b;
		b = temp;
		System.out.println("Int : a=" + a + ", b=" + b);
	}

}

/*class ob
{
	int count;
	String name;

	public ob(int c, String n)
	{
		this.count = c;
		this.name = n;
	}

	public String toString()
	{
		return "[count=" + count + ", name=" + name + "]";
	}
}*/