import java.util.Scanner;

/*
 * Create a program that takes a string with the following format as an input:
 *        1024356 h S
 * and converts 1024356 hours (h) into seconds (S). In particular, the source 
 * time unit is in lowercase, and the upper time unit is the target time unit
 * towards which apply the conversion. The strings that can be used to express 
 * such time units are the following ones:
 *
 * y/Y = Year
 * d/D = Day
 * h/H = Hour
 * m/M = Minute
 * s/S = Seconds
 * 
 * Create a program that parses the string and provides the desired time conversion
 */

class TimeConverter {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Provide a string as an input: ");

        String input = in.nextLine();
        boolean isYear = input.contains("y");
        input = input.replace("y","");
        boolean isDay = input.contains("d");
        input = input.replace("d","");
        boolean isHour = input.contains("h");
        input = input.replace("h","");
        boolean isMinute = input.contains("m");
        input = input.replace("m","");
        boolean isSeconds = input.contains("s");
        input = input.replace("s","");
	double conv1 = (isYear ? 1 : (isDay ? 365 : (isHour ? 365*24 : (isMinute ? 365*24*60 : 365*24*60*60))));

        boolean toYear = input.contains("Y");
        input = input.replace("Y","");
        boolean toDay = input.contains("D");
        input = input.replace("D","");
        boolean toHour = input.contains("H");
        input = input.replace("H","");
        boolean toMinute = input.contains("M");
        input = input.replace("M","");
        boolean toSeconds = input.contains("S");
        input = input.replace("S","");
        input = input.trim();
	double conv2 = toYear ? 1 : (toDay ? 365 : (toHour ? 365*24 : (toMinute ? 365*24*60 : 365*24*60*60)));

        int unit1 = isYear ? 4 : (isDay ? 3 : (isHour ? 2 : (isMinute ? 1 : 0)));
        int unit2 = toYear ? 4 : (toDay ? 3 : (toHour ? 2 : (toMinute ? 1 : 0)));
        double value = Double.valueOf(input);

        double toreturn = value * conv2 / conv1;

	/* Advanced

	// Checks if the conversion implies a multiplication (true) or a division (false)
        boolean grow = unit1 > unit2;
        boolean originalGrow = grow;

	// I adjust the "indices" for returning the right conversion for variable conversion
	//unit1 = grow ? unit1-1 : unit1;
	//unit2 = grow ? unit2-1 : unit2; 
	//unit2 = unit2 < 0 ? 0 : unit2;
        if (grow) {
            unit1--;
            unit2--;
            unit2 = unit2 < 0 ? 0 : unit2;
        }
       
	// See the first lesson, where the iteration was defined by a boolean.
        // this means iterate the body until the condition stated in the body is met
        while (unit1 != unit2) {
            // Gets the integer to be returned for the conversion
            double conversion = (unit1 == 0 ? 60 : (unit1 == 1 ? 60 : (unit1 == 2 ?  24 : 365)));

            // Depending if the conversion requires to multiply or divide
            // What does it happens if I replace 1.0 with 1 ??
            toreturn *= (grow ? conversion : (1.0/conversion));

            // Depending if the conversion is decreasing or increasing the unit of measurement,
            // I want to reach the final unit of measurement
            unit1 += (grow ? (-1) : (+1));
        }
	*/

        System.out.println(toreturn);

    }
}
