package basic.extend;

public class Son extends Father
{

	public Son(String a)
	{
		super(a);
	}
	
	public String foo()
	{
		super.foo();
		return this.s+"from son class";
	}
	
	public void foo2()
	{
		System.out.println("foo2() in son class");
	}
	
}
