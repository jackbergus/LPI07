import java.util.*;

/* A year with 366 days is said to be a leap. Following the adoption of the Gregorian calendar (1582),
 * in order to determine if a year is a leap, you can follow these steps:
 * 1) If the year can be divided by four, see 2) and 5) otherwise
 * 2) If the year can be divided by one hundred, see 3) and 4) otherwise
 * 3) If the year can be divided by four hundred, see 4) and 5) otherwise
 * 4) return true
 * 5) return false
 *
 * Solution of the same exercise with the usage of if..then..else clauses
 */

class IfLeapYearTest {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Insert a year following 1582: ");
        int year = in.nextInt();
	boolean isLeap = false;
	int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	// Do some checks
	if (year > 1582) {
		// 1) If the year can be divided by four...
		if (year % 4 == 0) {
			// 2) If the year can be divided by one hundred...
			if (year % 100 == 0) {
				// 3) If the year can be divided by four hundred...
				isLeap = (year % 400 == 0);
			} else {
				isLeap = true;
			}
		}
	}

	String verb = year < currentYear ? "\nQ: Was " : (year == currentYear ? "Is " : "Will ");
	String forModal = year > currentYear ? " be" : "";
	String reply = isLeap ? "Yes." : "No.";

        System.out.println(verb + year + forModal +" a leap year? \nA: " + reply);
    }
}
