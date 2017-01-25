package annotation;

import java.lang.reflect.Method;

public class TestPlayer
{
	@Player(id = 24, name = "Kobe Bryant")
	public static void main()
	{
		System.out.println("This is a test annotation!");
	}

	@Player(id = 23, name = "Lebron James")
	public void cl()
	{

	}

	@Player(id = 35, name = "Kevin Durant")
	private void tt()
	{

	}

	public static void main(String[] arg)
	{
		// use reflection to get all methods, and use method to get annotations
		// Class<?> cl = TestPlayer.class;
		for (Method m : TestPlayer.class.getDeclaredMethods())
		{
			// annotation is one of method's attributes
			Player test = m.getAnnotation(Player.class);
			if (test != null)
			{
				System.out.println("Id = " + test.id() + ", desc = " + test.name());
			}
		}

	}
}
