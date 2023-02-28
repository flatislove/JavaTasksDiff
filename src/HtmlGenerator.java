import java.io.FileWriter;
import java.io.IOException;

public class HtmlGenerator {
    public void saveToHtml(String[][] workTime, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("<div><table>");
            for (String[] strings : workTime) {
                writer.write("<tr>");
                for (String string : strings) {
                    writer.write("<td>" + string + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
