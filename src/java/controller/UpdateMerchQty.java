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

public class UpdateMerchQty extends HttpServlet {

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
        String cartID = jsonObject.get("cartID").getAsString();
        int qty = jsonObject.get("qty").getAsInt();

        // Create the response JSON object
        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("staus", "success");

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
