package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class TestList
{
	public static void main(String[] ar)
	{
		List listA = new ArrayList();
		List listB = new LinkedList();
		List listC = new Vector();
		List listD = new Stack();

		listA.add("element 0");
		listA.add("element 1");
		listA.add("element 2");

		// access via index
		String element0 = (String) listA.get(0);
		String element1 = (String) listA.get(1);
		String element3 = (String) listA.get(2);

		// access via Iterator
		Iterator iterator = listA.iterator();
		while (iterator.hasNext())
		{
			String element = (String) iterator.next();
		}

		// access via new for-loop
		for (Object object : listA)
		{
			String element = (String) object;
		}
	}
}
