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

    // Constructor
    public UserRatingStatistic(long fiveStarCounts, long fourStarCounts, long threeStarCounts, long twoStarCounts, long oneStarCounts) {
        this.fiveStarCounts = fiveStarCounts;
        this.fourStarCounts = fourStarCounts;
        this.threeStarCounts = threeStarCounts;
        this.twoStarCounts = twoStarCounts;
        this.oneStarCounts = oneStarCounts;
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


