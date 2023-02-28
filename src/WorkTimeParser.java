import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class WorkTimeParser {
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

                String[] one = scheduleOne[i].split("-");
                String[] two = scheduleTwo[i].split("-");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                LocalTime begOne = LocalTime.parse(one[0], formatter);
                LocalTime endOne = LocalTime.parse(one[1], formatter);
                LocalTime begTwo = LocalTime.parse(two[0], formatter);
                LocalTime endTwo = LocalTime.parse(two[1], formatter);

                if (begOne.isAfter(endTwo) || begTwo.isAfter(endOne)) {
                    actual[i] = begOne.format(formatter) + "-" + endOne.format(formatter) + ", "
                            + begTwo.format(formatter) + ":" + endTwo.format(formatter);
                } else {
                    LocalTime beg = begOne.isAfter(begTwo) ? begTwo : begOne;
                    LocalTime end = endOne.isAfter(endTwo) ? endOne : endTwo;

                    actual[i] = beg.format(DateTimeFormatter.ofPattern("HH:mm")) + "-" + end.format(DateTimeFormatter.ofPattern("HH:mm"));
                }
            }
        }
        return actual;
    }

    private String[][] convertMapToMatrix(Map<String, String[]> schedules) {
        String[][] scheduleMatrix = new String[schedules.size()][8];
        int pointer = 0;
        for (Map.Entry<String, String[]> line : schedules.entrySet()) {
            scheduleMatrix[pointer++] = line.getValue();
        }
        return scheduleMatrix;
    }
}
