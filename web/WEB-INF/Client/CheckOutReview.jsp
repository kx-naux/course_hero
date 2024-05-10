<%@page import="JPAEntity.BillingAddress"%>
<%@page import="JPAEntity.OnlineBankingInfo"%>
<%@page import="JPAEntity.TngAccounts"%>
<%@page import="JPAEntity.UserOnlineBankingInfo"%>
<%@page import="JPAEntity.Bankcardinfo"%>
<%@page import="JPAEntity.CartItems"%>
<%@page import="JPAEntity.ShippingMethod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<!DOCTYPE html>
<% List<ShippingMethod> shipMethodList = (List<ShippingMethod>) session.getAttribute("shipMethodList"); %>
<% List<CartItems> checkingOutCartItemList = (List<CartItems>) session.getAttribute("checkingOutCartItemList"); %>
<% String errMsg = (String) session.getAttribute("errMsg"); %>
<% String successMsg = (String) session.getAttribute("successMsg");%>
<% Bankcardinfo userBankCard = null;
    if(userData.getBankcardinfo() != null){
        userBankCard = userData.getBankcardinfo();
    }%>
<% UserOnlineBankingInfo userOBankInfo = null;
    if(!userData.getUserOnlineBankingInfoList().isEmpty()){
        userOBankInfo = userData.getUserOnlineBankingInfoList().get(0);
    }%>
<% TngAccounts userTng = null;
    if(userData.getTngAccounts() != null){
        userTng = userData.getTngAccounts();
    }%>
<% List<OnlineBankingInfo> oBankNameList = (List<OnlineBankingInfo>) request.getAttribute("oBankNameList"); %>

<% String totalItemsCheckout = (String) session.getAttribute("totalItemsCheckout"); %>
<% String itemsSubtotal = (String) session.getAttribute("itemsSubtotal"); %>
<% String itemsTotalDiscount = (String) session.getAttribute("itemsTotalDiscount");%>
<% String itemsTotalAfterDiscount = (String) session.getAttribute("itemsTotalAfterDiscount"); %>
<% String shippingDiscount = (String) session.getAttribute("shippingDiscount"); %>
<% String promoDiscount = (String) session.getAttribute("promoDiscount");%>
<% String paymentTotal = (String) session.getAttribute("paymentTotal");%>
<% String paymentTax = (String) session.getAttribute("paymentTax");%>

<% String selectedBillingInfo = (String) session.getAttribute("selectedBillingInfo"); %>
<% BillingAddress userInputBillAddressCheckout = (BillingAddress)session.getAttribute("userInputBillAddressCheckout"); %>
<% boolean chooseToUpdateBillAddress = ((Boolean) session.getAttribute("chooseToUpdateBillAddress")).booleanValue();%>
<% String selectedPaymentMethod = (String) session.getAttribute("selectedPaymentMethod");%>
<% boolean chooseToUpdateStoredPayment = ((Boolean) session.getAttribute("chooseToUpdateStoredPayment")).booleanValue();%>
<% boolean checkOutNeedShipping = ((Boolean) session.getAttribute("checkOutNeedShipping")).booleanValue();%>

<% String cardHolderNameInput = (String) session.getAttribute("cardHolderNameInput"); %>
<% String cardNumberInput = (String) session.getAttribute("cardNumberInput"); %>
<% String expDateInput = (String) session.getAttribute("expDateInput"); %>
<% String ccvInput = (String) session.getAttribute("ccvInput"); %>
<% String selectedOnlineBankId = (String) session.getAttribute("selectedOnlineBankId"); %>
<% String selectedOnlineBankRemark = (String) session.getAttribute("selectedOnlineBankRemark"); %>
<% String tngNoInputUser = (String) session.getAttribute("tngNoInputUser"); %>
<% String tngRemarkInput = (String) session.getAttribute("tngRemarkInput"); %>

<% OnlineBankingInfo selectedOBankInfo = (OnlineBankingInfo) session.getAttribute("selectedBankIdReq"); %>

