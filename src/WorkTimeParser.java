import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The class contains methods for working with the schedule of specialists
 */
public class WorkTimeParser {
    /**
     * The main public method for preparing schedules
     *
     * @param grafic matrix of schedules
     * @return merged schedule matrix
     */
    public String[][] prepareWorkTime(String[][] grafic) {
        Map<String, String[]> schedules = new HashMap<>();
        for (String[] strings : grafic) {
            if (schedules.containsKey(strings[0])) {
                String[] actual = mergeWorkTime(strings, schedules.get(strings[0]));
                schedules.put(actual[0], actual);
            } else {
                schedules.put(strings[0], strings);
            }
        }
        return convertMapToMatrix(schedules);
    }

    /**
     * The method parses and merges schedule records.
     *
     * @param scheduleOne first schedule
     * @param scheduleTwo second schedule
     * @return joint schedule
     */
    private String[] mergeWorkTime(String[] scheduleOne, String[] scheduleTwo) {
        String[] actual = new String[8];
        actual[0] = scheduleOne[0];
        boolean isEmpty;
        for (int i = 1; i < actual.length; i++) {
            isEmpty = false;
            if (scheduleOne[i].isEmpty() && scheduleTwo[i].isEmpty()) {
                actual[i] = "";
                isEmpty = true;
            } else if (scheduleOne[i].isEmpty() && !scheduleTwo[i].isEmpty()) {
                actual[i] = scheduleTwo[i];
                isEmpty = true;
            } else if (!scheduleOne[i].isEmpty() && scheduleTwo[i].isEmpty()) {
                actual[i] = scheduleOne[i];
                isEmpty = true;
            }
            if (!isEmpty) {
                actual[i] = parseAndMergeTimes(scheduleOne[i], scheduleTwo[i]);
            }
        }
        return actual;
    }

    /**
     * The method parses and merges two timespan records.
     *
     * @param timeOne first time range entry
     * @param timeTwo second time range entry
     * @return concatenated string
     */
    private String parseAndMergeTimes(String timeOne, String timeTwo) {
        String actual;
        String[] one = timeOne.split("-");
        String[] two = timeTwo.split("-");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime begOne = LocalTime.parse(one[0], formatter);
        LocalTime endOne = LocalTime.parse(one[1], formatter);
        LocalTime begTwo = LocalTime.parse(two[0], formatter);
        LocalTime endTwo = LocalTime.parse(two[1], formatter);

        if (begOne.isAfter(endTwo) || begTwo.isAfter(endOne)) {
            actual = begOne.format(formatter) + "-" + endOne.format(formatter) + ", "
                    + begTwo.format(formatter) + ":" + endTwo.format(formatter);
        } else {
            LocalTime beg = begOne.isAfter(begTwo) ? begTwo : begOne;
            LocalTime end = endOne.isAfter(endTwo) ? endOne : endTwo;

            actual = beg.format(DateTimeFormatter.ofPattern("HH:mm")) + "-" + end.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return actual;
    }

    /**
     * The method converts the hashmap to a matrix
     *
     * @param schedules specialist scheduling map
     * @return specialist scheduling matrix
     */
    private String[][] convertMapToMatrix(Map<String, String[]> schedules) {
        String[][] scheduleMatrix = new String[schedules.size()][8];
        int pointer = 0;
        for (Map.Entry<String, String[]> line : schedules.entrySet()) {
            scheduleMatrix[pointer++] = line.getValue();
        }
        return scheduleMatrix;
    }
}
