package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberStatisticData {

    public static List<Map<String, String>> getStatistic() {
        List<Map<String, String>> statistic = new ArrayList<>();

        statistic.add(createStatistic("Learners", 98325));
        statistic.add(createStatistic("Authors", 12543));
        statistic.add(createStatistic("Courses", 10324));
        statistic.add(createStatistic("Categories", 45));

        return statistic;
    }

    private static Map<String, String> createStatistic(String title, int number) {
        Map<String, String> statistic = new HashMap<>();

        String numberStr;
        if (number >= 1000000) {
            numberStr = String.format("%.1M+", number / 1000000.0);
        } else if (number >= 1000) {
            numberStr = String.format("%.1fK+", number / 1000.0);
        } else {
            numberStr = String.valueOf(number);
        }

        statistic.put("title", title);
        statistic.put("number", numberStr);

        return statistic;
    }

}
