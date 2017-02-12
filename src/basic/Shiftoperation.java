package basic;

public class Shiftoperation
{
	public static void main(String[] ar)
	{
		int a = (1 + Integer.MAX_VALUE) / 2;

		// signed shift right
		int b = (1 + Integer.MAX_VALUE) >> 1;

		// unsigned shift right
		int c = (1 + Integer.MAX_VALUE) >>> 1;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
}
