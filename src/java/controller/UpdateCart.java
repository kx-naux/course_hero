package controller;

import JPAEntity.CartItems;
import JPAEntity.CourseCategory;
import JPAEntity.Courses;
import JPAEntity.Product;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
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

public class UpdateCart extends HttpServlet {

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
        String courseID = jsonObject.get("productID").getAsString();
        String action = jsonObject.get("action").getAsString();
        int quantity = jsonObject.get("qty").getAsInt();

        // Get data from database
        // Course
        Courses currentCourse = em.find(Courses.class, courseID);
        String productID = currentCourse.getProductId().getProductId();
        String courseCatID = currentCourse.getCoursecatId().getCoursecatId();

        // Product
        Product currentProduct = em.find(Product.class, productID);
        String productName = currentProduct.getProdName();
        double productPrice = currentProduct.getPrice();
        String productImgPath = currentProduct.getImagePath();
        String productType = determineType(courseID);

        // Course Category
        CourseCategory currentCourseCat = em.find(CourseCategory.class, courseCatID);
        String courseCategoryName = currentCourseCat.getCategoryName();

        // Users
        Users userData = (Users) request.getSession().getAttribute("userData");
        String userID = userData.getUserId();

        // Time
        Date timeAdded = new Date();

        //Getting Counters for Cart Item
        TablesRecordCounter currentItemTableCounter = em.find(TablesRecordCounter.class, "CART_ITEMS");

        //Saving into the database
        Users currentUser = em.find(Users.class, userID);

        CartItems newCartItem = new CartItems();

        if ("add".equals(action)) {
            newCartItem.setProductId(currentProduct);
            newCartItem.setUserId(currentUser);
            newCartItem.setQuantity(quantity);
            newCartItem.setTimeAdded(timeAdded);
            newCartItem.setCartitemId(currentItemTableCounter.getCounter() + 1);
        } else if ("remove".equals(action)){
            List<CartItems> currentCartItem = em.createNamedQuery("CartItems.findByUserIdProductId").setParameter("userId", currentUser).setParameter("productId", currentProduct).getResultList();
            newCartItem = currentCartItem.get(0);
        }

        currentItemTableCounter.counterIncrementByOne();

        // response back to client
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("productID", courseID);
        responseJson.addProperty("action", action);
        responseJson.addProperty("productName", productName);
        responseJson.addProperty("quantity", quantity);
        responseJson.addProperty("productCategory", courseCategoryName);
        responseJson.addProperty("productImgPath", "./img/course/beginner_excel.jpg"); //Please change to productImgPath after inserting proper data
        responseJson.addProperty("productPrice", productPrice);
        responseJson.addProperty("productType", productType);
        responseJson.addProperty("status", "success");

        saveDataToDatabases(request, response, newCartItem, currentItemTableCounter, action);
        
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
                CartItems currentCartItems = em.find(CartItems.class, newCartItem.getCartitemId());
                em.remove(currentCartItems);
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

    public String determineType(String primaryKey) {
        if (primaryKey.startsWith("C")) {
            return "course";
        } else if (primaryKey.startsWith("M")) {
            return "merchandise";
        } else {
            return "Unknown";
        }
    }
}
