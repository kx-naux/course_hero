package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Admin Add Course", urlPatterns = {"/admin/add-course"})
public class AdminAddCoursePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminAddCourse.jsp").forward(request, response);
    }
}

