package collection.map;

/***
 * 自己实现的一个简单的HashMap<br>
 * 
 * @author jay
 *
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V>
{
	private Entry[] table;
	private static int capacity = 16;

	public MyHashMap()
	{
		this(capacity);
	}

	public MyHashMap(int capa)
	{
		table = new Entry[16];
	}

	static class Entry<K, V>
	{
		K key;
		V value;
		Entry next;

		public Entry(K k, V v, Entry ne)
		{
			this.key = k;
			this.value = v;
			this.next = ne;
		}

		public Entry(K k, V v)
		{
			this.key = k;
			this.value = v;
			this.next = null;

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
			if (next == null)
				return key + "=" + value;
			else
				return key + "=" + value + " -> " + next.toString();
		}

	}

	static final int hash(Object key)
	{
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	public V put(K key, V value)
	{
		V oldValue = null;
		int index = (capacity - 1) & hash(key);
		// int index = Math.abs(key.hashCode() % capacity);
		if (table[index] == null)
			table[index] = new Entry<K, V>(key, value);

		Entry<K, V> pair = new Entry(key, value);
		boolean found = false;

		for (Entry e = (Entry) table[index]; e != null; e = e.next)
		{
			Entry<K, V> ipair = e;
			if (ipair.getKey().equals(key))
			{
				oldValue = ipair.getValue();
				e.setValue(value);
				found = true;
				break;
			}
		}

		if (!found)
		{
			Entry entry = (Entry) table[index];
			pair.next = entry;
			table[index] = pair;
		}
		return oldValue;
	}

	public V get(Object key)
	{
		int index = Math.abs(key.hashCode() % capacity);
		if (table[index] == null)
			return null;

		for (Entry e = (Entry) table[index]; e != null; e = e.next)
		{
			if (key.equals(e.getKey()))
				return (V) e.getValue();
		}

		return null;
	}

	public String toString()
	{
		if (table == null || table.length == 0)
			return null;

		StringBuilder builder = new StringBuilder("{\n");
		for (int i = 0; i < table.length; i++)
		{
			Entry e = table[i];
			if (e != null)
			{
				builder.append(e.toString() + "\n");

			}
			// for (Entry e = table[i]; e != null; e = e.next)
		}

		builder.append("}");
		return builder.toString();
	}

	public static void main(String[] ar)
	{
		MyHashMap<Integer, String> map = new MyHashMap<Integer, String>();
		map.put(1, "zhangjie");
		map.put(2, "fanmiao");
		map.put(19, "zhangjie");
		map.put(18, "fanmiao");
		for (int i = 1; i < 96; i++)
			map.put(i, "zhangjie_" + i);
		System.out.println(map.toString());
	}
}
