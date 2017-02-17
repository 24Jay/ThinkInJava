package collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.DelayQueue;

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
		testDeque();
		testDeque();

	}

	public static void testQueue()
	{
		/***
		 * 二者保存element的顺序是不一样的
		 */
		// Queue<String> qa = new PriorityQueue<String>();
		Queue<String> qa = new LinkedList<String>();
		qa.add("Element 1");
		qa.add("Element 2");
		qa.add("Element 3");
		qa.add("Element 2");
		System.out.println(qa.peek());
		System.out.println(qa.peek());
		System.out.println(qa.peek());
		System.out.println(qa.peek());

		System.out.println("******QueueRemove********");
		System.out.println(qa.remove());
		System.out.println(qa.remove());
		System.out.println(qa.remove());
		System.out.println(qa.remove());
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
			deque.addFirst(i);
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
