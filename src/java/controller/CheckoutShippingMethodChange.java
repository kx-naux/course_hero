/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.ShippingMethod;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
// url "cout-ship-meth-chg"
public class CheckoutShippingMethodChange extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ErrorPage.forwardToServerErrorPage(request, response, "Invalid URL");
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String shippingMethodId = request.getParameter("shippingMethod");
        ShippingMethod selectedShip = em.find(ShippingMethod.class, shippingMethodId);
        HttpSession session = request.getSession();
        session.setAttribute("selectedShipping",selectedShip);
        response.sendRedirect("check-out");
    }

}
