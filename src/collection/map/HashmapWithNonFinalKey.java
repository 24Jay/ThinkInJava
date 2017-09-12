package collection.map;

import java.util.HashMap;

public class HashmapWithNonFinalKey
{
	public static void main(String[] ar)
	{
		System.out.println("测试不可变类作为key:");
		HashMap<NonFinalKey, Integer> hp = new HashMap<>();
		NonFinalKey key = new NonFinalKey(1, 2);
		System.out.println("key.hascode()=" + key.hashCode());
		hp.put(key, new Integer(100));
		System.out.println("value for the first time= " + hp.get(key));
		System.out.println("v=" + hp.values());
		key.setA(2);
		System.out.println("key.hascode()=" + key.hashCode());
		System.out.println("value for the second time= " + hp.get(key));
		System.out.println("v=" + hp.values());
		System.out.println(
				"key的hashcode变了，因此虽然之前的value依然还在map中，"
				+ "但是不能通过这个key找到对应的value了。这也解释了HashMap中的序列化方法中的设计！");

	}

}

/****
 * 测试使用可变对象作为HashMap的key会出现什么样的问题<br>
 * 
 * @author jay
 *
 */
class NonFinalKey
{
	private int a;

	private int b;

	public NonFinalKey(int a, int b)
	{
		this.a = a;
		this.b = b;
	}

	public int getA()
	{
		return a;
	}

	public void setA(int a)
	{
		this.a = a;
	}

	public int getB()
	{
		return b;
	}

	public void setB(int b)
	{
		this.b = b;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonFinalKey other = (NonFinalKey) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}

}
