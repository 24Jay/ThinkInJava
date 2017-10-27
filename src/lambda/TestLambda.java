package lambda;

import annotation.Foo;

public class TestLambda
{
	private IState s;

	public void addState(IState s)
	{
		s.foo("jj");
	}

	public static void main(String[] ar)
	{
		TestLambda t = new TestLambda();

		t.addState((String ss) -> System.out.println(ss));

		new Thread(() -> System.out.println("the1")).start();
		new Thread(() -> System.out.println("the2")).start();
	}

	
	
}
