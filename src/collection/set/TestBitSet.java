package collection.set;

import java.util.BitSet;
import java.util.Random;

public class TestBitSet
{
	public static void main(String []ar)
	{
		Random random = new Random(47);
		byte bt = (byte)random.nextInt();
		BitSet bs = new BitSet();
		for(int i=15;i>=0;i--)
		{
			if(((1<<i)&bt)!=0)
			{
				bs.set(i);
			}
			else
			{
				bs.clear(i);
			}
		}
		System.out.println(bs);
		printBits(bs);
		
		
		
	}
	
	private static void printBits(BitSet bs)
	{
		System.out.println(bs.size()+"\n");
		for(int i=0;i<bs.size();i++)
		{
			System.out.print((bs.get(i)?1:0)+"");
		}
	}
}
