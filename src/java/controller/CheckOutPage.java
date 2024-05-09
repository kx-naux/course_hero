package controller;

import JPAEntity.CartItems;
import JPAEntity.ShippingMethod;
import JPAEntity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

//url 'check-out'
public class CheckOutPage extends HttpServlet {

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
        
        //get all shipping method
        Query query = em.createNamedQuery("ShippingMethod.findAll");
        List<ShippingMethod> shipMethodList = query.getResultList();
        request.setAttribute("shipMethodList", shipMethodList);
        
        
        
        // Forward the request to CheckOut.jsp
        request.getRequestDispatcher("/WEB-INF/Client/CheckOut.jsp").forward(request, response);
    }

}
