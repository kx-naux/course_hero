package controller;

import JPAEntity.CartItems;
import JPAEntity.Product;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
        
        JsonArray reviews = new JsonArray();
        for (int i = 0; i < 3; i++) {
            JsonObject review = new JsonObject();
            review.addProperty("userName", "Woo Yu Beng");
            review.addProperty("rating", 4);
            review.addProperty("ratingDate", "29/4/2024");
            review.addProperty("comments", "Yu Beng have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.");
            review.addProperty("userImgPath", "./img/user/default.png");
            reviews.add(review);
        }

        // response back to client
        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", "success");
        responseObject.add("reviews", reviews);
        responseObject.addProperty("isMore", true);
        
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
