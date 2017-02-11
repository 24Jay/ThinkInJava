package basic;

public class TestObject
{

	private int no;

	private String name;

	public TestObject(int n, String name)
	{
		this.no = n;
		this.name = name;
	}

	public String toString()
	{
		return "(ObjectID=" + super.toString() + ", NO=" + no + ", Name=" + name + ")";
	}

	public int getNo()
	{
		return no;
	}

	public void setNo(int no)
	{
		this.no = no;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
