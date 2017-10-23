import java.util.Scanner;

/* A year with 366 days is said to be a leap. Following the adoption of the Gregorian calendar (1582),
 * in order to determine if a year is a leap, you can follow these steps:
 * 1) If the year can be divided by four, see 2) and 5) otherwise
 * 2) If the year can be divided by one hundred, see 3) and 4) otherwise
 * 3) If the year can be divided by four hundred, see 4) and 5) otherwise
 * 4) return true
 * 5) return false
 *
 * Solve this exercise without using any if-then-else statement.
 *
 * if we use a truth table, we can achieve the following result:
 *
 * x %4 == 0     x % 100 == 0       x % 400 == 0 |    result
 * -----------------------------------------------------------
 *     false       false                false    |     false
 *     true        false                false    |     true
 *     true        true                 true     |     true
 *     true        true                 false    |     false
 *
 * By using this table, we cannot see any pattern. By extending such table by using the
 * mathematical consequences of "being divisible by", we can achieve the following result
 *
 * x %4 == 0     x % 100 == 0       x % 400 == 0 |    result
 * -----------------------------------------------------------
 *     false        false                false   |     false
 *     false        false                true    |     true
 *     false        true                 false   |     false
 *     false        true                 true    |     true
 *     true         false                false   |     true
 *     true         false                true    |     true
 *     true         true                 false   |     false
 *     true         true                 true    |     true
 *
 * By sorting the rows by result, we have that:
 * x %4 == 0     x % 100 == 0       x % 400 == 0 |    result
 * -----------------------------------------------------------
 *     false        false                false   |     false
 *     false        true                 false   |     false
 *     true         true                 false   |     false
 *     false        true                 true    |     true
 *     true         false                true    |     true
 *     true         false                false   |     true
 *     false        false                true    |     true
 *     true         true                 true    |     true
 *
 * The pattern can be better seen if we replace the booleans with numbers
 *
 * x %4 == 0     x % 100 == 0       x % 400 == 0 |    result
 * -----------------------------------------------------------
 *     0            0                   0   |     0
 *     0            1                   0   |     0
 *     1            1                   0   |     0
 *     0            0                   1   |     1
 *     0            1                   1   |     1
 *     1            0                   0   |     1
 *     1            0                   1   |     1
 *     1            1                   1   |     1
 *
 * We can see the following pattern:
 *  1) if year can be divided by four (but not by 100) then it is a leap year
 *  2) when the year can be divided by 400, it is always a leap year
 * hereby, we can create the following formula:
 *
 * isLeap = (( x % 4 == 0) && (! (x % 100 == 0))) || (x % 400 == 0)
 *
 * See also the https://en.wikipedia.org/wiki/Karnaugh_map link in order to know
 * how to obtain the desired result without any if ... then ... else construct in an
 * automated and less intuitive way.
 */

class LeapYearTest {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Insert a year following 1582: ");
        int year = in.nextInt();
        boolean div4 = year % 4 == 0;
        boolean div100 = year % 100 == 0;
        boolean div400 = year % 400 == 0;
        //boolean isLeap = (( year % 4 == 0) && (! (year % 100 == 0))) || (year % 400 == 0);
        System.out.println("Is " + year +" a leap year? " + ((div4 && ! div100)||div400) );
    }
}
