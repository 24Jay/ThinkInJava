package collection;

import java.util.PriorityQueue;

/***
 * 
 * 使用PriorityQueue写一个ToDOList<br>
 * 
 * 1.静态内部类跟静态方法一样，只能访问静态的成员变量和方法，不能访问非静态的方法和属性，<br>
 * 但是普通内部类可以访问任意外部类的成员变量和方法<br>
 * 
 * 2.静态内部类可以声明普通成员变量和方法，而普通内部类不能声明static成员变量和方法。<br>
 * 
 * 3.静态内部类可以单独初始化,但是普通内部类需要用它的外部类实例来初始化<br>
 * 
 * @author jay
 *
 */
public class ToDoList extends PriorityQueue<ToDoList.ToDoItem>
{
	/**
	 * 静态类,static只能出现于内部类.否则编译不通过<br>
	 * 
	 * @author jay
	 *
	 */
	// 如果这里不声明为static,那么下面的静态内部类是不能访问它的<br>
	private static String tag = "My Todolist!";

	public static class ToDoItem implements Comparable<ToDoItem>
	{
		private char primary;
		private int secondary;
		private String item;
		// 如果这边内部类不是static的,那么这里是不能声明为static
		private static String itemTag = "This is a item!";

		public ToDoItem(String td, char pri, int sec)
		{
			this.item = td;
			this.primary = pri;
			this.secondary = sec;
		}

		@Override
		public int compareTo(ToDoItem o)
		{
			if (primary > o.primary)
				return 1;
			if (primary == o.primary)
				if (secondary > o.secondary)
					return 1;
				else if (secondary == o.secondary)
					return 0;
			return -1;
		}

		@Override
		public String toString()
		{
			return Character.toString(primary) + secondary + " : " + item + "--->In " + tag;
		}

	}

	public void add(String id, char pri, int sec)
	{
		ToDoItem it = new ToDoItem(id, pri, sec);
		super.add(it);
	}

	public static void main(String[] ar)
	{
		ToDoList list = new ToDoList();
		list.add("Empty trash", 'C', 4);
		list.add("Feed dog", 'A', 2);
		list.add("Feed bird", 'B', 7);
		list.add("Mow lawn", 'C', 3);
		list.add("Water lawn", 'A', 1);
		list.add("Feed cat", 'B', 1);
		while (!list.isEmpty())
			System.out.println(list.remove());

	}

}
