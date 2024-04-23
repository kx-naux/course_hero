package entity;

public class Order { 
    
    private String transactionID;
    private String productID;
    private int quantity;

    public Order() {
    }
    
    public Order(Transaction transaction, Product product, int quantity) {
        this.transactionID =transaction.getTransactionID();
        this.productID = product.getProductID();
        this.quantity = quantity;
    }

    public String getTransactionID() {
        return transactionID;
    }
    
    public String getProductID() {
        return productID;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s", transactionID, productID, quantity);
    }
    
}

    
    
    
    