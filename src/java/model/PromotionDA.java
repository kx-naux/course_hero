package model;
import entity.Promotion;
import java.sql.*;
import java.time.*;

public class PromotionDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "PROMOTIONS";
    private Connection conn;
    private PreparedStatement stmt;
    
    public PromotionDA() throws SQLException{
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
    public void createRecord(Promotion promotion) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, promotion.getPromotionID());
        stmt.setString(2, promotion.getStartTime().toString());
        stmt.setString(3, promotion.getEndTime().toString());
        stmt.setString(4, promotion.getPromoType());
        stmt.setString(5, String.valueOf(promotion.getamount()));
        stmt.setString(6, String.valueOf(promotion.getMinReq()));
        stmt.setString(7, promotion.getLegalTNC());
        stmt.setString(8, promotion.getStatus());
        stmt.setString(9, promotion.getGeographicRestriction());
        
        stmt.executeUpdate();
    }
    
    public Promotion retrieveRecord(String promotionID) throws SQLException{
        Promotion promotion = null;
        String query = "SELECT * FROM " + tableName + " WHERE PROMOTION_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, promotionID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            promotion = new Promotion(rs.getString("PROMOTION_ID"), LocalDateTime.parse(rs.getString("START_TIME")), LocalDateTime.parse(rs.getString("END_TIME")), rs.getString("PROMO_TYPE"), Double.parseDouble(rs.getString("AMOUNT")), Double.parseDouble(rs.getString("MIN_REQ")), rs.getString("LEGAL_TNC"), rs.getString("STATUS"), rs.getString("GEOGRAPHIC_RESTRICTION"));
        }
        
        return promotion;
    }
    
    public void updateRecord(Promotion promotion) throws SQLException{
        Promotion promotionExist = retrieveRecord(promotion.getPromotionID());
        if(promotionExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET START_TIME = ?, END_TIME = ?, PROMO_TYPE = ?, AMOUNT = ?, MIN_REQ = ?, LEGAL_TNC = ?, STATUS = ?, GEOGRAPHIC_RESTRICTION = ? WHERE SHIPPING_ID = ?";
            stmt = conn.prepareStatement(query);
            
        
        stmt.setString(1, promotion.getStartTime().toString());
        stmt.setString(2, promotion.getEndTime().toString());
        stmt.setString(3, promotion.getPromoType());
        stmt.setString(4, String.valueOf(promotion.getamount()));
        stmt.setString(5, String.valueOf(promotion.getMinReq()));
        stmt.setString(6, promotion.getLegalTNC());
        stmt.setString(7, promotion.getStatus());
        stmt.setString(8, promotion.getGeographicRestriction());
        stmt.setString(9, promotion.getPromotionID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Promotion promotion) throws SQLException{
        Promotion promotionExist = retrieveRecord(promotion.getPromotionID());
        if(promotionExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE PROMOTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, promotion.getPromotionID());
            
            stmt.executeUpdate();
        }
    }
    
}
