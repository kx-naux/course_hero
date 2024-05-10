package controller;

import JPAEntity.MerchCategory;
import JPAEntity.Merchandise;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@WebServlet(name = "Admin Manage Customer", urlPatterns = {"/admin/manage-customer"})
public class AdminManageCustomerPage extends HttpServlet {
    
    @PersistenceContext
    EntityManager em;

    @Resource UserTransaction utx;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        //To get all Merchandise Record
        Query merchandiseListQry = em.createNamedQuery("Merchandise.findAll");
        List<Merchandise> merchandiseList = merchandiseListQry.getResultList();
        request.setAttribute("merchandiseList", merchandiseList);

        //To get all Merchandise Category
        Query merchandiseCatListQry = em.createNamedQuery("MerchCategory.findAll");
        List<MerchCategory> merchandiseCatList = merchandiseCatListQry.getResultList();
        request.setAttribute("merchandiseCatList", merchandiseCatList);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageCustomer.jsp").forward(request, response);
    }
}