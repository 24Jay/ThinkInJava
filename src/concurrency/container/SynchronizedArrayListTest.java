package concurrency.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedArrayListTest extends ListTest
{

	public SynchronizedArrayListTest(String id, int nR, int nW)
	{
		super("SynchronizedArrayListTest", nR, nW);
	}

	public SynchronizedArrayListTest(int nR, int nW)
	{
		super("SynchronizedArrayListTest", nR, nW);
	}

	@Override
	List<Integer> containerInitializer()
	{
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 33; i++)
			list.add(i);
		return Collections.synchronizedList(list);//(new ArrayList<Integer>(containerSize));
	}

}
