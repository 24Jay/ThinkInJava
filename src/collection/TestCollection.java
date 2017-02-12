package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestCollection
{
	public static void main(String[] ar)
	{
		List<String> list = new ArrayList<String>();
		list.add("touch");
		list.add("pls");
		list.add("color");
		list.add("touch");

		Set<String> set = new HashSet<String>();
		set.addAll(list);
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		
	}
}
