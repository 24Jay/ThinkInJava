package collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * java.util.LinkedList<br>
 * java.util.PriorityQueue<br>
 * 
 * @author jay
 *
 */
public class TestQueue
{
	public static void main(String[] ar)
	{
		testQueue();
		testDeque();

	}

	public static void testQueue()
	{
		/***
		 * 二者保存element的顺序是不一样的
		 */
		 Queue<String> qa = new PriorityQueue<String>();
//		Queue<String> qa = new LinkedList<String>();
		
		for (int i = 0; i < 15; i++)
		{
			qa.add("Element-"+i);
			System.out.println(qa);
		}
		for (int i = 0; i < 15; i++)
		{
			System.out.println(qa.remove());
		}
	}

	public static void testDeque()
	{
		System.out.println("*******Deque*******");
		/***
		 * Deque
		 */
		Deque<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < 15; i++)
		{
			deque.addFirst(15-i);
			System.out.println(deque);
		}
		for (int i = 0; i < 15; i++)
		{
			deque.removeLast();
			System.out.println(deque);
		}
		System.out.println("*******DequeFromLast*******");
		for (int i = 0; i < 15; i++)
		{
			deque.addLast(i);
			System.out.println(deque);
		}
		for (int i = 0; i < 15; i++)
		{
			deque.removeLast();
			System.out.println(deque);
		}
	}
}
