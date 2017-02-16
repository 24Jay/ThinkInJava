package basic.extend;

public class Father
{
	String s = "This is a father class!";

	public Father(String a)
	{
		this.s = a;
	}

	public String foo()
	{
		System.out.println(s + "from father class!");
		return s + "from father class!";
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	
}
