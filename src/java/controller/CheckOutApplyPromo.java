/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Promotions;
import JPAEntity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author User
 */
// url 'check-out-apply-promo'
public class CheckOutApplyPromo extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction utx;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ErrorPage.forwardToServerErrorPage(request, response, "Invalid URL");
        
        //Users userDataSession = (Users) request.getSession().getAttribute("userData");
        //Users userDataRmbMe = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        //if(userDataRmbMe != null){
        //    HttpSession session = request.getSession();
        //    session.setAttribute("userData",userDataRmbMe);
        //check has user logged in
        //}else if(userDataSession.getUserId() == null){
        //    HttpSession session = request.getSession();
        //    session.setAttribute("pageToGoAfterLogin","cart");
        //    response.sendRedirect("login");
        //    return;

        //}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("errMsg");
        session.removeAttribute("successMsg");
        
        double itemsTotalAfterDiscount = Double.parseDouble((String)session.getAttribute("itemsTotalAfterDiscount"));
        
        String promoCodeInput = request.getParameter("promo");
        Query query = em.createNamedQuery("Promotions.findByPromoCode");
        query.setParameter("promoCode", promoCodeInput);
        
        List<Promotions> promoApplied = query.getResultList();
        if(!promoApplied.isEmpty()){
            if(promoApplied.get(0).getEndTime().compareTo(new Date()) > 0){
                if(itemsTotalAfterDiscount >= promoApplied.get(0).getMinReq()){
                    session.setAttribute("promoApplied", promoApplied.get(0));
                    session.setAttribute("successMsg","Promo Code Applied");
                }else{
                    session.setAttribute("errMsg", "Min Requirement does not match");
                }             
            }else{
                session.setAttribute("errMsg", "Invalid Promo Code");
            }
        }else{
            session.setAttribute("errMsg", "Invalid Promo Code");
        }
        
        response.sendRedirect("check-out");
        
    }

  
}
