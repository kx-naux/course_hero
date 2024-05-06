package controller;

import JPAEntity.CartItems;
import JPAEntity.TablesRecordCounter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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

public class UpdateWishlist extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retreive from js submission
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
        String action = jsonObject.get("action").getAsString();
        int quantity = jsonObject.get("qty").getAsInt();
        
//        String userID = jsonObject.get("userID").getAsString();
//        String productType = jsonObject.get("productType").getAsString();
//        String actionType = jsonObject.get("actionType").getAsString();
//        Date timeAdded = new Date();
//        
//        // process the data
//               
//        //Getting Counters for Cart Item
//        TablesRecordCounter currentItemTableCounter = em.find(TablesRecordCounter.class, "TablesRecordCounter");
//        
//        //Saving into the database
//        Product currentProduct = em.find(Product.class, productID);
//        Users currentUser = em.find(Users.class, userID);
//        
//        CartItems newCartItem = new CartItems();
//        
//        newCartItem.setCartitemId(currentItemTableCounter.getCounter() + 1);
//        newCartItem.setProductId(currentProduct);
//        newCartItem.setUserId(currentUser);
//        newCartItem.setQuantity(quantity);
//        newCartItem.setTimeAdded(timeAdded);
//        
//        currentItemTableCounter.counterIncrementByOne();
//        
//        //Creating JSON data
//        JsonObject newJsonObject = new JsonObject();
//        newJsonObject.addProperty("ProductID", currentProduct.getProductId());
//        newJsonObject.addProperty("ProductCategory", productType);
//        if("Add".equals(actionType)){
//            newJsonObject.addProperty("ProductName", currentProduct.getProdName());
//            newJsonObject.addProperty("ProductPrice", currentProduct.getPrice());
//            newJsonObject.addProperty("Quantity", quantity);
//            newJsonObject.addProperty("ProductCategory", currentProduct.getProdcatId().getCategoryName());
//            newJsonObject.addProperty("ProductImage", "TemporaryVar");
//        }
//        
//        saveDataToDatabases(request, response, newCartItem, currentItemTableCounter, actionType);

        // response back to client
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("status", "success");
        responseJson.addProperty("action", action);
        responseJson.addProperty("productID", productID);
        responseJson.addProperty("productType", "course");
        responseJson.addProperty("productName", "The Ultimate Excel Programming");
        responseJson.addProperty("productCategory", "Microsoft Excel");
        responseJson.addProperty("productImgPath", "./img/course/beginner_excel.jpg");
        responseJson.addProperty("productPrice", 20.00);
        responseJson.addProperty("productQty", 1);

        // Convert JSON object to string
        String responseJsonString = responseJson.toString();

        // Set content type and status code for the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // Write JSON string to response
        PrintWriter out = response.getWriter();
        out.print(responseJsonString);
        out.flush();
    }

    public void saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, CartItems newCartItem, TablesRecordCounter currentItemTableCounter, String actionType) throws ServletException, IOException {
        try {
            if ("add".equals(actionType)) {
                utx.begin();
                em.persist(newCartItem);
                em.merge(currentItemTableCounter);
                utx.commit();
            } else if ("remove".equals(actionType)) {
                utx.begin();
                em.remove(newCartItem);
                utx.commit();
            }
        } catch (Exception ex) {
            try {
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                    try {
                        utx.rollback();
                    } catch (SystemException ex2) {
                        //server error page
                        ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
                    }
                }
            } catch (SystemException ex2) {
                ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
        }
    }
}
