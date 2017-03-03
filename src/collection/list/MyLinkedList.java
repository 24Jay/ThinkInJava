package collection.list;

/***
 * 自己实现一个LinkedList
 * 
 * @author jay
 *
 */
public class MyLinkedList<T>
{
	private static class Node<T>
	{
		T data;
		Node<T> pre;
		Node<T> next;

		public Node(T t, Node<T> p, Node<T> n)
		{
			this.data = t;
			this.pre = p;
			this.next = n;
		}

		public String toString()
		{
			return "(data=" + data + ")"; 
		}
	}

	private int size = 0;

	private int modCount = 0;

	private Node<T> head;

	private Node<T> tail;

	public MyLinkedList(int size)
	{
		this.size = size;
		clear();
	}

	/**
	 * clear the whole list
	 */
	public void clear()
	{
		head = new Node<T>(null, null, null);
		tail = new Node<T>(null, head, null);
		head.next = tail;

		this.size = 0;
		modCount++;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public boolean add(T x)
	{
		Node<T> t = new Node<T>(x, tail.pre, tail);
		tail.pre.next = t;
		tail.pre = t;
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
		// if (id < size / 2)
		Node<T> temp;
		for (temp = head; id > 0; temp = temp.next, id--)
		{
		}
		return temp.data;
	}

	public String toString()
	{
		StringBuilder b = new StringBuilder();
		StringBuffer buffer = new StringBuffer();
		Node<T> temp = head.next;
		for (int i = 0; i < size; i++, temp = temp.next)
		{
			b.append(temp.toString() + " ");
		}

		return b.toString();
	}

	public static void main(String[] ar)
	{
		MyLinkedList<Integer> list = new MyLinkedList<>(10);

		for (int i = 0; i < 10; i++)
		{
			list.add(i);
		}
		System.out.println(list);
		System.out.println(list.get(6));
	}
}
