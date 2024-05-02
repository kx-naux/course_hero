package controller;

import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebServlet(name = "Home Page", urlPatterns = {"/home"})
public class HomePage extends HttpServlet {
    @PersistenceContext EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Users userData = Login.checkRmbMeToken(request, em);
        if(userData != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userData);
        }else{
            
        }
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Home.jsp").forward(request, response);
    }
}