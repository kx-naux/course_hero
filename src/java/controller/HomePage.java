package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import module.CollaborateLogos;

@WebServlet(name = "Home Page", urlPatterns = {"/home"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // Set the attributes in the request
        request.setAttribute("companies", CollaborateLogos.getCompanies());
        request.setAttribute("universities", CollaborateLogos.getUniversities());
        
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Home.jsp").forward(request, response);
    }
}
