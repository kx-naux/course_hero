package controller;

import JPAEntity.BillingAddress;
import JPAEntity.CartItems;
import JPAEntity.OnlineBankingInfo;
import JPAEntity.Promotions;
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
import java.text.DecimalFormat;

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
        
        //get all banking method
        Query query = em.createNamedQuery("OnlineBankingInfo.findAll");
        List<OnlineBankingInfo> oBankNameList = query.getResultList();
        request.setAttribute("oBankNameList",oBankNameList);
 
        //calculation
        int totalItemsCheckout = 0;
        double itemsSubtotal = 0;
        double itemsTotalDiscount = 0;
        double itemsTotalAfterDiscount = 0;
        double shippingDiscount = 0;
        double promoDiscount = 0;
        double paymentTotal = 0;
        double paymentTax = 0;
        
        for(CartItems item : checkingOutCartItemList){
            totalItemsCheckout += item.getQuantity();
            itemsSubtotal += item.getQuantity() * item.getProductId().getPrice();
            itemsTotalDiscount += item.getQuantity() * item.getProductId().getDiscount();
        }
        itemsTotalAfterDiscount = itemsSubtotal - itemsTotalDiscount;
        
        //-shiping discount
        if(itemsTotalAfterDiscount > 200){
            shippingDiscount = 25;
        }
        
        //+shiping method charges
        ShippingMethod selectedShipping = (ShippingMethod) session.getAttribute("selectedShipping");
        
        //-promotion applied
        Promotions promoApplied = (Promotions) session.getAttribute("promoApplied");
        if(promoApplied != null){
            promoDiscount = promoApplied.getAmount();
        }
        
        //+ tax (6%)
        paymentTax = itemsTotalAfterDiscount * 0.06;
        paymentTax = roundUpToTwoDecimalPlaces(paymentTax);
        
        //cal payment total
        paymentTotal = itemsTotalAfterDiscount - shippingDiscount + selectedShipping.getShippingRates() - promoDiscount + paymentTax + 25;
        
        
        // all attribute below are set in String
        session.setAttribute("totalItemsCheckout",totalItemsCheckout + "");
        session.setAttribute("itemsSubtotal",String.format("%.2f", itemsSubtotal));
        session.setAttribute("itemsTotalDiscount",String.format("%.2f", itemsTotalDiscount));
        session.setAttribute("itemsTotalAfterDiscount",String.format("%.2f", itemsTotalAfterDiscount));
        session.setAttribute("shippingDiscount",String.format("%.2f", shippingDiscount));
        session.setAttribute("promoDiscount",String.format("%.2f", promoDiscount));
        session.setAttribute("paymentTotal",String.format("%.2f", paymentTotal));
        session.setAttribute("paymentTax",String.format("%.2f",paymentTax));
        
        // Forward the request to CheckOut.jsp

        request.getRequestDispatcher("/WEB-INF/Client/CheckOut.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("errMsg");
        session.removeAttribute("successMsg");
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
        if(selectedPaymentMethod.equals("bank")){
            String test = request.getParameter("selectedBank");
            OnlineBankingInfo oBankInfo = em.find(OnlineBankingInfo.class,request.getParameter("selectedBank"));
            session.setAttribute("selectedBankIdReq",oBankInfo);
        }
        
        response.sendRedirect("check-out-review");
    }
    
    
    
    public static double roundUpToTwoDecimalPlaces(double value) {
        value *= 100;
        double roundedValue = Math.ceil(value);
        roundedValue /= 100;
        return roundedValue;
    }

}
