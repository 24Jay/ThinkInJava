package collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * java.util.HashMap<br>
 * java.util.Hashtable<br>
 * java.util.EnumMap<br>
 * java.util.IdentityHashMap<br>
 * java.util.LinkedHashMap<br>
 * java.util.Properties<br>
 * java.util.TreeMap<br>
 * java.util.WeakHashMap<br>
 * 
 * @author jay
 *
 */
public class TestMap
{
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	public static void main(String[] ar)
	{
		testHashMap();
		testLinkedHashMap();
		testTreeMap();
		thinkInTableSize();
	}

	/***
	 * 
	 * 如果两个key的hashcode不同,那么它们会在不同的位置上,因此不会冲突<br>
	 * 如果两个key仅仅是hashcode相同的话,但是equals为false,那么它们都会存在于其中的一个链上<br>
	 * 如果两个key的hashcode相同,而且equals为true,那么它们会被看做同一个key,只能存在其中一个<br>
	 */
	private static void testHashMap()
	{
		// Map<Integer, String> hash = new HashMap<Integer, String>();
		/**
		 * choose synchronizedMap for structurally modified under mutli-thread
		 */
		Map<Integer, String> hash = Collections.synchronizedMap(new HashMap<Integer, String>());

		String[] s = "HashMap,HashTable,EnumMap,IdentityHashMap,LinkedHashMap".split(",");
		// hash.put(null, null);
		for (int i = 0; i < s.length; i++)
		{
			hash.put(i, s[i]);
			int h;
			/***
			 * This is the method HashMap computing hashcode!
			 */
			System.out.println(((h = s[i].hashCode()) ^ (h >>> 16)));
		}
		hash.put(1, "Java");
		System.out.println(hash.keySet());
		System.out.println(hash.values());
	}

	private static void testLinkedHashMap()
	{
		Map<Integer, String> linked = new LinkedHashMap<Integer, String>();

		String[] s = "HashMap,HashTable,EnumMap,IdentityHashMap,LinkedHashMap".split(",");
		// linked.put(null, null);
		for (int i = 0; i < s.length; i++)
		{
			linked.put(i, s[i]);
			int h;
			/***
			 * This is the method HashMap computing hashcode!
			 */
			System.out.println(((h = s[i].hashCode()) ^ (h >>> 16)));
		}
		linked.put(1, "Java");
		System.out.println(linked);
	}

	private static void thinkInTableSize()
	{

		System.out.println(MAXIMUM_CAPACITY);

		for (int i = 1; i < 100; i++)
		{
			System.out.println("\n" + i + "=" + tableSizeFor(i));
		}
	}

	private static final int tableSizeFor(int c)
	{
		int n = c - 1;
		System.out.println("n=" + n);
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

	/**
	 * 实现的是SortedMap,保证Key处于排序状态
	 */
	private static void testTreeMap()
	{

	}
}
