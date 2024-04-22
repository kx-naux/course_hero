package entity;
import java.time.LocalDateTime;


public class Payment{ 
    
    private String paymentID;
    private String transactionID;
    private String paymentMethodID;
    private String accountNumber;
    private String paymentGateway;
    private double amount;
    private String status;
    private LocalDateTime dateIssued;


    public Payment() {
    }

    public Payment(String paymentID, PaymentMethod paymentMethod, Transaction transaction,  String accountNumber,
                    String paymentGateway, double amount, String status, LocalDateTime dateIssued) {
        this.paymentID= paymentID;
        this.transactionID = transaction.getTransactionID();
        this.paymentMethodID = paymentMethod.getPaymentMethodID();
        this.accountNumber = accountNumber;
        this.paymentGateway= paymentGateway;
        this.amount = amount;
        this.status = status;
        this.dateIssued = dateIssued;
    }

    public String getPaymentID() {
        return paymentID;
    }
    
    public String getTransactionID() {
        return transactionID;
    }
    
    public String getPaymentMethodID() {
        return paymentMethodID;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
     
    public String getPaymentGateway() {
        return paymentGateway;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getStatus(){
        return status;
    }
    
    public LocalDateTime getDateIssued(){
        return dateIssued;
    }
    
    public void setPaymentID(String paymentID){
        this.paymentID = paymentID;
    }
           
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    
    public void setPaymentMethodID(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
     
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setStatus(String status) {
        this.status= status;
    }
    
    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

 
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s", paymentID, transactionID, paymentMethodID, accountNumber, paymentGateway, amount, status, dateIssued);
    }
    
}

    
    
    
    