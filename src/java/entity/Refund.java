package entity;

public class Refund {
    private String refundID;
    private String transactionID;
    private String refundType;
    private String reason;

    // Constructor - empty
    public Refund() {
    }

    // Constructor - fully parameterized
    public Refund(String refundID, Transaction transaction, String refundType, String reason) {
        this.refundID = refundID;
        this.transactionID = transaction.getTransactionID();
        this.refundType = refundType;
        this.reason = reason;
    }

    // Getters
    public String getRefundID() {
        return refundID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getRefundType() {
        return refundType;
    }

    public String getReason() {
        return reason;
    }

    // Setters
    public void setRefundID(String refundID) {
        this.refundID = refundID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}