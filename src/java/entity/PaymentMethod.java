package entity;



public class PaymentMethod{ 
    
    private String paymentMethodID;
    private String paymentName;
    private String acceptedCurrencies;


    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodID) {
        this.paymentMethodID= paymentMethodID;
    }

    public PaymentMethod(String paymentMethodID, String paymentName, String acceptedCurrencies) {
        this.paymentMethodID = paymentMethodID;
        this.paymentName = paymentName;
        this.acceptedCurrencies = acceptedCurrencies;
    }

    public String getPaymentMethodID() {
        return paymentMethodID;
    }
    
    public String getPaymentName() {
        return paymentName;
    }
    
    public String getAcceptedCurrencies() {
        return acceptedCurrencies;
    }

    public void setPaymentMethodID(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public void setAcceptedCurrencies(String acceptedCurrencies) {
        this.acceptedCurrencies = acceptedCurrencies;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s", paymentMethodID, paymentName, acceptedCurrencies);
    }
    
}

    
    
    
    