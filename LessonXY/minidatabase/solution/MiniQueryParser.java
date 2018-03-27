import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MiniQueryParser {

    private static final String load = "load ";
    private static final String count = "count";
    private static final String project = "project ";
    private static final String filter = "filter ";
    private static final String group = "group ";
    private static final String dump = "dump ";

    Scanner console;
    public MiniQueryParser(InputStream in) {
        console = new Scanner(in);
    }

    public void evaluate() {
        Table t = new Table();
        while (console.hasNext()) {
            String line = console.nextLine();
            if (line.toLowerCase().startsWith(load)) {
                String filePath = line.substring(load.length()).trim();
                File file = new File(filePath);
                try {
                    t = t.load(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (line.trim().toLowerCase().equals(count)) {
                System.out.println(t.count());
            } else if (line.trim().toLowerCase().startsWith(project)) {
                String fields[] = line.substring(project.length()).trim().split(",");
                t = t.project(fields);
            } else if (line.trim().toLowerCase().startsWith(filter)) {
                try {
                    String ops[] = line.substring(filter.length()).trim().split("\\s");
                    t = t.filter(ops[0], ops[1], Integer.valueOf(ops[2]));
                } catch (Exception e) {
                        e.printStackTrace();
		}
            } else if (line.trim().toLowerCase().startsWith(group)) {
                String ops[] = line.substring(group.length()).trim().split("\\s");
                if (ops[1].toLowerCase().equals("by") && ops[3].toLowerCase().equals("with") && ops[5].toLowerCase().equals("as")) {
                    try {
                        String groupField = ops[0];
                        String byField = ops[2];
                        String operator = ops[4];
                        String name = ops[6];
                        t = t.groupBy(groupField, byField, operator, name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (line.toLowerCase().startsWith(dump)) {
                String filePath = line.substring(dump.length()).trim();
                try {
                    t.print(new File(filePath));
                    System.out.println(t.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (line.toLowerCase().trim().toLowerCase().equals("print")) {
                System.out.println(t);
            }
        }
    }

}
