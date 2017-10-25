import java.util.Scanner;

class QuadrialteralsClassification {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);


        /**
         * Creating the pairs
         */
        System.out.print("Provide coordinates for point A (two space-separated doubles): ");
        String point = input.nextLine().trim(); // chaining the operations
        int position = point.indexOf(" ");
        double xA = Double.valueOf(point.substring(0,position).trim());
        double yA = Double.valueOf(point.substring(position).trim());

        System.out.print("Provide coordinates for point B (two space-separated doubles): ");
        point = input.nextLine().trim(); // chaining the operations
        position = point.indexOf(" ");
        double xB = Double.valueOf(point.substring(0,position).trim());
        double yB = Double.valueOf(point.substring(position).trim());

        System.out.print("Provide coordinates for point C (two space-separated doubles): ");
        point = input.nextLine().trim(); // chaining the operations
        position = point.indexOf(" ");
        double xC = Double.valueOf(point.substring(0,position).trim());
        double yC = Double.valueOf(point.substring(position).trim());

        System.out.print("Provide coordinates for point D (two space-separated doubles): ");
        point = input.nextLine().trim(); // chaining the operations
        position = point.indexOf(" ");
        double xD = Double.valueOf(point.substring(0,position).trim());
        double yD = Double.valueOf(point.substring(position).trim());

        /**0
         * Evaluating the slopes
         */
        double slopeAB = (yB - yA) / (xB - xA);
        double slopeBC = (yC - yB) / (xC - xB);
        double slopeCD = (yD - yC) / (xD - xC);
        double slopeDA = (yA - yD) / (xA - xD);

        /**
         * Evaluating the length
         */
        double AB = Math.sqrt(Math.pow((yB - yA), 2) + Math.pow((xB - xA), 2));
        double BC = Math.sqrt(Math.pow((yB - yC), 2) + Math.pow((xB - xC), 2));
        double CD = Math.sqrt(Math.pow((yD - yC), 2) + Math.pow((xD - xC), 2));
        double DA = Math.sqrt(Math.pow((yD - yA), 2) + Math.pow((xD - xA), 2));

        // Evaluating the diagonals
        double AC = Math.sqrt(Math.pow((yC - yA), 2) + Math.pow((xC - xA), 2));
        double BD = Math.sqrt(Math.pow((yB - yD), 2) + Math.pow((xB - xD), 2));

        System.out.println("Length AB = " + AB);
        System.out.println("Length BC = " + BC);
        System.out.println("Length CD = " + CD);
        System.out.println("Length DA = " + DA);

        //arccos((P122 + P132 - P232) / (2 * P12 * P13))

        double ABC = Math.toDegrees(Math.acos((Math.pow(AB,2)+Math.pow(BC,2)-Math.pow(AC,2))/(2 * AB * BC)));
        ABC = ABC < 0 ? ABC + 360 : ABC;
        double BCD = Math.toDegrees(Math.acos((Math.pow(BC,2)+Math.pow(CD,2)-Math.pow(BD,2))/(2 * BC * CD)));
        BCD = BCD < 0 ? BCD + 360 : BCD;
        double CDA = Math.toDegrees(Math.acos((Math.pow(CD,2)+Math.pow(DA,2)-Math.pow(AC,2))/(2 * CD * DA)));
        CDA = CDA < 0 ? CDA + 360 : CDA;
        double DAB = Math.toDegrees(Math.acos((Math.pow(DA,2)+Math.pow(AB,2)-Math.pow(BD,2))/(2 * DA* AB)));
        DAB = DAB < 0 ? DAB + 360 : DAB;

        System.out.println("Degree ABC = " + ABC);
        System.out.println("Degree BCD = " + BCD);
        System.out.println("Degree CDA = " + CDA);
        System.out.println("Degree DAB = " + DAB);

        String result = "Quadrilateral";
        boolean hasRect = (ABC == BCD && BCD == CDA && CDA == DAB && DAB == 90.0);
        boolean sameLength = (AB == BC && BC == CD && CD == DA && DA == AB);
        if (slopeAB == slopeCD || slopeBC == slopeDA) {
            if (slopeAB == slopeCD &&  slopeBC == slopeDA) {
                // It is a parallelogram
                if (hasRect) {
                    // It can be either a square or a rectangle;
                    result = sameLength ? "Square" : "Rectangle";
                } else {
                    result = sameLength ? "Rhombus" : "Parallelogram";
                }
            } else {
                result = "Trapezoid";
            }
        }

        System.out.println(result);

    }
}
