package basic;

public class TestByteInteger
{
	public static void main(String[] ar)
	{
		byte a = 127;
		System.out.println(a);
		a = (byte) (a + 1);
		System.out.println(a);
		a = (byte) 128;
		System.out.println(a);

		Integer m = 100;
		Integer n = 100;
		System.out.println(m == new Integer(100));
		System.out.println(n == m);

		Integer mm = 129;
		Integer nn = 129;
		System.out.println(mm == new Integer(129));

		System.out.println(nn == mm);
	}
}
