package controller;

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
    
     public static double roundUpToTwoDecimalPlaces(double value) {
        value *= 100;
        double roundedValue = Math.ceil(value);
        roundedValue /= 100;
        return roundedValue;
    }

}
