package controller;

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

