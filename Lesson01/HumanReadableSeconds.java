/* 
 * This program allows to print the secons into a human readable format. This
 * means that each second must show the number of years, days, hours, minutes and
 * seconds it corresponds. the following output format must be used:
 * 
 * 0y 2d 3h 1m 20s
 */

import java.util.Scanner; 

public class HumanReadableSeconds {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double inSecs = in.nextDouble();
		double years = inSecs / (60*60*24*365);
		double days = (years - (int)years) * 365;
		double hours = (days - ((int)days)) * 24;
		double mins = (hours - ((int)hours)) * 60;
		double secs = (mins - ((int)mins)) * 60;
		System.out.println(((int)years)+"y " + ((int)days)+"d "+((int)hours)+"h " + ((int)mins) +"m "+ ((int)secs) + "s");
	}
}
