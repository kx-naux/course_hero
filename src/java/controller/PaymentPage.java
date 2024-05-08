package controller;

import JPAEntity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

public class PaymentPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users userData = Login.checkRmbMeToken(request, em);
        if (userData != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userData", userData);
        } else {
            //Statements
        }

        // Forward the request to Merchandises.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Payment.jsp").forward(request, response);
    }

}
