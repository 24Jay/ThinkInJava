package annotation;

import java.lang.reflect.Modifier;

public class Reflection
{
	public static void main(String[] ar) throws InstantiationException, IllegalAccessException
	{
		RunPlayer r = new RunPlayer();
		Class<RunPlayer> cl = (Class<RunPlayer>) r.getClass();
		System.out.println("Class = " + "fff".getClass() + ", cl=" + cl.getName());
		System.out.println(Modifier.toString(cl.getModifiers()));
		System.out.println(Modifier.toString(Modifier.ABSTRACT));

	}
}
