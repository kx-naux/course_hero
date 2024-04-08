package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Home Page", urlPatterns = {"/home"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Home.jsp").forward(request, response);
    }
}
