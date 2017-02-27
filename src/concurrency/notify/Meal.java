package concurrency.notify;

public class Meal
{
	private final int orderNum;

	public Meal(int i)
	{
		this.orderNum = i;
	}

	public String toString()
	{
		return "Meal " + orderNum;
	}
}
