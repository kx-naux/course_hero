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
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        
        //Retrieve form data from request parameters
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = Double.parseDouble(request.getParameter("price"));
        int stockBalance = Integer.parseInt(request.getParameter("stockBalance"));
        int rateWeightage = Integer.parseInt(request.getParameter("rateWeightage"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        double dimensionHeight = Double.parseDouble(request.getParameter("dimensionHeight"));
        double dimensionWidth = Double.parseDouble(request.getParameter("dimensionWidth"));
        double dimensionLength = Double.parseDouble(request.getParameter("dimensionLength"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        
        String status = request.getParameter("status");
        String merchCategory = request.getParameter("merchCategory");

        
        //set id for each of the entity so that they can be stored in databases
        //get all of the current counts
        TablesRecordCounter merchCounter = em.find(TablesRecordCounter.class, "MERCHANDISE");
        TablesRecordCounter productCounter = em.find(TablesRecordCounter.class, "PRODUCT");
        
        
        //create new product obj
        ProductCategory merch = new ProductCategory("PC0000002");
        Product newProduct = new Product(String.valueOf(productCounter.getCounter() + 1), merch,productName, productDescription, price, rateWeightage, 0, discount, "", status);
       
        //create new merchandise obj
        MerchCategory merchCat = new MerchCategory(merchCategory);
        Merchandise newMerch = new Merchandise(String.valueOf(merchCounter.getCounter() + 1), newProduct, merchCat, dimensionHeight, dimensionWidth, dimensionLength, weight, stockBalance);
        
        //add one to each counter
        merchCounter.counterIncrementByOne();
        productCounter.counterIncrementByOne();

        List<TablesRecordCounter> recordsCounterList = new ArrayList<TablesRecordCounter>();
        recordsCounterList.add(merchCounter);
        recordsCounterList.add(productCounter);

        //persist the records
        saveDataToDatabases(request, response, newProduct, newMerch, recordsCounterList);
    }
    
    private void saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, Product newProduct, Merchandise newMerch, List<TablesRecordCounter> tablesRecordCounterList) throws ServletException, IOException {
        try {
            utx.begin();
            em.persist(newProduct);
            em.persist(newMerch);
            for (TablesRecordCounter data : tablesRecordCounterList) {
                em.merge(data);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                    try {
                        utx.rollback();
                    } catch (SystemException ex2) {
                        //server error page
                        ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
                    }
                }
            } catch (SystemException ex2) {
                ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
        }
    }
}

