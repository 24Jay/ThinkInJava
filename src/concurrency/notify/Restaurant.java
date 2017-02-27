package concurrency.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Restaurant
{
	Meal meal;
	ExecutorService exe = Executors.newCachedThreadPool();
	WaitPerson person = new WaitPerson(this);
	Chef chef = new Chef(this);
	
	public Restaurant()
	{
		exe.execute(chef);
		exe.execute(person);
	}
	
	public static void main(String []ar)
	{
		new Restaurant();
	}
}
