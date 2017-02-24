package collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * java.util.EnumSet<br>
 * java.util.HashSet<br>
 * java.util.LinkedHashSet<br>
 * java.util.TreeSet<br>
 * 
 * @author jay
 *
 */
public class TestSet
{

	static class OS
	{
		String name;

		public OS(String name)
		{
			this.name = name;
		}

		@Override
		public int hashCode()
		{
			return 1;// super.hashCode();
		}

		@Override
		public boolean equals(Object obj)
		{
			return true;
		}

	}

	public static void main(String[] ar)
	{
		/***
		 * HashSet底层是用HashMap来实现的<>
		 */
		Set<OS> hash = new HashSet<OS>();
		hash.add(new OS("Jay"));
		hash.add(new OS("Miao"));
		hash.add(new OS("Miaommm"));
		System.out.println(hash.size());

		
		/***
		 * 
		 * 如果两个key的hashcode不同,那么它们会在不同的位置上,因此不会冲突<br>
		 * 如果两个key仅仅是hashcode相同的话,但是equals为false,那么它们都会存在于其中的一个链上<br>
		 * 如果两个key的hashcode相同,而且equals为true,那么它们会被看做同一个key,只能存在其中一个<br>
		 */
		Map<OS, String> m = new HashMap<OS, String>();
		m.put(new OS("Jay"), "Jay");
		m.put(new OS("Miao"), "Miao");
		System.out.println(m);
	}

}
