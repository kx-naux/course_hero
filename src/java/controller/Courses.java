package controller;

import JPAEntity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Courses", urlPatterns = {"/courses"})
public class Courses extends HttpServlet {
    @PersistenceContext EntityManager em;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                Users userData = Login.checkRmbMeToken(request, em);
        if(userData != null){
            HttpSession session = request.getSession(); 
            session.setAttribute("userData",userData);
            
            String optionName = request.getParameter("sort");
            if(optionName.equals("relevance")){
 
            }else if(optionName.equals("most-reviewed")){

            }else if(optionName.equals("highest-rated")){

            }else if(optionName.equals("newest")){

            }
            
        }else{
            
        }
               // Forward the request to Courses.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Courses.jsp").forward(request, response);
    }


}
