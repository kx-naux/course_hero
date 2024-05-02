package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Sign Up Page", urlPatterns = {"/sign-up"})
public class SignUpPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check is user login, if yes goto home page else sign up page
        
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }

}
