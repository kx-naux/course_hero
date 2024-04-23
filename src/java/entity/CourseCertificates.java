package entity;
import java.time.LocalDateTime;



public class CourseCertificates{ 
    
    private String certificatesID;
    private String userID;
    private String courseID;
    private LocalDateTime timeIssued;
    private String certTypeID;
    private String certificateNumber;


    public CourseCertificates() {
    }

    public CourseCertificates(String certificatesID, CourseCertType courseCertType, User user, Course course, LocalDateTime timeIssued,
                     String certificateNumber) {
        this.certificatesID = certificatesID;
        this.userID = user.getUserID();
        this.courseID = course.getCourseID();
        this.timeIssued = timeIssued;
        this.certTypeID= courseCertType.getCertTypeID();
        this.certificateNumber = certificateNumber;
    }

    public String getCertificatesID() {
        return certificatesID;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public String getCourseID() {
        return courseID;
    }
    
    public LocalDateTime getTimeIssued() {
        return timeIssued;
    }
     
    public String getCertTypeID() {
        return certTypeID;
    }
    
    public String getCertificateNumber() {
        return certificateNumber;
    }
           
    public void setCertificatesID(String certificatesID) {
        this.certificatesID = certificatesID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public void setTimeIssued(LocalDateTime timeIssued) {
        this.timeIssued = timeIssued;
    }
    
    public void setCertTypeID(String certTypeID) {
        this.certTypeID = certTypeID;
    }
    
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
 
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", certificatesID,certTypeID,userID, courseID, timeIssued, certificateNumber);
    }
    
}

    
    
    
    