<jsp:useBean id="selectedShipping" class="JPAEntity.ShippingMethod" scope="session" />
<jsp:useBean id="promoApplied" class="JPAEntity.Promotions" scope="session" />
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Checkout Review</title>
        <link rel="icon" type="image/ico" href="${companyIcon}">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/check_out.css" rel="stylesheet" >
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>
        <script src="./js/components/to_top.js"></script>

        <!--title section-->
        <section class="section title-section">
            <div class="title-div flex-row">
                <div class="flex-row">
                    <img class="title-logo" src="${companyLogo}" alt="" />
                    <h1 class="title">Checkout Review</h1>
                </div>
                <%--<a class="cancel-btn" href="<%= webpath.getPageUrl("cart")%>">Cancel</a>--%>
            </div>
        </section>

        <form id="checkOutReviewForm" method="post" action="check-out-review">
            <input type="text" id="fromAction" name="formAction" hidden />
            <section class="section content-section flex-col">
                <%  BillingAddress billAddressInfo = null;
                if(checkOutNeedShipping){
                        if(selectedBillingInfo.equals("newAddress")){
                            billAddressInfo = userInputBillAddressCheckout;
                        }else{
                            billAddressInfo = userData.getAddressId();
                            if(billAddressInfo.getLine2()==null){
                                billAddressInfo.setLine2("");
                    }
                        }%>
                <div class="content-div ship-div flex-col">
                    <h1 class="div-title"><i class="ri-truck-line"></i> Shipping & Delivery Address</h1>

                    <div class="unstored-address-div address-div check-address  flex-col">
                        <p class="address-div-title">Shipping</p>

                        <div class="text-input-div shipping-type-div  address-input-div flex-col">
                            <label>Type:</label>
                            <input type="text" value="Standard Shipping" maxlength="50" readonly />
                            <input type="text" value="" name="selectedShippngId" hidden />
                        </div>

                        <p class="address-div-title">Delivery Address</p>

                        <div class="text-input-div   address-input-div flex-col">
                            <label>Address:</label>
                            <input type="text" class="address-1 address-row-1" name="address1" value="<%= billAddressInfo.getLine1() %>" maxlength="50" readonly />
                            <input type="text" class="address-2"  name="address2" value="<%= billAddressInfo.getLine2() %>"  maxlength="50" readonly  />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="city">City:</label>
                            <input type="text" class="city"  name="city" value="<%= billAddressInfo.getCity() %>"  maxlength="20" readonly  />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="postalCode">Postal code:</label>
                            <input type="text" class="postal-code" name="postalCode" maxlength="9" value="<%= billAddressInfo.getPostalcode() %>" readonly  />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="state">State Resides:</label>
                            <input type="text" class="state"  name="state"  maxlength="20" value="<%= billAddressInfo.getStateResides() %>" readonly />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="state">Country:</label>
                            <input type="text" class="country"  name="country"  maxlength="40" value="<%= billAddressInfo.getCountry() %>" readonly />
                        </div>
                    </div>
                </div>        
                <%}%>
                

                <div class="content-div item-div flex-col">
                    <h1 class="div-title"><i class="ri-shopping-basket-2-line"></i> Products Ordered</h1>

                    <!--table to show selected products-->
                    <table id="itemsTable">
                        <thead>
                            <tr>
                                <th>Item</th>
                                <th>Unit Price</th>
                                <th>Qty</th>
                                <th>Sub Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (CartItems item : checkingOutCartItemList) {%>
                            <tr>
                                <td>
                                    <div class="flex-row">
                                        <input type="text" name="cartItem" value="<%= item.getCartitemId()%>" hidden />
                                        <img class="item-img" src="<%= item.getProductId().getImagePath()%>" onerror="this.src='./img/course/beginner_excel.jpg';" alt="" />
                                        <p><%= item.getProductId().getProdName()%><p>
                                    </div>
                                </td>
                                <td>RM <%= item.getProductId().getPrice()%></td>
                                <td><%= item.getQuantity()%></td>
                                <td>RM <%= String.format("%.2f", item.getProductId().getPrice() * item.getQuantity())%></td>
                            </tr>
                            <% }%>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3">Order Total ( <%= totalItemsCheckout%> item):</td>
                                <td>RM <%= itemsSubtotal%></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <%if(promoApplied.getEndTime()!=null){%>
                <div class="content-div promo-div flex-col">
                    <h1 class="div-title"><i class="ri-coupon-2-line"></i> Voucher / Promo Code</h1>

                    <div class="promo-input-div flex-row">
                        <input type="text" id="promo" name="promo" class="" value="<%= promoApplied.getEndTime() == null ? "":promoApplied.getPromoCode() %>" maxlength="9" />
                    </div>

                    <!--after promo apply show info here-->
                    <div class="promo-detail flex-col">
                        <p class="promo-name"><%= promoApplied.getEndTime() == null ? "":promoApplied.getPromotionName()%></p>
                        <p class="promo-desc"><%= promoApplied.getEndTime() == null ? "":promoApplied.getPromoDescription()%></p>
                        <p class="promo-end"><i class="ri-timer-line"></i><%= promoApplied.getEndTime() == null ? "":"Ends at"+promoApplied.getEndDateStr()%></p>
                    </div>

                    <p class="invalid-msg"></p>
                </div>
                <%}%>
                

                <div class="content-div payment-div flex-col">
                    <h1 class="div-title"><i class="ri-money-dollar-box-line"></i> Payment</h1>

                    <div class="unstored-address-div address-div check-address  flex-col">
                        <p class="address-div-title">Payment Detail</p>

                        <div class="text-input-div  flex-col">
                            <label>Payment method:</label>
                            <input type="text" value="<%= selectedPaymentMethod %>" readonly />
                        </div>

                        <!--credit card-->
                        <%if(selectedPaymentMethod.equals("card")){%>
                        <div class="payment-input-div text-input-div   flex-col">
                            <label for="cardHolder">Card Holder:</label>
                            <input type="text"  value="<%= cardHolderNameInput == null?"":cardHolderNameInput %>" readonly/>
                        </div>

                        <div class="payment-input-div text-input-div   flex-col">
                            <label for="cardNo">Card No.:</label>
                            <input type="text"  class=""  value="<%= cardNumberInput == null?"":cardNumberInput %>" readonly/>
                        </div>

                        <div class="payment-card-detail-row flex-row">

                            <div class="payment-input-div text-input-div   flex-col">
                                <label for="expDate">Exp Date:</label>
                                <input type="text"  value="<%= expDateInput == null?"":expDateInput %>" readonly/>
                            </div>

                            <div class="payment-input-div text-input-div   flex-col">
                                <label for="ccv">CCV:</label>
                                <input type="text" value="<%= ccvInput == null?"":ccvInput %>" readonly/>
                            </div>

                        </div>
                        <%}else if(selectedPaymentMethod.equals("bank")){%>
                            <!--bank-->
                        <div class="payment-input-div text-input-div  flex-col">
                            <label for="bank">Bank:</label>
                            <input type="text" value="<%= selectedOBankInfo == null?"":selectedOBankInfo.getBankName() %>" readonly/>
                        </div>

                        <!--<div class="payment-input-div text-input-div  flex-col">
                            <label for="bankAccNo">Account No.:</label>
                            <input type="text"  value="" readonly/>
                        </div>-->


                        <div class="payment-input-div text-input-div flex-col">
                            <label for="bankRemark">Remark:</label>
                            <input type="text" value="<%= selectedOnlineBankRemark == null? "" : selectedOnlineBankRemark %>" readonly/>
                        </div>
                        <%}else if(selectedPaymentMethod.equals("tng")){%>
                        <!--tng-->
                        <div class="payment-input-div text-input-div  flex-col">
                            <label for="tngPhoneNo">Phone No.:</label>
                            <input type="text" value="<%= tngNoInputUser == null?"":tngNoInputUser %>" readonly/>
                        </div>

                        <div class="payment-input-div text-input-div flex-col">
                            <label for="tngRemark">Remark:</label>
                            <input type="text" value="<%= tngRemarkInput == null?"":tngRemarkInput %>" readonly/>
                        </div>
                        <%}%>
                        

                    </div>

                    <hr class="total-div-divider"/>

                    <!--show total--> 
                    <div class="total-div flex-col">                        
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Items Total: </h2>
                            <h3 class="total-row-td">RM <%= itemsSubtotal %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Items Discount: </h2>
                            <h3 class="total-row-td">- RM <%= itemsTotalDiscount %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">After Items Discount: </h2>
                            <h3 class="total-row-td">RM <%= itemsTotalAfterDiscount %></h3>
                        </div>
                        <hr class="total-div-small-divider" />
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Promo Discount: </h2>
                            <h3 class="total-row-td">- RM <%= promoDiscount %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Shipping Fee: </h2>
                            <h3 class="total-row-td">+ RM <%= checkOutNeedShipping?"25.00":"0.00" %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Shipping Charges: </h2>
                            <h3 class="total-row-td">+ RM <%= String.format("%.2f", selectedShipping.getShippingRates()) %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Shipping Discount: </h2>
                            <h3 class="total-row-td">- RM <%= shippingDiscount %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Tax (6%): </h2>
                            <h3 class="total-row-td">+ RM <%= paymentTax %></h3>
                        </div>
                        <div class="total-row total-row-overall flex-row">
                            <h2 class="total-row-th">Total Payment: </h2>
                            <h3 class="total-row-td">RM <%= paymentTotal %></h3>
                        </div>

                        <div class="flex-row review-btn-row">
                            <input type="button" id="backOrderBtn" value="Back" onclick="submitForm('back')" />
                            <input type="button" id="cofirmOrderBtn" value="Confirm Order" onclick="submitForm('confirm')" />
                        </div>
                    </div>
                </div>

            </section>
        </form>

        <script src="./js/check_out/check_out_review.js"></script>

    </body>
</html>
