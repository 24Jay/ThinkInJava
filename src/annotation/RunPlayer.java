package annotation;

import java.lang.reflect.Method;

@Player(id=00,name="This is a class for basketball players.")
public class RunPlayer
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
		for (Method m : RunPlayer.class.getDeclaredMethods())
		{
			// annotation is one of method's attributes
			Player test = m.getAnnotation(Player.class);
			if (test != null)
			{
				System.out.println("Id = " + test.id() + ", name = " + test.name());
			}
		}

		Player player = RunPlayer.class.getAnnotation(Player.class);
		System.out.println("Id="+player.id()+", Name="+player.name());
	}
	
}
