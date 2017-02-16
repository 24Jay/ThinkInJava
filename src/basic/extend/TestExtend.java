package basic.extend;

public class TestExtend
{
	public static void main(String []ar)
	{
		Father son = new Son("This is the son class!");
		System.out.println(son.foo());
//		son.foo2();
		((Son) son).foo2();
	}
	
}
