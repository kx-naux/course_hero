/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class AboutUsStats {
    private List<Map<String, String>> statistics = new ArrayList<>();
    
    public List<Map<String, String>> getStatistic() {
        return statistics;
    }
    
    public void setStatistic(List<Map<String, String>> statistics) {
        this.statistics = statistics;
    }

    public void addStatistic(String title, int number) {
        Map<String, String> statistic = new HashMap<>();
        
        //get string value
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

        statistics.add(statistic);
    }
}
