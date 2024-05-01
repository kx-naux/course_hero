package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberStatisticData {

    
    private int learnersNum;
    private int authorsNum;
    private int coursesNum;
    private int categoriesNum;
    
    public NumberStatisticData(){
        
    }
    
    public NumberStatisticData(int learnerNum,int authorsNum, int coursesNum, int categoriesNum){
        this.learnersNum = learnerNum;
        this.authorsNum = authorsNum;
        this.coursesNum = coursesNum;
        this.categoriesNum = categoriesNum;
    }
    
    public int getLearnersNum() {
        return learnersNum;
    }

    public void setLearnersNum(int learnerNum) {
        this.learnersNum = learnerNum;
    }
    
    public int getAuthorsNum() {
        return authorsNum;
    }

    public void setAuthorsNum(int authorsNum) {
        this.authorsNum = authorsNum;
    }
    
    public int getCoursesNum() {
        return coursesNum;
    }

    public void setCoursesNum(int coursesNum) {
        this.coursesNum = coursesNum;
    }
    
    public int getCategoriesNum() {
        return categoriesNum;
    }

    public void setCategoriesNum(int categoriesNum) {
        this.categoriesNum = categoriesNum;
    }
    
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
