package controller;

import JPAEntity.CartItems;
import JPAEntity.Courses;
import JPAEntity.Product;
import JPAEntity.Ratings;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class GetCourseReview extends HttpServlet {

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
        String courseID = jsonObject.get("courseID").getAsString();
        String lastID = jsonObject.get("lastID").getAsString();
        int submitCount = jsonObject.get("submitCount").getAsInt();
        Courses courseRetrieved = em.find(Courses.class, courseID);
        //set offset limit  
        int offset = submitCount * 5;
        int maxDataInPage = 5;

        //get rating count
        Query getRateCountQuery = em.createNamedQuery("Ratings.findRatingCountByProdId");
        getRateCountQuery.setParameter("productId", courseRetrieved.getProductId());
        long ratingCount = (long) getRateCountQuery.getSingleResult();

        //find max submitCount
        long lastPage = (ratingCount - 1) / Long.parseLong(String.valueOf(maxDataInPage));

//        Query getRatings = em.createNamedQuery("Ratings.findRatingByCourseIdSortDescPriKey");
        Query getRatings = em.createNamedQuery("Ratings.findRatingByProdIdSortDescPriKey");
        getRatings.setParameter("productId", courseRetrieved.getProductId());
        getRatings.setMaxResults(maxDataInPage);
        getRatings.setFirstResult(offset);
        List<Ratings> ratingsList = getRatings.getResultList();
        JsonArray reviews = new JsonArray();

        String base64ImageData = "";

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        for(Ratings rating: ratingsList){
            JsonObject review = new JsonObject();
            Users user = rating.getUserId();
            review.addProperty("userName", user.getDisplayName());
            review.addProperty("rating", rating.getScore());
            review.addProperty("ratingDate", dateFormatter.format(rating.getTimeRated()));
            review.addProperty("comments", rating.getComment());
            if (user.getImgData() != null) {
                base64ImageData = Base64.getEncoder().encodeToString((byte[])user.getImgData());
            }else{
                base64ImageData = "./img/user/default.png";
            }
            review.addProperty("userImgPath", base64ImageData);
            reviews.add(review);
        }
        
//        JsonArray reviews = new JsonArray();
//        for (int i = 0; i < 3; i++) {
//            JsonObject review = new JsonObject();
//            review.addProperty("userName", "Woo Yu Beng");
//            review.addProperty("rating", 4);
//            review.addProperty("ratingDate", "29/4/2024");
//            review.addProperty("comments", "Yu Beng have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.");
//            review.addProperty("userImgPath", "./img/user/default.png");
//            reviews.add(review);
//        }

        // response back to client
        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", "success");
        responseObject.add("reviews", reviews);

        //if end need put false
        int totalPageCount = (int) Math.ceil((double) ratingCount / maxDataInPage);
        boolean thisIsLastPage = (submitCount >= totalPageCount);
        if (thisIsLastPage) {
            responseObject.addProperty("isMore", false);
        } else {
            responseObject.addProperty("isMore", true);
        }
        responseObject.addProperty("submitCount", submitCount + 1);

        // Convert JSON object to string
        String responseJsonString = responseObject.toString();

        // Set content type and status code for the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // Write JSON string to response
        PrintWriter out = response.getWriter();
        out.print(responseJsonString);
        out.flush();
    }
}
