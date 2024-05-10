package controller;

import JPAEntity.Bankcardinfo;
import JPAEntity.BillingAddress;
import JPAEntity.CartItems;
import JPAEntity.CourseSubscriptions;
import JPAEntity.Merchandise;
import JPAEntity.OnlineBankingInfo;
import JPAEntity.Orders;
import JPAEntity.OrdersPK;
import JPAEntity.PaymentMethod;
import JPAEntity.Payments;
import JPAEntity.Promotions;
import JPAEntity.Shipping;
import JPAEntity.ShippingMethod;
import JPAEntity.TablesRecordCounter;
import JPAEntity.TngAccounts;
import JPAEntity.Transactions;
import JPAEntity.UserOnlineBankingInfo;
import JPAEntity.Users;
import JPAEntity.Courses;
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
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class CheckOutReviewPage extends HttpServlet {

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
        // Forward the request to Merchandises.jsp
        request.getRequestDispatcher("/WEB-INF/Client/CheckOutReview.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formAction = request.getParameter("formAction");
        HttpSession session = request.getSession();
        if(formAction.equals("back")){
            response.sendRedirect("check-out");
            return;
        }else if(formAction.equals("confirm")){
            //get all data first 
            List<CartItems> checkingOutCartItemList = (List<CartItems>) session.getAttribute("checkingOutCartItemList");
            Promotions prmoApplied = (Promotions) session.getAttribute("promoApplied");
            boolean checkOutNeedShipping = ((Boolean) session.getAttribute("checkOutNeedShipping")).booleanValue();
            double  itemsSubtotal = Double.parseDouble((String) session.getAttribute("itemsSubtotal")); 
            double itemsTotalDiscount = Double.parseDouble((String)session.getAttribute("itemsTotalDiscount"));
            double itemsTotalAfterDiscount = Double.parseDouble((String) session.getAttribute("itemsTotalAfterDiscount")); 
            double shippingDiscount = Double.parseDouble((String) session.getAttribute("shippingDiscount")); 
            double promoDiscount = Double.parseDouble((String) session.getAttribute("promoDiscount"));
            double paymentTotal = Double.parseDouble((String) session.getAttribute("paymentTotal"));
            double paymentTax = Double.parseDouble((String) session.getAttribute("paymentTax"));
            String selectedBillingInfo = (String) session.getAttribute("selectedBillingInfo");
            BillingAddress userInputBillAddressCheckout = (BillingAddress)session.getAttribute("userInputBillAddressCheckout");
            boolean chooseToUpdateBillAddress = ((Boolean) session.getAttribute("chooseToUpdateBillAddress")).booleanValue();
            String selectedPaymentMethod = (String) session.getAttribute("selectedPaymentMethod");
            boolean chooseToUpdateStoredPayment = ((Boolean) session.getAttribute("chooseToUpdateStoredPayment")).booleanValue();
            
            String cardHolderNameInput = (String) session.getAttribute("cardHolderNameInput"); 
            String cardNumberInput = (String) session.getAttribute("cardNumberInput"); 
            String expDateInput = (String) session.getAttribute("expDateInput"); 
            String ccvInput = (String) session.getAttribute("ccvInput"); 
            String selectedOnlineBankId = (String) session.getAttribute("selectedOnlineBankId"); 
            String selectedOnlineBankRemark = (String) session.getAttribute("selectedOnlineBankRemark"); 
            String tngNoInputUser = (String) session.getAttribute("tngNoInputUser"); 
            String tngRemarkInput = (String) session.getAttribute("tngRemarkInput"); 
            ShippingMethod selectedShipping = (ShippingMethod)session.getAttribute("selectedShipping");
            Users userData = (Users) session.getAttribute("userData");
            OnlineBankingInfo selectedOBankInfo = (OnlineBankingInfo) session.getAttribute("selectedBankIdReq");
            
            List<TablesRecordCounter> recordsCounterList = new ArrayList<TablesRecordCounter>();
            
            String test = "";
            //check which address is used
            Shipping newShippingEntity = null;
            BillingAddress newBillAddress = null;
            if(checkOutNeedShipping){
                //check which address is used
                //create shipping object
                TablesRecordCounter shippingCounter = em.find(TablesRecordCounter.class,"SHIPPING");
                TablesRecordCounter billAddCounter = em.find(TablesRecordCounter.class,"BILLING_ADDRESS");
                newShippingEntity = new Shipping(new Date(),new Date(),25+selectedShipping.getShippingRates(),0,"0","");
                newShippingEntity.setShippingId(shippingCounter.getCounter()+1);
                shippingCounter.counterIncrementByOne();
                recordsCounterList.add(shippingCounter);
                if(selectedBillingInfo.equals("newAddress")){ //if new create new billing address object
                    newBillAddress = userInputBillAddressCheckout;
                    newBillAddress.setAddressId(billAddCounter.getCounter()+1);
                    billAddCounter.counterIncrementByOne();
                    recordsCounterList.add(billAddCounter);
                    newShippingEntity.setAddressId(newBillAddress);
                }else{
                    newShippingEntity.setAddressId(userData.getAddressId());
                }
            }
            
            //create new transaction
            Transactions newTrans = new Transactions(itemsTotalAfterDiscount,promoDiscount,paymentTax,selectedPaymentMethod,0.0,paymentTotal,"Complete",new Date());
            newTrans.setUserId(userData);
            TablesRecordCounter transCounter = em.find(TablesRecordCounter.class,"TRANSACTIONS");
            newTrans.setTransactionId(transCounter.getCounter()+1);
            transCounter.counterIncrementByOne();
            recordsCounterList.add(transCounter);
            
            if(promoDiscount > 0){
                newTrans.setPromotionId(prmoApplied);
            }
            if(checkOutNeedShipping){
                newTrans.setShippingId(newShippingEntity);
            }
            
            //create new payment
            Payments newPayment = null;
            if(selectedPaymentMethod.equals("card")){
                newPayment = new Payments(cardNumberInput,"VISA/MASTER",paymentTotal,"Complete",new Date(),em.find(PaymentMethod.class, "PM00001"));
            }else if(selectedPaymentMethod.equals("bank")){
                newPayment = new Payments("",selectedOBankInfo.getBankName(),paymentTotal,"Complete",new Date(),em.find(PaymentMethod.class, "PM00003"));
            }else if(selectedPaymentMethod.equals("tng")){
                newPayment = new Payments(tngNoInputUser,"Touch N' Go",paymentTotal,"Complete",new Date(),em.find(PaymentMethod.class, "PM00006"));
            }
            newPayment.setTransactionId(newTrans);
            TablesRecordCounter paymentCounter = em.find(TablesRecordCounter.class,"PAYMENTS");
            newPayment.setPaymentId(paymentCounter.getCounter()+1);
            paymentCounter.counterIncrementByOne();
            recordsCounterList.add(paymentCounter);
            
            //create new order list
            List<Orders> newOrdersList = new ArrayList<Orders>();
            for(CartItems item:checkingOutCartItemList){
                OrdersPK  newOrderPK = new OrdersPK(newTrans.getTransactionId(),item.getProductId().getProductId());
                Orders newOrder = new Orders(newOrderPK,item.getQuantity());
                newOrdersList.add(newOrder);
            }
            
            //check need update payment on user onot
            Bankcardinfo newCardinfo = null;
            TngAccounts newTngInfo = null;
            UserOnlineBankingInfo newUserOBankInfo = null;
            if(chooseToUpdateStoredPayment){
                if(selectedPaymentMethod.equals("card")){
                    if(userData.getBankcardinfo()==null){
                        newCardinfo = new Bankcardinfo("VISA",cardHolderNameInput,cardNumberInput,expDateInput,ccvInput,userData);
                        userData.setBankcardinfo(newCardinfo);
                        TablesRecordCounter bankCardInfoCounter = em.find(TablesRecordCounter.class,"BANKCARDINFO");
                        newCardinfo.setCardinfoid(bankCardInfoCounter.getCounter()+1);
                        bankCardInfoCounter.counterIncrementByOne();
                        recordsCounterList.add(bankCardInfoCounter);
                    }else{
                        newCardinfo = userData.getBankcardinfo();
                        newCardinfo.setCardHolderName(cardHolderNameInput);
                        newCardinfo.setCardNo(cardNumberInput);
                        newCardinfo.setCardtype("VISA");
                        newCardinfo.setCvv(ccvInput);
                        newCardinfo.setExpiryDate(expDateInput);
                    }
                
                }else if(selectedPaymentMethod.equals("bank")){
                    if(userData.getUserOnlineBankingInfoList().isEmpty()){
                        newUserOBankInfo = new UserOnlineBankingInfo();
                        newUserOBankInfo.setDateAdded(new Date());
                        newUserOBankInfo.setOnlineBankingId(selectedOBankInfo);
                        newUserOBankInfo.setUserId(userData);
                        userData.getUserOnlineBankingInfoList().add(newUserOBankInfo);
                        TablesRecordCounter userOBankInfoCounter = em.find(TablesRecordCounter.class,"USER_ONLINE_BANKING_INFO");
                        newUserOBankInfo.setUserOnlineBankingInfoId(userOBankInfoCounter.getCounter()+1);
                        userOBankInfoCounter.counterIncrementByOne();
                        recordsCounterList.add(userOBankInfoCounter);
                    }else{
                        newUserOBankInfo = userData.getUserOnlineBankingInfoList().get(0);
                        newUserOBankInfo.setDateAdded(new Date());
                        newUserOBankInfo.setOnlineBankingId(selectedOBankInfo);
                    }
                }else if(selectedPaymentMethod.equals("tng")){
                    if(userData.getTngAccounts()==null){
                        newTngInfo = new TngAccounts(userData,tngNoInputUser);
                        TablesRecordCounter tngTableCounter = em.find(TablesRecordCounter.class,"TNG_ACCOUNTS");
                        newTngInfo.setTngId(tngTableCounter.getCounter()+1);
                        tngTableCounter.counterIncrementByOne();
                        recordsCounterList.add(tngTableCounter);
                    }else{
                        newTngInfo = userData.getTngAccounts();
                        newTngInfo.setPhoneno(tngNoInputUser);
                    }
                }
            }
            
            //check need update billing onot
            BillingAddress updatedBillingAddress = null;
            if(chooseToUpdateBillAddress){
                if(selectedBillingInfo.equals("newAddress")){ //if new create new billing address object
                    //userData.setAddressId(newBillAddress);
                    updatedBillingAddress = userData.getAddressId();
                    updatedBillingAddress.setCity(userInputBillAddressCheckout.getCity());
                    updatedBillingAddress.setCountry(userInputBillAddressCheckout.getCountry());
                    updatedBillingAddress.setLine1(userInputBillAddressCheckout.getLine1());
                    updatedBillingAddress.setLine2(userInputBillAddressCheckout.getLine2());
                    updatedBillingAddress.setPostalcode(userInputBillAddressCheckout.getPostalcode());
                    updatedBillingAddress.setStateResides(userInputBillAddressCheckout.getStateResides());
                }
            }
            List<CourseSubscriptions> newCourseSubList = new ArrayList<CourseSubscriptions>();
            List<Merchandise> updatedMerchandiseList = new ArrayList<Merchandise>();
            TablesRecordCounter courseSubsTableCounter = em.find(TablesRecordCounter.class,"COURSE_SUBSCRIPTIONS");
            for(CartItems item:checkingOutCartItemList){
               Query query = em.createNamedQuery("Courses.findByProductId").setParameter("productId", item.getProductId());
               List<Courses> courseList = query.getResultList();
               
               query = em.createNamedQuery("Merchandise.findByProductId").setParameter("productId", item.getProductId());
               List<Merchandise> merchandiseList = query.getResultList();
               //update courseSubcribtion table
               if(!courseList.isEmpty()){
                   Courses retrievedCourse = courseList.get(0);
                   CourseSubscriptions newSub = new CourseSubscriptions(new Date(),new Date(),"Just Started",new Date());
                   newSub.setCourseId(retrievedCourse);
                   newSub.setUserId(userData);
                   newSub.setSubscriptionsId(courseSubsTableCounter.getCounter()+1);
                   courseSubsTableCounter.counterIncrementByOne();
                   newCourseSubList.add(newSub);
               }else if(!merchandiseList.isEmpty()){
                   Merchandise retrievedMerch = merchandiseList.get(0);
                   retrievedMerch.setStockBalance(retrievedMerch.getStockBalance()-item.getQuantity());
                   updatedMerchandiseList.add(retrievedMerch);
               }
               recordsCounterList.add(courseSubsTableCounter);
               
               
               
            }
           
            
            
            //proceed save to databases
            try{
                utx.begin();
                if(checkOutNeedShipping){
                    if(selectedBillingInfo.equals("newAddress")){ //if new create new billing address object
                        em.persist(newBillAddress);
                    }
                    em.persist(newShippingEntity);
                }
                em.persist(newTrans);
                em.persist(newPayment);
                
                for(Orders order:newOrdersList){
                    em.persist(order);
                }
                
                if(chooseToUpdateStoredPayment){
                    if(selectedPaymentMethod.equals("card")){
                        if(userData.getBankcardinfo()==null){
                        em.persist(newCardinfo);
                    }else{
                        em.merge(newCardinfo);
                    }
                
                }else if(selectedPaymentMethod.equals("bank")){
                    if(userData.getUserOnlineBankingInfoList().isEmpty()){
                        em.persist(newUserOBankInfo);
                    }else{
                        em.merge(newUserOBankInfo);
                    }
                }else if(selectedPaymentMethod.equals("tng")){
                    if(userData.getTngAccounts()==null){
                        em.persist(newTngInfo);
                    }else{
                        em.merge(newTngInfo);
                    }
                }
                    em.merge(userData);
                }
                
                if(chooseToUpdateBillAddress){
                    if(selectedBillingInfo.equals("newAddress")){ //if new create new billing address object
                        em.merge(updatedBillingAddress);
                        em.merge(userData);
                    }
                }
                //remove items from db
                for(CartItems item:checkingOutCartItemList){
                    CartItems itemToRemoved = em.find(CartItems.class, item.getCartitemId());
                    em.remove(itemToRemoved);
                }
                
                for(CourseSubscriptions newSub : newCourseSubList){
                    em.persist(newSub);
                }
                
                for(Merchandise targetUpdateMerch : updatedMerchandiseList ){
                    em.merge(targetUpdateMerch);
                }
                
                for(TablesRecordCounter tableCounter : recordsCounterList){
                    em.merge(tableCounter);
                }
                
                em.merge(userData);
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
            
            //update user session
            session.setAttribute("userData",userData);
            //clear all session
            session.removeAttribute("shipMethodList");
            session.removeAttribute("totalItemsCheckout");
            session.removeAttribute("errMsg");
            session.removeAttribute("successMsg");
            session.removeAttribute("promoApplied");
            session.removeAttribute("checkOutNeedShipping");
            session.removeAttribute("selectedShipping");
            session.removeAttribute("selectedBillingInfo");
            session.removeAttribute("userInputBillAddressCheckout");
            session.removeAttribute("chooseToUpdateBillAddress");
            session.removeAttribute("selectedPaymentMethod");
            session.removeAttribute("selectedBankIdReq");
            session.removeAttribute("cardHolderNameInput");
            session.removeAttribute("cardNumberInput");
            session.removeAttribute("expDateInput");
            session.removeAttribute("ccvInput");
            session.removeAttribute("selectedOnlineBankId");
            session.removeAttribute("selectedOnlineBankRemark");
            session.removeAttribute("tngNoInputUser");
            session.removeAttribute("tngRemarkInput");
            session.removeAttribute("chooseToUpdateStoredPayment");
            session.removeAttribute("itemsSubtotal");
            session.removeAttribute("itemsTotalDiscount");
            session.removeAttribute("itemsTotalAfterDiscount");
            session.removeAttribute("promoDiscount");
            session.removeAttribute("shippingDiscount");
            session.removeAttribute("shippingCharges");
            session.removeAttribute("selectedBankIdReq");
            session.removeAttribute("paymentTotal");
            session.removeAttribute("paymentTotal");
            
            request.setAttribute("TransactionNumber",newTrans.getTransactionId());
            request.getRequestDispatcher("/WEB-INF/Client/CheckOutSuccess.jsp").forward(request, response);
        }
    }

}
