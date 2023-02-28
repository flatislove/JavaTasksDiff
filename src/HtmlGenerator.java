import java.io.*;

/**
 * The class contains a method for generating a html document
 */
public class HtmlGenerator {
    /**
     * Method for generating page html only from div tags
     *
     * @param schedule specialist scheduling matrix
     * @param filename HTML document name
     */
    public void saveToHtml(String[][] schedule, String filename) {
        StringBuilder html = new StringBuilder("<html><head><meta charset='UTF-8'></head><body><div style='display: table;'>");
        html.append("<div style='display: table-row;'>");
        html.append("<div style='display: table-cell; font-weight: bold;'>N</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Специалист</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Понедельник</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Вторник</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Среда</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Четверг</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Пятница</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Суббота</div>");
        html.append("<div style='display: table-cell; font-weight: bold;'>Воскресенье</div>");
        html.append("</div>");

        for (int i = 0; i < schedule.length; i++) {
            html.append("<div style='display: table-row;'>");
            html.append(i + 1);
            for (String cell : schedule[i]) {
                html.append("<div style='display: table-cell;'>");
                html.append(cell);
                html.append("</div>");
            }
            html.append("</div>");
        }
        html.append("</div></body></html>");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(html.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
