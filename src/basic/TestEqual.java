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
		int[] a = { 2, 3, 2, 4, 5, 8 };
		int[] b = a.clone();
		if (a != b)
		{
			System.out.println("Clone means different!");
		}
	}

	
	/**
	 * 这是Object的equals方法，从这里可以清楚的看到它和=的区别
	 * 
	 *  public boolean equals(Object obj) {
        	return (this == obj);
     *  }
	 */
	
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
		if (object == object3 && object.equals(object3))
		{
			System.out.println("object == object3 and object.equals(object3)");
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

	
	/***
	 *  这里是String对象的equals()方法，与Object的equals方法对比来看，就十分清楚了
	 *  
	 *  
	 *  
	 *  public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }

	 */
	@SuppressWarnings("null")
	private static void stringEqual()
	{
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx StringEqual xxxxxxxxxx");
		/**
		 * 这种写法是可以的，不会抛出异常
		 */
		String s = null;
		if (!"hello".equals(s))
		{
			System.out.println("No Exception even if s=null");
		}
		/**
		 * 这种写法会抛出异常
		 */
		try
		{
			if (s.equals("hhh"));
		}
		catch (Exception e)
		{
			System.out.println("Throw Null Pointer Exception!");
		}

		/**
		 * 这样也是可以的
		 */
		String ss = new String("");
		String sd = new String("");

		if (sd == ss && sd.equals(ss))
		{
			System.out.println("== and equals are the same　for string");
		}
		if (!"  ".equals(ss))
		{
			System.out.println("\"\" is quite different from \"   \"");
		}
		
		String name1= "zhangjie";
		String name2= "zhangjie";
		String name3= new String("zhangjie");
		if(name1 == name2)
		{
			System.out.println("name1==name2");
		}
		if(name1!=name3 && name1.equals(name3))
		{
			System.out.println("name != name3 but name1.equals(name3)");
		}
		
		System.out.println("ttttt : "+("zh"=="zh"));
	}

}
