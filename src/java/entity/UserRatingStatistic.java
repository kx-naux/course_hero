/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author User
 */
public class UserRatingStatistic {
    private long fiveStarCounts;
    private long fourStarCounts;
    private long threeStarCounts;
    private long twoStarCounts;
    private long oneStarCounts;
    private long allStarCounts;
    private double avgRating;

    // Constructor
    public UserRatingStatistic(long fiveStarCounts, long fourStarCounts, long threeStarCounts, long twoStarCounts, long oneStarCounts) {
        this.fiveStarCounts = fiveStarCounts;
        this.fourStarCounts = fourStarCounts;
        this.threeStarCounts = threeStarCounts;
        this.twoStarCounts = twoStarCounts;
        this.oneStarCounts = oneStarCounts;
        this.allStarCounts = getAllStarCounts();
        this.avgRating = (fiveStarCounts*5 + fourStarCounts*4 +threeStarCounts*3+twoStarCounts*2+oneStarCounts)/(double)this.allStarCounts;
        String test = "";
    }
    
    public double getAvgRating(){
        return avgRating;
    }
    
    public long getAllStarCounts(){
        return fiveStarCounts + fourStarCounts + threeStarCounts + twoStarCounts + oneStarCounts;
    }
    
    public long getOneStarPercentage(){
        if(allStarCounts == 0){
            return 0;
        }
        return (oneStarCounts*100/allStarCounts);
    }
    
    public long getTwoStarPercentage(){
        if(allStarCounts == 0){
            return 0;
        }
        return (twoStarCounts*100/allStarCounts);
    }
    
    public long getThreeStarPercentage(){
        if(allStarCounts == 0){
            return 0;
        }
        return (threeStarCounts*100/allStarCounts);
    }
    
    public long getFourStarPercentage(){
        if(allStarCounts == 0){
            return 0;
        }
        return (fourStarCounts*100/allStarCounts);
    }
    
    public long getFiveStarPercentage(){
        if(allStarCounts == 0){
            return 0;
        }
        return (fiveStarCounts*100/allStarCounts);
    }

    // Getter and setter methods
    public long getFiveStarCounts() {
        return fiveStarCounts;
    }

    public void setFiveStarCounts(long fiveStarCounts) {
        this.fiveStarCounts = fiveStarCounts;
    }

    public long getFourStarCounts() {
        return fourStarCounts;
    }

    public void setFourStarCounts(long fourStarCounts) {
        this.fourStarCounts = fourStarCounts;
    }

    public long getThreeStarCounts() {
        return threeStarCounts;
    }

    public void setThreeStarCounts(long threeStarCounts) {
        this.threeStarCounts = threeStarCounts;
    }

    public long getTwoStarCounts() {
        return twoStarCounts;
    }

    public void setTwoStarCounts(long twoStarCounts) {
        this.twoStarCounts = twoStarCounts;
    }

    public long getOneStarCounts() {
        return oneStarCounts;
    }

    public void setOneStarCounts(long oneStarCounts) {
        this.oneStarCounts = oneStarCounts;
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UserRatingStatistic that = (UserRatingStatistic) obj;

        return fiveStarCounts == that.fiveStarCounts &&
               fourStarCounts == that.fourStarCounts &&
               threeStarCounts == that.threeStarCounts &&
               twoStarCounts == that.twoStarCounts &&
               oneStarCounts == that.oneStarCounts;
    }

    // toString method
    @Override
    public String toString() {
        return "UserRatingStatistic{" +
               "fiveStarCounts=" + fiveStarCounts +
               ", fourStarCounts=" + fourStarCounts +
               ", threeStarCounts=" + threeStarCounts +
               ", twoStarCounts=" + twoStarCounts +
               ", oneStarCounts=" + oneStarCounts +
               '}';
    }
}


