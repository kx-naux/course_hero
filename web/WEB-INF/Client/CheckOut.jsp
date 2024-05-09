<%@page import="JPAEntity.OnlineBankingInfo"%>
<%@page import="JPAEntity.TngAccounts"%>
<%@page import="JPAEntity.UserOnlineBankingInfo"%>
<%@page import="JPAEntity.CartItems"%>
<%@page import="JPAEntity.BillingAddress"%>
<%@page import="JPAEntity.ShippingMethod"%>
<%@page import="JPAEntity.Bankcardinfo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
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


<jsp:useBean id="selectedShipping" class="JPAEntity.ShippingMethod" scope="session" />
<jsp:useBean id="promoApplied" class="JPAEntity.Promotions" scope="session" />
<html lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Checkout</title>
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

        <!--to show a toast error message--> 
        <% if(errMsg == null){ %>
            <span id="errorMsg" hidden></span>
        <%}else{%>
            <span id="errorMsg" hidden><%= errMsg %></span>
        <%}%>
        

        <!--to show like promo code apply successfully-->
        <% if(successMsg == null){ %>
            <span id="successMsg" hidden></span>
        <%}else{%>
            <span id="successMsg" hidden><%= successMsg %></span>
        <%}%>
        

        <!--title section-->
        <section class="section title-section">
            <div class="title-div flex-row">
                <div class="flex-row">
                    <img class="title-logo" src="${companyLogo}" alt="" />
                    <h1 class="title">Checkout</h1>
                </div>
                <a class="cancel-btn" href="<%= webpath.getPageUrl("cart")%>">Cancel</a>
            </div>
        </section>

        <form id="checkOutForm" method="post">
            <input type="text" id="fromAction" name="formAction" hidden />
            <section class="section content-section flex-col">

                <!--render this div (ship-div) if need shipping-->
                <div class="content-div ship-div flex-col">
                    <h1 class="div-title"><i class="ri-truck-line"></i> Shipping & Delivery Address</h1>

                    <!--choose shipping method-->
                    <div class="shipping-method-div flex-row">
                        <% if (selectedShipping.getShippingMethodId().isEmpty()) {
                                ShippingMethod firstMethod = shipMethodList.get(0);%>

                        <div class="shipping-method flex-col">
                            <input type="radio" id="standardShipping" name="shippingMethod" value="<%= firstMethod.getShippingMethodId()%>" checked hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <div class="shipping-method-top flex-col">
                                <h1 class="method-title"><%= firstMethod.getShippingType()%></h1>
                                <p class="method-desc"><%= firstMethod.getDeliverySpeed()%></p>
                            </div>
                            <div class="shipping-method-bot flex-col">  
                                <p class="method-rate">CHARGES: RM <%= firstMethod.getShippingRates()%></p>
                            </div> 
                        </div>
                            
                        <% for (int i = 1; i < shipMethodList.size(); i++) {%>
                        <div class="shipping-method flex-col">
                            <input type="radio" id="standardShipping" name="shippingMethod" value="<%= shipMethodList.get(i).getShippingMethodId()%>" hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <div class="shipping-method-top flex-col">
                                <h1 class="method-title"><%= shipMethodList.get(i).getShippingType()%></h1>
                                <p class="method-desc"><%= shipMethodList.get(i).getDeliverySpeed()%></p>
                            </div>
                            <div class="shipping-method-bot flex-col">  
                                <p class="method-rate">CHARGES: RM <%= shipMethodList.get(i).getShippingRates()%></p>
                            </div> 
                        </div>
                        <% }%>
                        <%} else {%>
                        <% for (ShippingMethod shipMethod : shipMethodList) { %>
                        <% if (shipMethod.getShippingMethodId().equals(selectedShipping.getShippingMethodId())) {%>
                        <div class="shipping-method flex-col">
                            <input type="radio" id="standardShipping" name="shippingMethod" value="<%= shipMethod.getShippingMethodId()%>" checked hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <div class="shipping-method-top flex-col">
                                <h1 class="method-title"><%= shipMethod.getShippingType()%></h1>
                                <p class="method-desc"><%= shipMethod.getDeliverySpeed()%></p>
                            </div>
                            <div class="shipping-method-bot flex-col">  
                                <p class="method-rate">CHARGES: RM <%= shipMethod.getShippingRates()%></p>
                            </div> 
                        </div>
                        <%} else {%>
                        <div class="shipping-method flex-col">
                            <input type="radio" id="standardShipping" name="shippingMethod" value="<%= shipMethod.getShippingMethodId()%>" hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <div class="shipping-method-top flex-col">
                                <h1 class="method-title"><%= shipMethod.getShippingType()%></h1>
                                <p class="method-desc"><%= shipMethod.getDeliverySpeed()%></p>
                            </div>
                            <div class="shipping-method-bot flex-col">  
                                <p class="method-rate">CHARGES: RM <%= shipMethod.getShippingRates()%></p>
                            </div> 
                        </div>
                        <%}%>
                        <%}%>
                        <%}%>
                    </div>

                    <!--if got stored address show here-->
                    <%  BillingAddress userAddress = userData.getAddressId();
                        if (!userAddress.getCity().isEmpty() && !userAddress.getCountry().isEmpty() && !userAddress.getLine1().isEmpty() && !userAddress.getPostalcode().isEmpty() && !userAddress.getStateResides().isEmpty()) { %>
                    <div class="stored-address-div flex-row">

                        <div class="current-address address-div flex-col">
                            <input type="radio"  id="currentAddress" name="storedAddress" value="storedAddress" checked hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>

                            <p class="address-div-title">Default Address</p>

                            <div class="text-input-div address-input-div flex-col">
                                <label>Address:</label>
                                <input type="text"  class="address-row-1" value="${userData.addressId.line1}" readonly />
                                <input type="text"  value="${userData.addressId.line2}" readonly />
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="city">City:</label>
                                <input type="text"  value="${userData.addressId.city}" readonly />
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="postalCode">Postal code:</label>
                                <input type="text" value="${userData.addressId.postalcode}" readonly/>
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="state">State Resides:</label>
                                <input type="text"  value="${userData.addressId.stateResides}" readonly/>
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="state">Country:</label>
                                <input type="text" value="${userData.addressId.country}" readonly/>
                            </div>
                        </div>

                        <div class="new-address address-div flex-col ">
                            <input type="radio"  id="addAddress" name="storedAddress" value="newAddress" hidden/>
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>

                            <p class="address-div-title">New Address</p>

                            <div class="text-input-div address-input-div flex-col">
                                <label>Address:</label>
                                <input type="text" class="address-row-1 address-1" name="address1" value="" placeholder="address line 1" maxlength="50" />
                                <input type="text" class="address-2"  name="address2" value="" placeholder="address line 2" maxlength="50" />
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="city">City:</label>
                                <input type="text" class="city"  name="city" value="" placeholder="city name" maxlength="20" />
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="postalCode">Postal code:</label>
                                <input type="text" class="postal-code" name="postalCode" placeholder="postal code" maxlength="9" value="" />
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="state">State Resides:</label>
                                <input type="text" class="state"  name="state" placeholder="state name" maxlength="20" value=""/>
                            </div>

                            <div class="text-input-div address-input-div flex-col">
                                <label for="state">Country:</label>
                                <input type="text" class="country"  name="country" placeholder="country name" maxlength="40" value=""/>
                            </div>
                        </div>
                    </div>
                    <% } else { %>
                    <div class="unstored-address-div address-div flex-col">
                        <input type="radio" id="current-address" name="storedAddress" value="newAddress" hidden checked />
                        <p class="address-div-title">Enter New Address</p>

                        <div class="text-input-div address-input-div flex-col">
                            <label>Address:</label>
                            <input type="text" class="address-1 address-row-1" name="address1" value="" placeholder="address line 1" maxlength="50" />
                            <input type="text" class="address-2"  name="address2" value="" placeholder="address line 2" maxlength="50" />
                        </div>

                        <div class="text-input-div address-input-div flex-col">
                            <label for="city">City:</label>
                            <input type="text" class="city"  name="city" value="" placeholder="city name" maxlength="20" />
                        </div>

                        <div class="text-input-div address-input-div flex-col">
                            <label for="postalCode">Postal code:</label>
                            <input type="text" class="postal-code" name="postalCode" placeholder="postal code" maxlength="9" value="" />
                        </div>

                        <div class="text-input-div address-input-div flex-col">
                            <label for="state">State Resides:</label>
                            <input type="text" class="state"  name="state" placeholder="state name" maxlength="20" value=""/>
                        </div>

                        <div class="text-input-div address-input-div flex-col">
                            <label for="state">Country:</label>
                            <input type="text" class="country"  name="country" placeholder="country name" maxlength="40" value=""/>
                        </div>
                    </div>


                    <% }%>
                    <div class="flex-col confirma-store-div">
                        <label for="storingAddress"><input type="checkbox" id="storingAddress" name="storingAddress" value="storingAddress"> Store this address to use at next time</label>
                    </div>

                    <p class="invalid-msg"></p>

                </div>

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

                <div class="content-div promo-div flex-col">
                    <h1 class="div-title"><i class="ri-coupon-2-line"></i> Voucher / Promo Code</h1>

                    <div class="promo-input-div flex-row">
                        <input type="text" id="promo" name="promo" placeholder="Enter promo code here" value="<%= promoApplied.getEndTime() == null ? "":promoApplied.getPromoCode() %>" maxlength="9" />
                        <input type="button" id="promoApply" value="Apply" />
                    </div>

                    <!--after promo apply show info here-->
                    <div class="promo-detail flex-col">
                        <p class="promo-name"><%= promoApplied.getEndTime() == null ? "":promoApplied.getPromotionName()%></p>
                        <p class="promo-desc"><%= promoApplied.getEndTime() == null ? "":promoApplied.getPromoDescription()%></p>
                        <p class="promo-end"><i class="ri-timer-line"></i> Ends at <%= promoApplied.getEndTime() == null ? "":promoApplied.getEndDateStr()%></p>
                    </div>

                    <p class="invalid-msg"></p>
                </div>

                <div class="content-div payment-div flex-col">
                    <h1 class="div-title"><i class="ri-money-dollar-box-line"></i> Payment</h1>

                    <div class="payment-method-div flex-row">

                        <div class="payment-method flex-col">
                            <input type="radio" id="cardMethod" name="paymentMethod" value="card" hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <p class="payment-method-icon"><i class="ri-bank-card-line"></i></p>
                            <p class="payment-method-name">Card</p>
                        </div>

                        <div class="payment-method flex-col">
                            <input type="radio" id="cardMethod" name="paymentMethod" value="bank" hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <p class="payment-method-icon"><i class="ri-bank-fill"></i></p>
                            <p class="payment-method-name">Bank</p>
                        </div>

                        <div class="payment-method flex-col">
                            <input type="radio" id="cardMethod" name="paymentMethod" value="tng" hidden />
                            <span class="selected-icon"><i class="ri-check-fill"></i></span>
                            <p class="payment-method-icon"><i class="ri-wallet-line"></i></p>
                            <p class="payment-method-name">TNG E-wallet</p>
                        </div>

                    </div>

                    <!--payment input here-->
                    <div class="payment-method-input payment-card flex-col">
                        <!--if got stored record render this div-->
                        <!--in span put last 4 digits-->
                        <!--in span first put first 4 digit-->
                        <label class="storedDetailLabel" for="storedCard">
                            <input type="checkbox" id="storedCard" name="storedCard" value="storedCard"> 
                            <img class="credit-logo" src="./img/check_out/visa.png" alt=""> 
                            <span id="storedCardNo" first="<%= userBankCard == null ? "" : userBankCard.getCardNo().substring(0, 4) %>" ><%= userBankCard == null ? "" : "*****" + userBankCard.getCardNo().substring(userBankCard.getCardNo().length()-4) %></span> </label>

                        <div class="payment-input-div text-input-div required-input-field  flex-col">
                            <label for="cardHolder">Card Holder:</label>
                            <input type="text"  id="cardHolder" name="cardHolder" placeholder="Holders name" maxlength="45" value="<%= userBankCard == null? "" : userBankCard.getCardHolderName() %>"/>
                        </div>

                        <div class="payment-input-div text-input-div required-input-field  flex-col">
                            <label for="cardNo">Card No.:</label>
                            <input type="text" id="cardNo"  name="cardNo" placeholder="Enter card number (16 digits)" maxlength="16" value="<%= userBankCard == null? "" : userBankCard.getCardNo() %>"/>
                        </div>

                        <div class="payment-card-detail-row flex-row">

                            <div class="payment-input-div text-input-div  required-input-field flex-col">
                                <label for="expDate">Exp Date:</label>
                                <input type="text" id="expDate"  name="expDate" placeholder="MM/YY" maxlength="5" value="<%= userBankCard == null? "" : userBankCard.getExpiryDate() %>"/>
                            </div>

                            <div class="payment-input-div text-input-div required-input-field  flex-col">
                                <label for="ccv">CCV:</label>
                                <input type="text" id="ccv"  name="ccv" placeholder="ccv" maxlength="3" value="<%= userBankCard == null? "" : userBankCard.getCvv()%>"/>
                            </div>

                        </div>

                    </div>

                    <div class="payment-method-input payment-bank flex-col">
                        <label class="storedDetailLabel"  for="storedBank">
                            <input type="checkbox" id="storedBank" name="storedBank" value="storedBank">  Bank Name - Acc No.</label>

                        <div class="payment-input-div text-input-div required-input-field  flex-col">
                            <label for="bank">Bank:</label>
                            <select id="bank" name="bank">
                                <% if(userOBankInfo != null){ %>
                                    <option selected hidden>Select a bank</option>
                                    <% for(OnlineBankingInfo info : oBankNameList ){ 
                                        if(userOBankInfo.getOnlineBankingId().getOnlineBankingId().equals(info.getOnlineBankingId())){%>
                                            <option selected value="<%= info.getOnlineBankingId() %>"><%= info.getBankName()%></option>
                                        <%}else{%>
                                            <option value="<%= info.getOnlineBankingId() %>"><%= info.getBankName()%></option>
                                        <%}%>
                                    <%}%>
                                <%}else{%>
                                    <option selected hidden>Select a bank</option>
                                    <% for(OnlineBankingInfo info:oBankNameList ){ %>
                                    <option value="<%= info.getOnlineBankingId() %>"><%= info.getBankName()%></option>
                                    <%}%>
                                <%}%>
                            </select>
                        </div>

                        <!--<div class="payment-input-div text-input-div  required-input-field flex-col">
                            <label for="bankAccNo">Account No.:</label>
                            <input type="text" id="bankAccNo" name="bankAccNo" placeholder="Bank account no" maxlength="12" value=""/>
                        </div>-->


                        <div class="payment-input-div text-input-div flex-col">
                            <label for="bankRemark">Remark:</label>
                            <input type="text" id="bankRemark" name="bankRemark" placeholder="Enter remark here" maxlength="80" value=""/>
                        </div>


                    </div>

                    <div class="payment-method-input payment-tng flex-col">
                        <!--if got stored record render this div-->
                        <label class="storedDetailLabel"  for="storedTng">
                            <input type="checkbox" id="storedTng" name="storedTng" value="storedTng"> 
                            <img class="tng-logo" src="./img/check_out/tng.png" alt=""/> Touch N' Go
                        </label>

                        <div class="payment-input-div text-input-div required-input-field flex-col">
                            <label for="tngPhoneNo">Phone No.:</label>
                            <input type="text" id="tngPhoneNo" name="tngPhoneNo" placeholder="Enter phone no." maxlength="<%= userTng == null ? "" : userTng.getPhoneno() %>" value=""/>
                        </div>

                        <div class="payment-input-div text-input-div flex-col">
                            <label for="tngRemark">Remark:</label>
                            <input type="text" id="tngRemark" name="tngRemark" placeholder="Enter remark here" maxlength="80" value=""/>
                        </div>

                    </div>

                    <div class="flex-col confirma-store-div">
                        <label for="storingPayment"><input type="checkbox" id="storingPayment" name="storingPayment" value="storingPayment"> Store this payment detail to use at next time</label>
                    </div>

                    <!--invalid msg for payment-->
                    <p class="invalid-msg"></p>

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
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Promo Discount: </h2>
                            <h3 class="total-row-td">- RM <%= promoDiscount %></h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Shipping Fee: </h2>
                            <h3 class="total-row-td">+ RM 25.00</h3>
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

                        <input type="button" id="placeOrderBtn" value="Place Order" />
                    </div>
                </div>

            </section>
        </form>

        <script src="./js/check_out/check_out.js"></script>
        
    </body>
</html>

