package controller;

import JPAEntity.CartItems;
import JPAEntity.Courses;
import JPAEntity.Merchandise;
import JPAEntity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
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


// url "/cart"
public class CartPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userDataRmbMe = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        if(userDataRmbMe != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userDataRmbMe);
        //check has user logged in
        }else if(userDataSession.getUserId() == null){
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin","cart");
            response.sendRedirect("login");
            return;

        }
        //get all cart items by userId
        Query qry = em.createNamedQuery("CartItems.findByUserId");
        qry.setParameter("userId",userDataSession);
        List<CartItems> cartItems =  qry.getResultList();
        request.setAttribute("cartItems",cartItems);
        
        //get all course List in cart
        List<Courses> courseList = new ArrayList<Courses>();
        for(CartItems item:cartItems){
            qry = em.createNamedQuery("Courses.findByProductId");
            qry.setParameter("productId", item.getProductId());
            if(!qry.getResultList().isEmpty()){
                courseList.addAll(qry.getResultList());
            }
        }
        request.setAttribute("courseList",courseList);
        
        //get merchandise List in cart
        List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
        for(CartItems item:cartItems){
            qry = em.createNamedQuery("Merchandise.findByProductId");
            qry.setParameter("productId", item.getProductId());
            if(!qry.getResultList().isEmpty()){
                merchandiseList.addAll(qry.getResultList());
            }
        }
        request.setAttribute("merchandiseList",merchandiseList);
        
        //pass all data (including number items for course + merchandise)
        request.setAttribute("numberOfCourse",courseList.size());
        request.setAttribute("numberOfMerch",merchandiseList.size());
        
        request.getRequestDispatcher("/WEB-INF/Client/Cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] checkingOutcartItemIds = request.getParameterValues("cartItemId");
        
        // Check if there were any items selected to check out
        HttpSession session = request.getSession();
        if (checkingOutcartItemIds != null) {
            //store cart items in session
            List<CartItems> cartItemList = new ArrayList<CartItems>();
                for(String checkOutItemId : checkingOutcartItemIds){
                CartItems item = em.find(CartItems.class, checkOutItemId);
                cartItemList.add(item);
            }
            session.setAttribute("checkingOutCartItemList", cartItemList);
            response.sendRedirect("check-out");
        }else{
            request.setAttribute("errMsg","No cart items were selected.");
            doGet(request,response);
        }
    }
    
    
}
