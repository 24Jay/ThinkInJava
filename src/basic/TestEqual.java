package basic;

public class TestEqual
{
	public static void main(String[] ar)
	{
		stringEqual();
		objectEqual();
		cloneEqual();
	}

	private static void cloneEqual()
	{
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx ObjectEqual xxxxxxxxxx");
		int[] a =
			{ 2, 3, 2, 4, 5, 8 };
		int[] b = a.clone();
		if (a != b)
		{
			System.out.println("Clone means different!");
		}
	}

	/**
	 * 当且仅当两个引用指向同一个对象实例的时候，二者相等
	 */
	private static void objectEqual()
	{
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx ObjectEqual xxxxxxxxxx");
		TestObject object = new TestObject(1, "wu");
		TestObject object2 = new TestObject(1, "wu");
		TestObject object3 = object;
		TestObject object4 = object;
		TestObject oo = null;
		
		if (null == oo)
		{
			System.out.println("object is null");
		}

		if (!object.equals(object2))
		{
			System.out.println("object do not equal object2");
		}
		if (object != object2)
		{
			System.out.println("object != object2");
		}
		if (object == object3)
		{
			System.out.println("object == object3");
		}

		/**
		 * 改变object对象的值域
		 */
		object4.setName("zhangjie");
		object4.setNo(110);
		if (object == object3)
		{
			System.out.println("object == object3 == object4" + object);
		}
	}

	private static void stringEqual()
	{
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx StringEqual xxxxxxxxxx");
		/**
		 * 这种写法是可以的，不会抛出异常
		 */
		String s = "hello";
		if ("hello".equals(s))
		{
			System.out.println("No Exception even if s=null");
		}

		/**
		 * 这样也是可以的
		 */
		String ss = "";
		if ("".equals(ss))
		{
			System.out.println("No Exception even if s=null and \"\"");
		}
		if (!"  ".equals(ss))
		{
			System.out.println("\"\" is quite different from \"   \"");
		}
		/**
		 * 这样写是不可以的，一旦s=null就会抛出异常
		 */
		/*
		 * String sss = null; if (sss.equals("hhh")) {
		 * System.out.println("Throw Null Pointer Exception!"); }
		 */
	}

}
