/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.BillingAddress;
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
        HttpSession session = request.getSession();
        //-------------------------------------------------------------------------------
        //store billing address option    
        String selectedBillingAddress = request.getParameter("storedAddress");
        if(selectedBillingAddress!=null){
            session.setAttribute("selectedBillingInfo",selectedBillingAddress);
        }
        if(selectedBillingAddress!=null && selectedBillingAddress.equals("newAddress")){
            //store new billing address
            String addLine1 = request.getParameter("address1");
            if(addLine1 == null){
                addLine1 = "";
            }
            String addLine2 = request.getParameter("address2");
            if(addLine2 == null){
                addLine2 = "";
            }
            String city = request.getParameter("city");
            if(city == null){
                city = "";
            }
            String postalCode = request.getParameter("postalCode");
            if(postalCode == null){
                postalCode = "";
            }
            String state = request.getParameter("state");
            if(state == null){
                state = "";
            }
            String country = request.getParameter("country");
            if(country == null){
                country = "";
            }
            BillingAddress newBillData = new BillingAddress(addLine1,city,state,postalCode,country);
            newBillData.setLine2(addLine2);
            session.setAttribute("userInputBillAddressCheckout",newBillData);
        }
        
        //get is checked to update billing address
        if(request.getParameter("storingAddress")==null){
            session.setAttribute("chooseToUpdateBillAddress", false);
        }else{
            session.setAttribute("chooseToUpdateBillAddress", true);
        }
        
        //get selected payment method
        String selectedPaymentMethod = request.getParameter("paymentMethod");
        if(selectedPaymentMethod == null){
            selectedPaymentMethod = "";
        }
        session.setAttribute("selectedPaymentMethod", selectedPaymentMethod);
        //store selected payment details
        if(selectedPaymentMethod.equals("card")){
            String cardHolderName = request.getParameter("cardHolder");
            if(cardHolderName == null){
                cardHolderName = "";
            }
            session.setAttribute("cardHolderNameInput", cardHolderName);
            
            String cardNumber = request.getParameter("cardNo");
            if(cardNumber == null){
                cardNumber = "";
            }
            session.setAttribute("cardNumberInput", cardNumber);
            
            String expDate = request.getParameter("expDate");
            if(expDate == null){
                expDate = "";
            }
            session.setAttribute("expDateInput", expDate);
            
            String ccv = request.getParameter("ccv");
            if(ccv == null){
                ccv = "";
            }
            session.setAttribute("ccvInput", ccv);
            
        }else if(selectedPaymentMethod.equals("bank")){
            String selectedBankId = request.getParameter("selectedBank");
            if(selectedBankId==null){
                selectedBankId = "";
            }
            session.setAttribute("selectedOnlineBankId", selectedBankId);
            
            String selectedBankRemark = request.getParameter("bankRemark");
            if(selectedBankRemark == null){
                selectedBankRemark = "";
            }
            session.setAttribute("selectedOnlineBankRemark", selectedBankRemark);
            
        }else if(selectedPaymentMethod.equals("tng")){
            String tngNo = request.getParameter("tngPhoneNo");
            if(tngNo==null){
                tngNo = "";
            }
            session.setAttribute("tngNoInputUser", tngNo);
            
            String tngRemark = request.getParameter("tngRemark");
            if(tngRemark == null){
                tngRemark = "";
            }
            session.setAttribute("tngRemarkInput", tngRemark);
        }
        
        //get is check to update
        if(request.getParameter("storingPayment")==null){
            session.setAttribute("chooseToUpdateStoredPayment", false);
        }else{
            session.setAttribute("chooseToUpdateStoredPayment", true);
        }
        //-------------------------------------------------------------------------------
        session.removeAttribute("errMsg");
        session.removeAttribute("successMsg");
        String shippingMethodId = request.getParameter("shippingMethod");
        ShippingMethod selectedShip = em.find(ShippingMethod.class, shippingMethodId);
        session.setAttribute("selectedShipping",selectedShip);
        response.sendRedirect("check-out");
    }

}
