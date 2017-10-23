public class DoubleUnderflow {
	public static void main(String[] args) {
		double d = 1e-305 * Math.PI;
 		System.out.print("gradual underflow: " + d + "\n      ");
 		System.out.print(" " + (d /= 100000));
		System.out.print(" " + (d /= 100000));
		System.out.print(" " + (d /= 100000));
		System.out.print(" " + (d /= 100000));
	}
}
