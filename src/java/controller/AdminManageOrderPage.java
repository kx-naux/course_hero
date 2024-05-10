package controller;


import JPAEntity.Users;
import JPAEntity.Orders;
import JPAEntity.Product;
import JPAEntity.Transactions;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebServlet(name = "Admin Manage Order", urlPatterns = {"/admin/manage-order"})
public class AdminManageOrderPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        if(userData != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        //check has user logged in
        }else if(userDataSession == null){
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin","admin/manage-order");
            response.sendRedirect("../login");
            return;
        }
        
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
        //get userData from session as the user can login thru the rmbMe
        Users checkUserAccess = (Users) request.getSession().getAttribute("userData");
        if(checkUserAccess.getUsertype().equals("Customer")){
            ErrorPage.forwardToServerErrorPage(request, response, "Authorized Access Only ! ! !");
        }
        

        //Getting all of the parameters
        String status = request.getParameter("status");
        
        
        
        
        List<Transactions> allTransactions = em.createNamedQuery("Transactions.findAll").getResultList();
        Map<String, List<Product>> relatedTransProd = new HashMap<>();
        Map<String, List<Integer>> relatedTransQty = new HashMap<>();
        
        for(Transactions currentTransactions: allTransactions){
            List<Orders> currentOrders = em.createNamedQuery("Orders.findByTransactionId").setParameter("transactionId", currentTransactions.getTransactionId()).getResultList();
            List<Product> currentProduct = new ArrayList<>();
            List<Integer> currentQty = new ArrayList<>();
            for(Orders eachCurrentOrder: currentOrders){
                currentProduct.add(eachCurrentOrder.getProduct());
                currentQty.add(eachCurrentOrder.getQuantity());
            }
            relatedTransProd.put(currentTransactions.getTransactionId(), currentProduct);
            relatedTransQty.put(currentTransactions.getTransactionId(), currentQty);
        }
        
        request.setAttribute("allTransactions", allTransactions);
        request.setAttribute("relatedTransProd", relatedTransProd);
        request.setAttribute("relatedTransQty", relatedTransQty);

        
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageOrder.jsp").forward(request, response);
    }
}

