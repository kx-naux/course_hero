package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SingleCoursePage", urlPatterns = {"/course"})
public class SingleCoursePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check is course id exist
        if (request.getParameter("id") == null) {
            // Forward the request to Course.jsp
            request.getRequestDispatcher("/WEB-INF/Client/CourseNotFound.jsp").forward(request, response);
        }

        // Forward the request to Course.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Course.jsp").forward(request, response);
    }

}
