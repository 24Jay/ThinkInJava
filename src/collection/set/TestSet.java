package collection.set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
			return name.hashCode();// super.hashCode();
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
		 * HashSet底层是用HashMap来实现的<br>
		 */

		/**
		 * 如果hashcode不同,但是依然有可能映射到HashSet中<br>
		 * 如果hashcode相同,但是equals为false,那么它们依然是都可以存在于同一个HashSet中的<br>
		 * 如果hashcode不同,但是equals为true,那么它们也依然是可以存在于同一个HashSet中的<br>
		 * 如果hashcode相同,而且equals为true,那么就只有一个能够存在与HashSet中<br>
		 */
		Set<OS> hash = new HashSet<OS>();
		hash.add(new OS("Jay"));
		hash.add(new OS("Miao"));
		hash.add(new OS("Miaommm"));
		System.out.println(hash.size());
		
		LinkedHashSet<String> linked = new LinkedHashSet<>();
		TreeSet<String> tree = new TreeSet<>();

		/***
		 * 
		 * 如果两个key的hashcode不同,那么它们会在不同的位置上,因此不会冲突;但是依然有可能映射到同一个slot<br>
		 * 如果两个key仅仅是hashcode相同的话,但是equals为false,那么它们都会存在于其中的一个链上<br>
		 * 如果两个key的hashcode相同,而且equals为true,那么它们会被看做同一个key,只能存在其中一个<br>
		 */
		Map<OS, String> m = new HashMap<OS, String>();
		m.put(new OS("Jay"), "Jay");
		m.put(new OS("Miao"), "Miao");
		System.out.println(m);
	}

}
