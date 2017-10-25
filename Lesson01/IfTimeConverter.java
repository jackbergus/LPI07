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

class IfTimeConverter {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Provide a string as an input: ");
	boolean error = false;

        String input = in.nextLine();
	double value = 1;
        
	if (input.contains("y")) {		//boolean isYear = input.contains("y");
		value *= 365*24*60*60;
 	} else if (input.contains("d")) {	//boolean isDay = input.contains("d");
		value *= 24*60*60;
	} else if (input.contains("h")) {	//boolean isHour = input.contains("h");
		value *= 60*60;
	} else if (input.contains("m")) {	//boolean isMinute = input.contains("m");
		value *= 60;
	} else if (input.contains("s")) {	//boolean isSeconds = input.contains("s");
		//noop
	} else {
		System.err.println("Error: there is no valid input format associated to the string");
		error = true;
	}


	if (input.contains("Y")) {		//boolean isYear = input.contains("y");
		value /= 365*24*60*60;
 	} else if (input.contains("D")) {	//boolean isDay = input.contains("d");
		value /= 24*60*60;
	} else if (input.contains("H")) {	//boolean isHour = input.contains("h");
		value /= 60*60;
	} else if (input.contains("M")) {	//boolean isMinute = input.contains("m");
		value /= 60;
	} else if (input.contains("S")) {	//boolean isSeconds = input.contains("s");
		//noop
	} else {
		System.err.println("Error: there is no valid conversion format associated to the string");
		error = true;
	}

	input = input.replaceAll("y|d|h|m|s|Y|D|H|M|S", "");
        /*

	// the previous line replaces all the following ones:
	
	input = input.replace("y","");
        input = input.replace("d","");
        input = input.replace("h","");
        input = input.replace("m","");
        input = input.replace("s","");
        input = input.replace("Y","");
        input = input.replace("D","");
        input = input.replace("H","");
        input = input.replace("M","");
        input = input.replace("S","");
	*/
	input = input.trim();
	value *= Double.valueOf(input);

	if (!error) System.out.println(value);

    }
}
