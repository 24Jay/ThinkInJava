package annotation;

import java.lang.reflect.Method;

public class TestPlayer
{
	@Player(id = 110, description = "Kobe Bryant")
	public static void main()
	{
		System.out.println("This is a test annotation!");
	}

	@Player(id = 112, description="Kevin Durant")
	private void tt()
	{

	}

	public static void main(String[] arg)
	{
		// use reflection to get all methods, and use method to get annotations
		Class<?> cl = TestPlayer.class;
		for (Method m : cl.getDeclaredMethods())
		{
			// annotation is one of method's attributes
			Player test = m.getAnnotation(Player.class);
			if (test != null)
			{
				System.out.println("Id = " + test.id() + ", desc = " + test.description());
			}
		}

	}
}
