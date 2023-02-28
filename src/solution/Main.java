package solution;

import java.util.Arrays;

/**
 * The class is the entry point. The base execution script is set
 */
public class Main {
    public static void main(String[] args) {
        String[][] grafic = {
                {"Robin", "12:00-12:20", "12:10-14:00", "", "", "", "15:00-17:00", ""},
                {"Aaron", "14:40-15:10", "14:50-15:50", "", "", "", "", ""},
                {"Zinedine", "13:30-13:50", "", "", "", "15:50-16:10", "", ""},
                {"Tomasz", "13:20-13:50", "14:10-15:55", "15:50-16:15", "", "", "", ""},
                {"Tomasz", "13:10-13:50", "15:55-16:00", "15:50-16:30", "", "", "", ""}
        };

        WorkTimeParser parser = new WorkTimeParser();
        String[][] merged = parser.prepareWorkTime(grafic);

        for (String[] line : merged) {
            System.out.println(Arrays.toString(line));
        }
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.saveToHtml(merged, "schedule.html");
    }
}
