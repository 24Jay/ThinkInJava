package collection.map;

import java.util.HashMap;

public class ThinkInHashMap
{
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	public static void main(String[] ar)
	{
		HashMap<Integer, String> map = new HashMap<Integer, String>(10);
		System.out.println(map.size());
		thinkInTableSize();
		thinkInHash();

	}

	private static void thinkInHash()
	{
		/**
		 * n为HashMap的capacity<br>
		 */
		int n = 16;
		
		/**
		 * 测试Integer
		 */
		Integer[] keys =
			{ 1, 2, 3, 4, 5, 6, 7, 17, 18, 19, 20, 21 };
		for (Integer key : keys)
		{
			System.out.format("key=%2d\tkey.hashcode=%2d", key, key.hashCode());
			System.out.format("\tkey.hashcodeByHashMap=%2d", hash(key));
			System.out.println("\tLocationCountedByHashcode=" + ((n - 1) & hash(key)));
		}
		System.out.println("从中可以看到,虽然1和17的hashcode不同,但是它们还是映射到了同一个bucket!!!\n\n");

		
		/**
		 * 测试String
		 */
		String[] ks =
			{ "zhangjie", "zhang", "jie","fan","miao" ,"hello"};
		for (String s : ks)
		{
			{
				System.out.format("key=%10s\tkey.hashcode=%d", s, s.hashCode());
				System.out.format("\tkey.hashcodeByHashMap=%s", hash(s));
				System.out.println("\tLocationCountedByHashcode=" + ((n - 1) & hash(s)));
			}
		}

	}

	/**
	 * HashMap的计算某个Key的hashcode值的hash函数<br>
	 * 
	 * @param key
	 * @return
	 */
	static final int hash(Object key)
	{
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	/***
	 * 测试HashMap如何调整它的初始大小,保证capacity一直为2的N次方<br>
	 */
	private static void thinkInTableSize()
	{
		int[] clientSetSize =
			{ 1, 2, 3, 5, 9, 17, 33, 65, 129 };
		for (int i : clientSetSize)
		{
			int actualSize = tableSizeFor(i);
			System.out.println("f(" + i + ")=" + actualSize + "\n");
		}
	}

	/****
	 * 这是HashMap的tableSize初始化函数,依据用户的设置的大小,自动调整为2的n次方<br>
	 * 
	 * @param c
	 * @return
	 */
	private static final int tableSizeFor(int c)
	{
		int n = c - 1;
		n |= n >>> 1;
		System.out.print("n|=n>>1 : " + n + "\t");
		n |= n >>> 2;
		System.out.print("n|=n>>2 : " + n + "\t");
		n |= n >>> 4;
		System.out.print("n|=n>>4 : " + n + "\t");
		n |= n >>> 8;
		System.out.print("n|=n>>8 : " + n + "\t");
		n |= n >>> 16;
		System.out.print("n|=n>>16 : " + n + "\t");
		System.out.println("");
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
}
