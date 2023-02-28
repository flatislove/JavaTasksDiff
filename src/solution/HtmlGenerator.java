package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        StringBuilder html = new StringBuilder("<html><head><meta charset='UTF-8'>");
        html.append("<style>");
        html.append(".table {display: table; border-collapse: collapse; width: 100%;}");
        html.append(".row {display: table-row; border: 1px solid black;}");
        html.append(".header-row {font-weight: bold; background-color: lightgray;}");
        html.append(".cell {display: table-cell; padding: 5px; border: 1px solid black;}");
        html.append("</style>");
        html.append("</head><body>");

        html.append("<div class='table'>");
        html.append("<div class='row header-row'>");
        html.append("<div class='cell'>N</div>");
        html.append("<div class='cell'>Специалист</div>");
        html.append("<div class='cell'>Понедельник</div>");
        html.append("<div class='cell'>Вторник</div>");
        html.append("<div class='cell'>Среда</div>");
        html.append("<div class='cell'>Четверг</div>");
        html.append("<div class='cell'>Пятница</div>");
        html.append("<div class='cell'>Суббота</div>");
        html.append("<div class='cell'>Воскресенье</div>");
        html.append("</div>");

        for (int i = 0; i < schedule.length; i++) {
            html.append("<div class='row'>");
            html.append("<div class='cell'>").append(i + 1).append("</div>");
            for (String cell : schedule[i]) {
                html.append("<div class='cell'>").append(cell).append("</div>");
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
