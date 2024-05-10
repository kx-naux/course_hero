package controller;

import JPAEntity.CartItems;
import JPAEntity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

public class CheckOutReviewPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItems> checkingOutCartItemList = (List<CartItems>) session.getAttribute("checkingOutCartItemList");
        if(checkingOutCartItemList == null){
            ErrorPage.forwardToServerErrorPage(request, response, "Please proceed check out from cart page.");
        }   
        // Forward the request to Merchandises.jsp
        request.getRequestDispatcher("/WEB-INF/Client/CheckOutReview.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formAction = request.getParameter("formAction");
        HttpSession session = request.getSession();
        if(formAction.equals("back")){
            response.sendRedirect("check-out");
            return;
        }else if(formAction.equals("confirm")){
            //get all data first 

            //check which address is used
            //if new create new billing address object
            
            //create shipping object
            
            //create new transaction
            
            //create new payment
            
            //create new order list
            
            //check need update payment on user onot
            
            //check need upadate billing onot
            
            
            //proceed save to databases
        }
    }

}
