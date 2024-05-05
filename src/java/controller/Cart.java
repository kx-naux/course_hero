package controller;

import JPAEntity.CartItems;
import JPAEntity.Product;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author wooyu
 */
public class Cart extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the request body
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        
        // Parse JSON string
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody.toString(), JsonObject.class);
        
        // Get JSON data
        String productID = jsonObject.get("productID").getAsString();
        String userID = jsonObject.get("userID").getAsString();
        int quantity = jsonObject.get("quantity").getAsInt();
        String productType = jsonObject.get("productType").getAsString();
        String actionType = jsonObject.get("actionType").getAsString();
        Date timeAdded = new Date();
               
        //Getting Counters for Cart Item
        TablesRecordCounter currentItemTableCounter = em.find(TablesRecordCounter.class, "TablesRecordCounter");
        
        //Saving into the database
        Product currentProduct = em.find(Product.class, productID);
        Users currentUser = em.find(Users.class, userID);
        
        CartItems newCartItem = new CartItems();
        
        newCartItem.setCartitemId(currentItemTableCounter.getCounter() + 1);
        newCartItem.setProductId(currentProduct);
        newCartItem.setUserId(currentUser);
        newCartItem.setQuantity(quantity);
        newCartItem.setTimeAdded(timeAdded);
        
        currentItemTableCounter.counterIncrementByOne();
        
        //Creating JSON data
        JsonObject newJsonObject = new JsonObject();
        newJsonObject.addProperty("ProductID", currentProduct.getProductId());
        newJsonObject.addProperty("ProductCategory", productType);
        if("Add".equals(actionType)){
            newJsonObject.addProperty("ProductName", currentProduct.getProdName());
            newJsonObject.addProperty("ProductPrice", currentProduct.getPrice());
            newJsonObject.addProperty("Quantity", quantity);
            newJsonObject.addProperty("ProductCategory", currentProduct.getProdcatId().getCategoryName());
            newJsonObject.addProperty("ProductImage", "TemporaryVar");
        }
        
        saveDataToDatabases(request, response, newCartItem, currentItemTableCounter, actionType);
    }
    
    public void saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, CartItems newCartItem, TablesRecordCounter currentItemTableCounter, String actionType) throws ServletException, IOException{
        try{
            if("Add".equals(actionType)){
                utx.begin();
                em.persist(newCartItem);
                em.merge(currentItemTableCounter);
                utx.commit();
            }else if("Remove".equals(actionType)){
               utx.begin();
               //WIP
               utx.commit();
            }
        }catch(Exception ex){
            try{
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                    try {
                        utx.rollback();
                    }catch (SystemException ex2) {
                        //server error page
                        ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
                    }
                }
            }catch (SystemException ex2){
                ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
        }
    }
    
}