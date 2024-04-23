package entity;
import java.time.LocalDateTime;


public class Promotion{ 
    
    private String promotionID;
    private String promoType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double amount;
    private double minReq;
    private String geographicRestriction;
    private String status;
    private String legalTNC;


    public Promotion() {
    }

    public Promotion(String promotionID, LocalDateTime startTime, LocalDateTime endTime, String promoType, 
                     double amount, double minReq, String legalTNC, String status, String geographicRestriction) {
        this.promotionID= promotionID;
        this.promoType = promoType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
        this.minReq = minReq;
        this.geographicRestriction = geographicRestriction;
        this.status = status;
        this.legalTNC = legalTNC;
    }

    public String getPromotionID() {
        return promotionID;
    }
    
    public String getPromoType() {
        return promoType;
    }
   
    public LocalDateTime getStartTime() {
        return startTime;
    }
     
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public double getamount(){
        return amount;
    }
    
    public double getMinReq(){
        return minReq;
    }
    
    public String getGeographicRestriction(){
        return geographicRestriction;
    }
        
    public String getStatus(){
        return status;
    }
            
    public String getLegalTNC(){
        return legalTNC;
    }
           
    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }
    
    public void setPromotionType(String promoType) {
        this.promoType = promoType;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public void setamount(double amount) {
        this.amount = amount;
    }
    
    public void setMinReq(double minReq) {
        this.minReq = minReq;
    }
    
    public void setGeographicRestriction(String geographicRestriction) {
        this.geographicRestriction = geographicRestriction;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setLegalTNC(String legalTNC) {
        this.legalTNC = legalTNC;
    }
 
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s %s", promotionID, startTime, endTime, promoType, amount, minReq, legalTNC, status,  geographicRestriction);
    }
    
}

    
    
    
    