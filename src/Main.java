/**
 * The class is the entry point. The base execution script is set
 */
public class Main {
    public static void main(String[] args) {
        String[] doc1 = {"doc1name", "10:00-11:00", "", "", "", "", "", "13:20-13:40"};
        String[] doc2 = {"doc2name", "10:00-11:00", "", "", "", "", "", "13:20-13:40"};
        String[] doc3 = {"doc3name", "10:00-11:00", "", "", "", "", "", "13:20-13:40"};
        String[] doc4 = {"doc4name", "12:34-14:56", "", "", "", "", "", "13:20-13:40"};
        String[] doc5 = {"doc4name", "11:35-13:45", "", "", "", "", "", "13:10-13:40"};
        String[] doc6 = {"doc4name", "17:35-18:45", "", "", "", "", "", "13:10-13:40"};

        String[][] schedule = {doc1, doc2, doc3, doc4, doc5, doc6};
        WorkTimeParser workTimeParser = new WorkTimeParser();
        String[][] strings = workTimeParser.prepareWorkTime(schedule);
        for (String[] string : strings) {
            for (String s : string) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.saveToHtml(strings, "schedule.html");
    }
}
