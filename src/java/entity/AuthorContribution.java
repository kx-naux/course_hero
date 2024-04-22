package entity;
import java.util.Date;


public class AuthorContribution{ 
    
    private String contributionID;
    private String authorID;
    private String courseID;
    private Double contribution;
    private String description;


    public AuthorContribution() {
    }

    public AuthorContribution(String contributionID, Author author, Course course, double contribution, String description) {
        this.contributionID = contributionID;
        this.authorID = author.getAuthorID();
        this.courseID = course.getCourseID();
        this.contribution = contribution;
        this.description = description;
    }

    public String getContributionID() {
        return contributionID;
    }

    public String getAuthorID() {
        return authorID;
    }
    
    public String getCourseID() {
        return courseID;
    }
 
    public double getContribution() {
        return contribution;
    }
        
    public String getDescription() {
        return description;
    }
           
    public void setContributionID(String contributionID) {
        this.contributionID = contributionID;
    }
    
    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }   
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
  
    public void setContribution(double contribution) {
        this.contribution = contribution;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
   
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", contributionID, authorID, courseID, contribution, description);
    }
    
}

    
    
    
    