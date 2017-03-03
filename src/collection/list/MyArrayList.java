package collection.list;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T>
{
	private final static int DEFAULT_CAPACITY = 10;

	private int size;

	private int capacity;

	/***
	 * 这里不能使用泛型数组,因为无法new
	 */
	private Object[] elements;

	public MyArrayList(int cap)
	{
		this.capacity = cap;
		elements = new Object[capacity];
		this.size = 0;
	}

	public MyArrayList()
	{
		this(DEFAULT_CAPACITY);
	}

	private void clear()
	{
		this.size = DEFAULT_CAPACITY;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	private void ensureCapacity(int cap)
	{
		if (cap < capacity)
			return;
		elements = Arrays.copyOf(elements, cap);
	}

	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public void trimToSize()
	{
		ensureCapacity(size);
	}

	public T get(int id)
	{
		if (id > size)
			throw new ArrayIndexOutOfBoundsException();
		return (T) elements[id];
	}

	public boolean add(T x)
	{
		add(size, x);
		return true;
	}

	/***
	 * 在某个指定位置添加一个元素
	 * 
	 * @param location
	 * @param x
	 */
	public void add(int location, T x)
	{
		if (location == size)
		{
			int oldSize = size;
			int cap = oldSize + (oldSize >> 1);
			ensureCapacity(cap);
			int i = oldSize;
			for (; i > location; i--)
			{
				elements[i] = elements[i - 1];
			}
			elements[i] = x;
			size++;
		}
	}

	public T set(int id, T newValue)
	{
		if (id >= size || id < 0)
			throw new IndexOutOfBoundsException();
		T old = (T) elements[id];
		elements[id] = newValue;
		return old;
	}

	public T remove(int id)
	{
		if (id >= size || id < 0)
			throw new IndexOutOfBoundsException();
		T old = (T) elements[id];
		for (int i = id; i < size - 1; i++)
			elements[i] = elements[i + 1];
		size--;
		return old;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new MyIterator();
	}

	private class MyIterator implements Iterator<T>
	{
		int current = 0;

		@Override
		public boolean hasNext()
		{
			return current < size;
		}

		@Override
		public T next()
		{
			if (!hasNext())
				throw new IndexOutOfBoundsException();
			return (T) elements[current++];
		}

		@Override
		public void remove()
		{
			MyArrayList.this.remove(--current);
		}

	}

	public static void main(String[] ar)
	{
		int[] a =
			{ 1, 2, 3 };
		int[] b = Arrays.copyOf(a, 50);
		int[] c = Arrays.copyOf(b, 2);
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.toString(c));

		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 0; i < 20; i++)
			list.add(i);
		list.set(10, 8888);
		Iterator<Integer> it = list.iterator();
		while (it.hasNext())
		{
			Integer v = it.next();
			if (v < 8)
				it.remove();
		}
		it = list.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}
