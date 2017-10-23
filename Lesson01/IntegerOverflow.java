/*
 * https://en.wikipedia.org/wiki/Integer_overflow
 */

public class IntegerOverflow {
	public static void main(String[] args) {
		int numeroShort_sup = Integer.MAX_VALUE;
		int numeroShort_inf = Integer.MIN_VALUE;

		numeroShort_sup = (int)(numeroShort_sup + 1);

		System.out.println(numeroShort_sup);
		System.out.println(numeroShort_inf);
	}
}
