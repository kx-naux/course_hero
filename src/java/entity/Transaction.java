package entity;
import java.time.LocalDateTime;


public class Transaction{ 
    
    private String transactionID;
    private String userID;
    private LocalDateTime timeAdded;
    private double subTotal;
    private String promotionID;
    private double promoAmount;
    private double tax;
    private String transactionType;
    private double transactionFee;
    private String status;
    private double total;
    private String shippingID;


    public Transaction() {
    }

    public Transaction(String transactionID, User user, double subTotal,
                    Promotion promotion, double promoAmount, double tax, String transactionType, 
                    double transactionFee, double total, String status, Shipping shipping, LocalDateTime timeAdded) {
        this.transactionID= transactionID;
        this.userID = user.getUserID();
        this.timeAdded = timeAdded;
        this.subTotal = subTotal;
        this.promotionID= promotion.getPromotionID();
        this.promoAmount = promoAmount;
        this.tax = tax;
        this.transactionType = transactionType;
        this.transactionFee = transactionFee;
        this.status = status;
        this.total = total;
        this.shippingID = shipping.getShippingID();
    }

    public String getTransactionID() {
        return transactionID;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public String getPromotionID(){
        return promotionID;
    }
    
    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }
    
    public double getSubTotal() {
        return subTotal;
    }
     
    public double getPromoAmount() {
        return promoAmount;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String getTransactionType(){
        return transactionType;
    }
    
    public double getTransactionFee(){
        return transactionFee;
    }
    
    public String getStatus(){
        return status;
    }
    
    public double getTotal(){
        return total;
    }
    
    public String getShippingID(){
        return shippingID;
    }
           
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }
    
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
     
    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }
    
    public void setPromoAmount(double promoAmount) {
        this.promoAmount = promoAmount;
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public void setShippingID(String shippingID){
        this.shippingID = shippingID;
    }
 
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s %s %s %s %s", transactionID, userID, subTotal, promotionID, promoAmount, tax, transactionType, transactionFee, total,status,shippingID, timeAdded);
    }
    
}

    
    
    
    