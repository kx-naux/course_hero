package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.Authors;
import JPAEntity.CartItems;
import JPAEntity.Product;
import JPAEntity.Courses;
import JPAEntity.Keyword;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class SearchSuggestion extends HttpServlet {

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

        // Create the response JSON object
        JsonObject responseObject = new JsonObject();
        JsonArray keywordsArray = new JsonArray();
        JsonArray courses = new JsonArray();

        // Get JSON data
        String keyword = jsonObject.get("keyword").getAsString();

        // Check if it is a Course ID
        if (keyword.length() > 0) {
            if (isCourseID(keyword)) {
                responseObject.add("keyword", keywordsArray);
                Courses currentCourse = em.find(Courses.class, keyword);
                if (currentCourse != null) {
                    JsonObject course = new JsonObject();
                    course.addProperty("courseID", currentCourse.getCourseId());
                    course.addProperty("img", "./img/course/beginner_excel.jpg"); //currentCourse.getProductId().getImagePath()
                    course.addProperty("courseTitle", currentCourse.getProductId().getProdName());

                    List<Authors> currentCourseAuthor = getAuthorContributionName(currentCourse);

                    String concatAuthors = "";
                    for (int i = 0; i < currentCourseAuthor.size(); i++) {
                        concatAuthors += currentCourseAuthor.get(i).getAuthorName();
                        if (i < currentCourseAuthor.size() - 1) {
                            concatAuthors += ", ";
                        }
                    }

                    course.addProperty("courseAuthor", concatAuthors);
                    courses.add(course);
                }
                responseObject.add("course", courses);

            } else {

                List<String> matchedKeyword = getRelevantKeywords(keyword);
                for (String mKeyword : matchedKeyword) {
                    keywordsArray.add(mKeyword);
                }
                responseObject.add("keyword", keywordsArray);

                List<Product> matchingProducts = em.createNamedQuery("Product.findByProdNameFilter").setParameter("prodName", keyword).getResultList();
                int courseCounter = 0;
                List<Courses> matchingCourses = new ArrayList<>();

                for (Product currentMatchingProd : matchingProducts) {
                    List<Courses> currentMatchingCourses = em.createNamedQuery("Courses.findByProductId").setParameter("productId", currentMatchingProd).getResultList();
                    if (!currentMatchingCourses.isEmpty()) {
                        matchingCourses.add(currentMatchingCourses.get(0));
                        courseCounter++;
                    }
                    if (courseCounter == 3) {
                        break;
                    }
                }

                for (Courses mCourses : matchingCourses) {
                    JsonObject course = new JsonObject();
                    course.addProperty("courseID", mCourses.getCourseId());
                    course.addProperty("img", "./img/course/beginner_excel.jpg"); //mCourses.getProductId().getImagePath()
                    course.addProperty("courseTitle", mCourses.getProductId().getProdName());

                    List<Authors> currentCourseAuthor = getAuthorContributionName(mCourses);

                    String concatAuthors = "";
                    for (int i = 0; i < currentCourseAuthor.size(); i++) {
                        concatAuthors += currentCourseAuthor.get(i).getAuthorName();
                        if (i < currentCourseAuthor.size() - 1) {
                            concatAuthors += ", ";
                        }
                    }

                    course.addProperty("courseAuthor", concatAuthors);

                    courses.add(course);
                }
                responseObject.add("course", courses);
            }

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

    private boolean isCourseID(String courseID) {
        String regex = "^CR\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(courseID);

        return matcher.find();
    }

    private List<Authors> getAuthorContributionName(Courses currentCourse) {
        List<Authors> author = new ArrayList<>();

        List<AuthorContribution> rawAuthorContributions = em.createNamedQuery("AuthorContribution.findByCourseId").setParameter("courseId", currentCourse).getResultList();
        for (AuthorContribution eachRawAuthor : rawAuthorContributions) {
            author.add(eachRawAuthor.getAuthorId());
        }

        return author;
    }

    private List<String> getRelevantKeywords(String keyword) {
        List<String> matchingKeywords = new ArrayList<>();
        List<Keyword> allKeywords = em.createNamedQuery("Keyword.findAll").getResultList();

        int currentSize = 0;
        for (Keyword currentKeyword : allKeywords) {
            if (currentKeyword.getKeyword().toLowerCase().contains(keyword.toLowerCase()) && currentSize <= 3) {
                matchingKeywords.add(currentKeyword.getKeyword());
                currentSize++;
            }
        }

        return matchingKeywords;
    }
}
