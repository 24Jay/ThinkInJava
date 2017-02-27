package collection.map;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/***
 * 自己实现的一个简单的HashMap<br>
 * 
 * @author jay
 *
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V>
{
	private Object[] table;
	private int capacity = 997;
	private LinkedList<Entry<K, V>>[] buckets = new LinkedList[capacity];


/*	public SimpleHashMap()
	{
		new SimpleHashMap(capacity);
	}

	public SimpleHashMap(int capa)
	{
		this.capacity = capa;
		table = new Object[capacity];
	}*/

	static class Entry<K, V>
	{
		K key;
		V value;
		Entry next;

		public Entry(K k, V v)
		{
			this.key = k;
			this.value = v;
		}

		

		public K getKey()
		{
			return key;
		}

		public void setKey(K key)
		{
			this.key = key;
		}

		public V getValue()
		{
			return value;
		}

		public void setValue(V value)
		{
			this.value = value;
		}
		
		public String toString()
		{
			return key+"="+value;
		}
	}


	public V put(K key, V value)
	{
		V oldValue = null;
		int index = Math.abs(key.hashCode() % capacity);
		if (buckets[index] == null)
			buckets[index] = new LinkedList<Entry<K, V>>();

		LinkedList<Entry<K, V>> bucket = buckets[index];
		Entry<K, V> pair = new Entry(key, value);
		boolean found = false;
		ListIterator<Entry<K, V>> it = bucket.listIterator();

		while (it.hasNext())
		{
			Entry<K, V> ipair = it.next();
			if (ipair.getKey().equals(key))
			{
				oldValue = ipair.getValue();
				it.set(pair);
				found = true;
				break;
			}
		}
		if (!found)
			buckets[index].add(pair);
		return oldValue;
	}

	public V get(Object key)
	{
		int index = Math.abs(key.hashCode() % capacity);
		if (buckets[index] == null)
			return null;
		for (Entry<K, V> e : buckets[index])
		{
			if (key.equals(e.getKey()))
			{
				return e.getValue();
			}
		}
		return null;
	}

	public static void main(String[] ar)
	{
		SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
		map.put("zhn", 1);
		map.put("jay", 2);
		System.out.println(map.get("zhn"));
		System.out.println(map.get("jay"));
	}
}
