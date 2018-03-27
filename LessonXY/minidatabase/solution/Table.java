import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    private String[] header;
    private ArrayList<ArrayList<Integer>> rows;

    public Table() {
        rows = new ArrayList<>();
        header = new String[]{};
    }

    public Table(String[] header) {
        this();
        this.header = header;
    }

    public Table load(File f) throws Exception {
        Scanner scanner = new Scanner(f);
        boolean isHeader = true;
        while (scanner.hasNext()) {
            if (isHeader) {
                header = scanner.nextLine().replaceAll("\\s","").split(",");
                for (int i = 0; i<header.length; i++) {
                    for (int j = 0; j<header.length; j++) {
                        if (header[i].equals(header[j]) && i != j)
                            throw new Exception("Two header elements with different positions have the same name");
                    }
                }
                isHeader = false;
            } else {
                String[] line = scanner.nextLine().replaceAll("\\s","").split(",");
                if (line.length == header.length) {
                    ArrayList<Integer> lineList = new ArrayList<>();
                    for (int i = 0; i<line.length; i++) {
                        lineList.add(Integer.valueOf(line[i]));
                    }
                    rows.add(lineList);
                } else {
                    throw new Exception("The number of the header's columns does not match with the current line's columns");
                }
            }
        }
        scanner.close();
        return this;
    }

    private Table addRow(ArrayList<Integer> row) {
        rows.add(row);
        return this;
    }

    public int count() {
        return rows.size();
    }

    public Table project(String... args) {
        ArrayList<Integer> positions = new ArrayList<>();
        ArrayList<String> argList = new ArrayList<>();
        for (String arg : args) {
            if (!argList.contains(arg))
                argList.add(arg);
        }
        for (int i = 0; i<header.length; i++) {
            for (String arg : argList) {
                if (header[i].equals(arg)) {
                    positions.add(i);
                }
            }
        }
        String[] newHeader = new String[positions.size()];
        for (int i = 0; i<newHeader.length; i++) {
            newHeader[i] = header[positions.get(i)];
        }
        Table newTable = new Table(newHeader);
        if (newHeader.length == 0)
            return newTable;
        for (ArrayList<Integer> row : rows) {
            ArrayList<Integer> projectedRow = new ArrayList<>();
            for (Integer pos : positions) {
                projectedRow.add(row.get(pos));
            }
            newTable.addRow(projectedRow);
        }
        return newTable;
    }

    private int getFieldPos(String field) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(field)) {
                return i;
            }
        }
        return -1;
    }

    public Table filter(String filed, String operator, int value) {
        Table newTable = new Table(header);
        int fieldPos = getFieldPos(filed);
        if (fieldPos == -1) {
            return newTable;
        } else {
            for (ArrayList<Integer> row : rows) {
                int rowValue = row.get(fieldPos);
                if (operator.equals("=")) {
                    if (rowValue == value)
                        newTable.addRow(row);
                } else if (operator.equals("<")) {
                    if (rowValue < value)
                        newTable.addRow(row);
                } else if (operator.equals(">")) {
                    if (rowValue > value)
                        newTable.addRow(row);
                } else if (operator.equals(">=")) {
                    if (rowValue >= value)
                        newTable.addRow(row);
                } else if (operator.equals("<=")) {
                    if (rowValue <= value)
                        newTable.addRow(row);
                } else if (operator.equals("!=")) {
                    if (rowValue != value)
                        newTable.addRow(row);
                }
            }
            return newTable;
        }
    }

    public Table groupBy(String groupField, String byField, String operator, String newFieldName) throws Exception {
        int groupFieldPos = getFieldPos(groupField);
        int byFieldPos = getFieldPos(byField);
        if (groupFieldPos == -1 || byFieldPos == -1 || byField.equals(newFieldName)) {
            return new Table();
        } else {
            IntPairList ipl = new IntPairList();
            Table newTable = new Table(new String[]{byField,newFieldName});
            int accBase;
            if (operator.equals("*")) {
                accBase = 1;
            } else if (operator.equals("+")) {
                accBase = 0;
            } else {
                throw new Exception("Unsupported operator");
            }
            for (ArrayList<Integer> row : rows) {
                int byValue = row.get(byFieldPos);
                int accValue = row.get(groupFieldPos);
                Integer value = ipl.getByValue(byValue);
                if (value == null)
                    value = accBase;
                if (accBase == 1)
                    value *= accValue;
                if (accBase == 0)
                    value += accValue;
                ipl.updateByValue(byValue, value);
            }
            newTable.addAll(ipl);
            return newTable;
        }
    }

    private void addAll(IntPairList ipl) {
        for (IntPair row : ipl.asList()) {
            ArrayList<Integer> newRow = new ArrayList<>(2);
            newRow.add(row.getBy());
            newRow.add(row.getAcc());
            addRow(newRow);
        }
    }

    public void print(File f) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(f);
        writer.print(toString());
        writer.close();
    }

    @Override
    public String toString() {
        if (header.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<header.length; i++) {
            sb.append(header[i]);
            if (i != header.length-1) {
                sb.append(',');
            }
        }
        if (!rows.isEmpty())
            sb.append('\n');
        for (int j = 0; j<rows.size(); j++) {
            ArrayList<Integer> row = rows.get(j);
            for (int i = 0; i<header.length; i++) {
                sb.append(row.get(i));
                if (i != header.length-1) {
                    sb.append(',');
                }
            }
            if (j != rows.size()-1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

}
