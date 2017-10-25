import java.util.Scanner;

class Quadranti {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Provide two space-separated doubles");
        String pair = input.nextLine().trim(); // chaining the operations
        /*
           //Please note that the above line is equivalent to the following one:

           String pair = input.nextLine();
           pair = pair.trim();
         */
        if (!pair.contains(" ")) {
            System.err.println("Error: the string does not contain a space");
        } else {
            int position = pair.indexOf(" ");
            double left = Double.valueOf(pair.substring(0,position).trim());
            double right = Double.valueOf(pair.substring(position).trim());
            if (left > 0 ) {
                if (right > 0) {
                    System.out.println("I Quadrant");
                } else {
                    System.out.println("IV Quadrant");
                }
            } else {
                if (right > 0) {
                    System.out.println("II Quadrant");
                } else {
                    System.out.println("III Quadrant");
                }
            }

            /*
            // More compact alternative
            System.out.println((left > 0 ? (right > 0 ? "I" : "IV") : (right > 0 ? "II" : "III")) + " Quadrant");
            */
        }
    }
}
