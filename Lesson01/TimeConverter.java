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
	double conv1 = (isYear ? 365*24*60*60 : (isDay ? 24*60*60 : (isHour ? 60*60 : (isMinute ? 60 : 1))));

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
	double conv2 = toYear ? 365*24*60*60 : (toDay ? 24*60*60 : (toHour ? 60*60 : (toMinute ? 60 : 1)));

        int unit1 = isYear ? 4 : (isDay ? 3 : (isHour ? 2 : (isMinute ? 1 : 0)));
        int unit2 = toYear ? 4 : (toDay ? 3 : (toHour ? 2 : (toMinute ? 1 : 0)));
        double value = Double.valueOf(input);

        double toreturn = value * conv1 / conv2;

        System.out.println(toreturn);

    }
}
