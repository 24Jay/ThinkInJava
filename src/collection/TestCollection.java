package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class TestCollection
{
	public static void main(String[] ar)
	{
		/**
		 * list可以保存多个相同的对象
		 */
		List<String> list = new ArrayList<String>();
		list.add("touch");
		list.add("pls");
		list.add("color");
		list.add("touch");
		System.out.println("list = " + list);
		/***
		 * set不允许对象重复
		 */
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		System.out.println("set = " + set);

		list = Collections.nCopies(5, new String("Hello"));
		System.out.println("nCopies = " + list);

		/**
		 * 这里必须是ArrayList才能支持fill()操作,List不支持<br>
		 * ArrayList不支持nCopies()操作
		 */
		ArrayList<String> list2 = new ArrayList<String>();
		list2.addAll(list);
		Collections.fill(list2, "zhangjie");
		System.out.println("ArrayList = " + list2);

		/**
		 * 测试ListIterator
		 */
		ListIterator<String> lIterator = list2.listIterator(list2.size());
		while (lIterator.hasPrevious())
		{
			System.out.println(
					"Previous = " + lIterator.previous() + ", previous id=" + lIterator.previousIndex());
		}
		/**
		 * LinkedLIst和ListIterator
		 */
		LinkedList<String> link = new LinkedList<String>();
		link.addAll(list);
		ListIterator<String> lit = link.listIterator(1);
		while (lit.hasNext())
		{
			System.out.println("Next = " + lit.next() + ", next id=" + lit.nextIndex());
		}
		
	}

}
