/*
 * https://en.wikipedia.org/wiki/Integer_overflow
 */

public class ByteOverflow {
	public static void main(String[] args) {
		byte numeroShort_sup = 127;
		byte numeroShort_inf = -128;
		
		numeroShort_sup = (byte)(numeroShort_sup + 1);

		System.out.println(numeroShort_sup);
		System.out.println(numeroShort_inf);
	}
}
