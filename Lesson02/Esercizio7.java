public class Esercizio7 {

    /**
     * Creare un programme che, date due stringhe arbitrarie "data" e "pattern", controlla quante volte "pattern" è
     * contenuto dentro a "data"
     */

    /**
     * Given two arbitrary strings, "data" and "pattern", create a program that checks how many times pattern is
     * represented as a substring of data.
     */

    public static void main(String[] args) {
        // Data visited by the pattern
        String data = "pannannanananaknanapananamingnatan";
        // Index used to scan data
        int dataIndex = 0;

        // Pattern (substring) to be found in data
        String pattern = "nana";
        // Index used to scan the pattern
        int patternIndex = 0;

        // Data index from which I start a new visit of the pattern
        int currentStart = 0;
        // Data index, subsequent to currentStart, where I can start to visit the pattern a-new
        int newBeginning = 0;
        // How many times I met pattern inside data
        int count = 0;

        /*
         * Until the visited position does not point at the end of the string, and if the pattern is not empty
         */
        while (dataIndex < data.length() && pattern.length() > 0) {
            char l = data.charAt(dataIndex);
            char r = pattern.charAt(patternIndex);
            /////System.out.println("<-- l = " + l + "; r = "+r+" pos = "+pos+"; currentStart=" + currentStart + "["+ data.charAt(currentStart) +"]; patternPos=" + patternPos +"; newBeginning = "+newBeginning+ "[" + data.charAt(newBeginning) + "]; count = "+count);

            if (l == r) {
                /*
                 * If I've just started to match the pattern against the data, set newBeginning and currentStart so that
                 * I know that I'm starting a new visiting process
                 */
                if (patternIndex == 0)
                    newBeginning = currentStart = dataIndex;
                else {
                    /*
                      Otherwise, set newBeginning to the index in "data" to the first occurrence of another letter
                      in data equivalent to the beginning of the pattern. This index must be subsequent to currentStart
                     */
                    if (r == pattern.charAt(0) && dataIndex != currentStart && newBeginning == currentStart) {
                        newBeginning = dataIndex;
                    }
                }

                /* Continuing to both match the data and the pattern */
                dataIndex++;
                patternIndex++;

                /* If I finished to visit the pattern against a specific substring of the data*/
                if (patternIndex == pattern.length()) {
                    count++;
                    /*
                     If I have a new place where to re-start from, do it!
                     */
                    if (newBeginning != currentStart) {
                        dataIndex = newBeginning;
                    } else {
                        /* else, do continue the visit */
                        dataIndex++;
                    }
                    /* Starts the visit a-new */
                    patternIndex = 0;
                    /*if (pos < data.length()) {
                        System.out.println(data + "\n" + data.substring(0, pos) + "." + data.substring(pos + 1) + "{"+pos+"}");
                    } else {
                    }*/
                }

            } else {
                /*
                 * If I've already met a point from which I can restart the pattern visit (patternIndex = 0),
                 * start the match from this value (newBeginning)
                 */
                if (currentStart != newBeginning) {
                    dataIndex = newBeginning;
                    currentStart = newBeginning;
                } else {
                    /*
                     * Otherwise:
                     * 1) if patternIndex == 0, I must then increment the index (the first letter of the pattern is not matched)
                     * 2) if patternIndex > 0,  I must set it to zero, and re-start the matching process from dataIndex.
                     */
                    dataIndex += (patternIndex == 0 ? 1 : 0);
                    /*
                     * dataIndex becomes the new point from which start the computation
                     */
                    newBeginning = currentStart = dataIndex;
                }
                patternIndex = 0;
            }
        }

        System.out.println("#Occurences, " + count);
    }

}
