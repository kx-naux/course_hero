package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "About Us Page", urlPatterns = {"/about-us"})
public class AboutUsPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        // Forward the request to AboutUs.jsp
        request.getRequestDispatcher("/WEB-INF/Client/AboutUs.jsp").forward(request, response);
    }
}