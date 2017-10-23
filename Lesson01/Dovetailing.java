/*
 * Write a program that associates to a pair of integers (i,j) an unique integer 
 * c, such that (i,j) -> c is a bijection.
 *
 * In order to do that, we can use the dovetailing function:
 * dt(i,j) = (i+j)*(i+j+1) / 2 + j;
 * 
 * Then, we want to make sure that the resulting number is uniquely associated to 
 * (i,j). Then, we can ask ourselves if there is an inverse function. Such inverse
 * function is:
 * 
 * right = c - ((√(c*8+1)-1)/2 + 1) * ((√(c*8+1)-1)/2) / 2;
 * left = ((√(c*8+1)-1)/2) - right;
 */

import java.util.Scanner;

public class Dovetailing {

	public static void main(String args[]) {
		int i, j;
		Scanner scan = new Scanner(System.in);
		System.out.println("Provide the left integer. i = ");
		i = scan.nextInt();
		System.out.println("Provide the right integer. j = ");
		j = scan.nextInt();

		// dovetailing function
		int ij = i + j;
		int ij1 = ij + 1;
		int result = (ij * ij1) / 2+j;

		// inverse function
		int dmax = ((((int)Math.sqrt(result*8+1))-1)) / 2;
		int smax = (dmax + 1) * dmax / 2;
		int right = result - smax;
		int left = dmax - right;

		// test
		boolean testLeft = i == left;
		boolean testRight = j == right; 
		boolean wholetest = testLeft && testRight;
		String resultString = (wholetest ? "correct" : "wrong");
		System.out.println("The result is " + resultString);
	}
}

