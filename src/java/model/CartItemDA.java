package model;
import entity.CartItem;
import java.sql.*;
import java.time.*;


public class CartItemDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "CART_ITEMS";
    private Connection conn;
    private PreparedStatement stmt;
    private ProductDA productDA;
    private UserDA userDA;
    
    public CartItemDA() throws SQLException{
        productDA = new ProductDA();
        userDA = new UserDA();
        createConnection();
    }
      
    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password);
    }
    
    public void shutDownConnection() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    //Create Retrieve Update Delete
    public void createRecord(CartItem cartItem) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, cartItem.getCartItemID());
        stmt.setString(2, cartItem.getProductID());
        stmt.setString(3, cartItem.getUserID());
        stmt.setString(4, String.valueOf(cartItem.getQuantity()));
        stmt.setString(5, cartItem.getTimeAdded().toString());
        
        stmt.executeUpdate();
    }
    
    public CartItem retrieveRecord(String cartItemID) throws SQLException{
        CartItem cartItem = null;
        String query = "SELECT * FROM " + tableName + " WHERE CARTITEM_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, cartItemID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            cartItem = new CartItem(rs.getString("CARTITEM_ID"), productDA.retrieveRecord(rs.getString("PRODUCT_ID")), userDA.retrieveRecord(rs.getString("USER_ID")), Integer.parseInt(rs.getString("QUANTITY")), LocalDateTime.parse(rs.getString("TIME_ADDED")));
        }
        return cartItem;
    }
    
    public void updateRecord(CartItem cartItem) throws SQLException{
        CartItem cartItemExist = retrieveRecord(cartItem.getCartItemID());
        if(cartItemExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PRODUCT_ID = ?, USER_ID = ?, QUANTITY = ?, TIME_ADDED = ? WHERE CARTITEM_ID = ?";
            stmt = conn.prepareStatement(query);
            
        stmt.setString(1, cartItem.getProductID());
        stmt.setString(2, cartItem.getUserID());
        stmt.setString(3, String.valueOf(cartItem.getQuantity()));
        stmt.setString(4, cartItem.getTimeAdded().toString());
        stmt.setString(5, cartItem.getCartItemID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(CartItem cartItem) throws SQLException{
        CartItem cartItemExist = retrieveRecord(cartItem.getCartItemID());
        if(cartItemExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE CARTITEM_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, cartItem.getCartItemID());
            
            stmt.executeUpdate();
        }
    }
}
