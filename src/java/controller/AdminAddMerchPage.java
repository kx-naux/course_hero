package controller;

import JPAEntity.MerchCategory;
import JPAEntity.Merchandise;
import JPAEntity.Product;
import JPAEntity.ProductCategory;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "Admin Add Merch", urlPatterns = {"/admin/add-merch"})
public class AdminAddMerchPage extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

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
            session.setAttribute("pageToGoAfterLogin","admin/add-merch");
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


        //To get all Merchandise Category
        Query merchandiseCatListQry = em.createNamedQuery("MerchCategory.findAll");
        List<MerchCategory> merchandiseCatList = merchandiseCatListQry.getResultList();
        request.setAttribute("merchandiseCatList", merchandiseCatList);
        
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminAddMerch.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String targetMerchId = (String) request.getParameter("merchId");
        String newProductName = (String) request.getParameter("productName");
        String newMerchCategory = (String) request.getParameter("merchCategory");
        double newPrice = Double.parseDouble(request.getParameter("price"));
        int newStockBalance = Integer.parseInt(request.getParameter("stockBalance"));
        String newStatus = (String)request.getParameter("status");
        
        
        //get user object
        HttpSession session = request.getSession();
        Merchandise updateMerchandise = em.find(Merchandise.class, targetMerchId);
        Product updateProduct = updateMerchandise.getProductId();
        MerchCategory updateMerchCategory = updateMerchandise.getMerchcatId();
        
        
        //set data product
        updateProduct.setProdName(newProductName);
        updateProduct.setPrice(newPrice);
        updateProduct.setStatus(newStatus);
        
        //set data merch
        updateMerchandise.setMerchcatId(updateMerchCategory);
        updateMerchandise.setStockBalance(newStockBalance);
        
        
        updateDataInDatabase(updateMerchandise, updateProduct, request, response);
        
        //request.getRequestDispatcher("/WEB-INF/Admin/AdminManageMerch.jsp").forward(request, response);
        response.sendRedirect("manage-merch");
    }
    
    private void updateDataInDatabase(Merchandise updateMerchandise, Product updateProduct, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            utx.begin();
            em.merge(updateMerchandise);
            em.merge(updateProduct);
            utx.commit();
        }catch(Exception ex){
            try{
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                try {
                    utx.rollback();
                }catch (SystemException ex2) {
                    //server error page
                    ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
                }
            }
            }catch (SystemException ex2){
                ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
        }
    }
}

