package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Home Page", urlPatterns = {"/home"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Home.jsp").forward(request, response);
    }
}