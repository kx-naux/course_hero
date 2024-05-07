package controller;

import JPAEntity.CartItems;
import JPAEntity.CourseCategory;
import JPAEntity.Courses;
import JPAEntity.MerchCategory;
import JPAEntity.Merchandise;
import JPAEntity.Product;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        String courseOrmerchandiseID = jsonObject.get("productID").getAsString();
        String action = jsonObject.get("action").getAsString();

        // MAIN PROCESS
        Users userData = (Users) request.getSession().getAttribute("userData");
        String userID = userData.getUserId();

        // Time
        Date timeAdded = new Date();

        Courses currentCourse;
        String courseCatID;
        CourseCategory currentCourseCat;
        String courseCategoryName = "";

        Merchandise currentMerchandise;
        String merchCatID;
        MerchCategory currentMerchCat;
        String merchCategoryName = "";

        Product currentProduct = new Product();
        String productName = "";
        double productPrice = 0.0;
        String productImgPath = "";
        String productID = "";

        // Course or Merchandise
        String productType = determineType(courseOrmerchandiseID);
        if (productType.equals("course")) {
            // Course
            currentCourse = em.find(Courses.class, courseOrmerchandiseID);
            productID = currentCourse.getProductId().getProductId();
            courseCatID = currentCourse.getCoursecatId().getCoursecatId();

            // Course Category
            currentCourseCat = em.find(CourseCategory.class, courseCatID);
            courseCategoryName = currentCourseCat.getCategoryName();

            // Product
            currentProduct = em.find(Product.class, productID);
            productName = currentProduct.getProdName();
            productPrice = currentProduct.getPrice();
            productImgPath = currentProduct.getImagePath();
        } else if (productType.equals("merchandise")) {
            // Merchandise
            currentMerchandise = em.find(Merchandise.class, courseOrmerchandiseID);
            productID = currentMerchandise.getProductId().getProductId();
            merchCatID = currentMerchandise.getMerchcatId().getMerchcatId();

            // Merchandise Category
            currentMerchCat = em.find(MerchCategory.class, merchCatID);
            merchCategoryName = currentMerchCat.getCategoryName();

            // Product
            currentProduct = em.find(Product.class, productID);
            productName = currentProduct.getProdName();
            productPrice = currentProduct.getPrice();
            productImgPath = currentProduct.getImagePath();
        }

        //Getting Counters for Cart Item
        //TablesRecordCounter currentItemTableCounter = em.find(TablesRecordCounter.class, "CART_ITEMS");
        
        //Saving into the database
        Users currentUser = em.find(Users.class, userID);
        
        /*Create the wishlist object, get it from zhan liang*/
        
        //currentItemTableCounter.counterIncrementByOne();
        
        // response back to client
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("productID", courseOrmerchandiseID);
        responseJson.addProperty("action", action);
        responseJson.addProperty("productName", productName);
        responseJson.addProperty("productImgPath", "./img/course/beginner_excel.jpg"); //Please change to productImgPath after inserting proper data
        responseJson.addProperty("productPrice", productPrice);
        responseJson.addProperty("productType", productType);
        responseJson.addProperty("status", "success");
        if (productType.equals("course")) {
            responseJson.addProperty("productCategory", courseCategoryName);
        } else if (productType.equals("merchandise")) {
            responseJson.addProperty("productCategory", merchCategoryName);
        }

        //saveDataToDatabases(request, response, newCartItem, currentItemTableCounter, actionType);


        // END OF MAIN PROCESS
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
