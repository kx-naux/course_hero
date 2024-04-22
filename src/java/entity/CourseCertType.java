package entity;
import java.util.Date;


public class CourseCertType{ 
    
    private String certTypeID;
    private String courseID;
    private String description;
    private String issuingAuthority;
    private String certLevel;


    public CourseCertType() {
    }

    public CourseCertType(String certTypeID, Course course, String description, String issuingAuthority,
                    String certLevel) {
        this.certTypeID = certTypeID;
        this.courseID = course.getCourseID();
        this.description = description;
        this.issuingAuthority = issuingAuthority;
        this.certLevel = certLevel;
    }

    public String getCertTypeID() {
        return certTypeID;
    }
    
    public String getCourseID() {
        return courseID;
    }
   
    public String getDescription() {
        return description;
    }
     
    public String getIssuingAuthority() {
        return issuingAuthority;
    }
    
    public String getCertLevel() {
        return certLevel;
    }
           
    public void setCertTypeID(String certTypeID) {
        this.certTypeID = certTypeID;
    }
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }
     
    public void setCertLevel(String certLevel) {
        this.certLevel = certLevel;
    }
   
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", certTypeID, courseID, description, issuingAuthority, certLevel);
    }
    
}

    
    
    
    