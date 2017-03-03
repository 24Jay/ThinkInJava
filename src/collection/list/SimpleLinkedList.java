package collection.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleLinkedList<T>
{
	private static class Node<T>
	{
		T data;
		Node<T> next;

		public Node(T t, Node<T> n)
		{
			this.data = t;
			this.next = n;
		}

		public String toString()
		{
			return "(data=" + data + ")";
		}
	}

	private int size = 0;

	/**
	 * 记录list被修改结构的次数
	 */
	private int modCount = 0;

	private Node<T> head;

	public SimpleLinkedList(int size)
	{
		this.size = size;
		clear();
	}

	/**
	 * clear the whole list
	 */
	public void clear()
	{
		head = new Node<T>(null, null);
		this.size = 0;
		modCount++;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	/***
	 * 每次都在最前端插入<br>
	 * 
	 * @param x
	 * @return
	 */
	public boolean add(T x)
	{
		Node<T> t = new Node<T>(x, head.next);
		head.next = t;
		size++;
		modCount++;
		return true;
	}

	/***
	 * id表示第id个元素,head为第0个,依次类推<br>
	 * 
	 * @param id
	 * @return
	 */
	public T get(int id)
	{
		if (id > size)
			return null;
		Node<T> temp;
		for (temp = head; id > 0; temp = temp.next, id--)
		{
		}
		return temp.data;
	}

	public T set(int id, T newValue)
	{
		if (id > size || id < 1)
			return null;
		Node<T> temp;
		for (temp = head; id > 0; temp = temp.next, id--)
		{
		}
		T oldValue = temp.data;
		temp.data = newValue;
		return oldValue;

	}

	public T remove(int id)
	{
		Node<T> temp = head;
		for (; id > 1; temp = temp.next, id--)
		{
		}
		T value = temp.next.data;
		temp.next = temp.next.next;
		size--;
		return value;
	}

	/***
	 * 借助先进先出队列进行reverse操作<br>
	 * 逻辑简单,但是效率不高<br>
	 * 
	 * @return
	 */
	public void reverse_1()
	{
		// Stack<Node<T>> stack = new Stack<Node<T>>();
		Queue<Node<T>> queue = new LinkedList<>();
		Node<T> te = head;
		while (te.next != null)
		{
			te = te.next;
			queue.add(te);
		}
		/**
		 * 清空原来的链表,重新建表<br>
		 */
		clear();
		while (!queue.isEmpty())
		{
			add(queue.poll().data);
		}
	}

	/***
	 * 反转链表 基本思想:逐个节点拆开,建一个新的list<br>
	 */
	public void reverse_2()
	{
		Node<T> newHead = new Node<T>(null, null);
		while (head.next != null)
		{
			Node<T> temp = head.next;
			head.next = head.next.next;
			temp.next = newHead.next;
			newHead.next = temp;
		}
		head = newHead;
	}

	public String toString()
	{
		StringBuilder b = new StringBuilder();
		Node<T> temp = head.next;
		for (int i = 0; i < size; i++, temp = temp.next)
		{
			b.append(temp.toString() + "->");
		}
		b.append("NULL");
		return b.toString();
	}

	public Iterator<T> iterator()
	{
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T>
	{
		Node<T> current = head;

		@Override
		public boolean hasNext()
		{
			return current.next != null;
		}

		@Override
		public T next()
		{
			if (hasNext())
			{
				current = current.next;
				return current.data;
			}
			return null;
		}

	}

	public static void main(String[] ar)
	{
		SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>(10);

		for (int i = 0; i < 10; i++)
		{
			list.add(i);
		}
		System.out.println(list);
		System.out.println("Get an element : (data=" + list.get(6) + ")");
		// System.out.println("Remove an element : (data=" + list.remove(6) +
		// ")");
		System.out.println("Remove an element : (data=" + list.set(6, 110) + ")");
		System.out.println("\nOriginal list : " + list);
		list.reverse_1();
		System.out.println("List after reversed_1 : " + list);
		list.reverse_2();
		System.out.println("List after reversed_2 : " + list);

		/***
		 * Iterator
		 */
		Iterator<Integer> it = list.iterator();
		while (it.hasNext())
			System.out.println("it.next = " + it.next());
	}
}
