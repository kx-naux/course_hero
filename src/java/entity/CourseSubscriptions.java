package entity;
import java.time.LocalDateTime;


public class CourseSubscriptions{ 
    
    private String subscriptionsID;
    private String courseID;
    private String userID;
    private LocalDateTime subscribeTime;
    private LocalDateTime startTime;
    private String progress;
    private LocalDateTime finishTime;


    public CourseSubscriptions() {
    }

    public CourseSubscriptions(String subscriptionsID, Course course, User user, LocalDateTime subscribeTime,
                    LocalDateTime startTime, String progress, LocalDateTime finishTime) {
        this.subscriptionsID = subscriptionsID;
        this.courseID = course.getCourseID();
        this.userID = user.getUserID();
        this.subscribeTime = subscribeTime;
        this.startTime= startTime;
        this.progress = progress;
        this.finishTime = finishTime;
    }

    public String getSubscriptionsID() {
        return subscriptionsID;
    }
    
    public String getCourseID() {
        return courseID;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public LocalDateTime getSubscribeTime() {
        return subscribeTime;
    }
     
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public String getProgress() {
        return progress;
    }
    
    public LocalDateTime getFinishTime() {
        return finishTime;
    }
           
    public void setSubscriptionsID(String subscriptionsID) {
        this.subscriptionsID = subscriptionsID;
    }
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }    

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public void setSubscribeTime(LocalDateTime subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public void setProgress(String progress) {
        this.progress = progress;
    }
    
    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }
 
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", subscriptionsID, courseID, userID, subscribeTime, startTime, progress,finishTime);
    }
    
}

    
    
    
    