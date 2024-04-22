package entity;
import java.time.LocalDateTime;


public class CartItem{ 
    
    private String cartItemID;
    private String productID;
    private String userID;
    private int quantity;
    private LocalDateTime timeAdded;

    public CartItem() {
    }

    public CartItem(String cartItemID, Product product, User user, int quantity,
                    LocalDateTime timeAdded) {
        this.cartItemID = cartItemID;
        this.productID = product.getProductID();
        this.userID = user.getUserID();
        this.quantity = quantity;
        this.timeAdded = timeAdded;
    }

    public String getCartItemID() {
        return cartItemID;
    }
    
    public String getProductID() {
        return productID;
    }
   
    public String getUserID() {
        return userID;
    }
     
    public int getQuantity() {
        return quantity;
    }
    
    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }
           
    public void setCartItemID(String cartItemID) {
        this.cartItemID = cartItemID;
    }
    
    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
     
    public void setTimeAdded(LocalDateTime timeAdded) { 
        this.timeAdded = timeAdded;
    }
   
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", cartItemID, productID, userID, quantity, timeAdded);
    }
    
}

    
    
    
    