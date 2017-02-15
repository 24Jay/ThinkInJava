package basic;

import java.io.Serializable;

public class MyObject implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int no;

	private String name;

	public MyObject(int n, String name)
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
