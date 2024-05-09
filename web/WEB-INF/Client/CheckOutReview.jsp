<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Checkout Review</title>
        <link rel="icon" type="image/ico" href="${companyIcon}">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/check_out.css" rel="stylesheet" >
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
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

        <form id="checkOutReviewForm">
            <input type="text" id="fromAction" name="formAction" hidden />
            <section class="section content-section flex-col">

                <div class="content-div ship-div flex-col">
                    <h1 class="div-title"><i class="ri-truck-line"></i> Shipping & Delivery Address</h1>

                    <div class="unstored-address-div address-div check-address  flex-col">
                        <p class="address-div-title">Shipping</p>

                        <div class="text-input-div shipping-type-div  address-input-div flex-col">
                            <label>Type:</label>
                            <input type="text" value="Standard Shipping" maxlength="50" readonly />
                        </div>

                        <p class="address-div-title">Delivery Address</p>

                        <div class="text-input-div   address-input-div flex-col">
                            <label>Address:</label>
                            <input type="text" class="address-1 address-row-1" name="address1" value="" maxlength="50" readonly />
                            <input type="text" class="address-2"  name="address2" value=""  maxlength="50" readonly  />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="city">City:</label>
                            <input type="text" class="city"  name="city" value=""  maxlength="20" readonly  />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="postalCode">Postal code:</label>
                            <input type="text" class="postal-code" name="postalCode" maxlength="9" value="" readonly  />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="state">State Resides:</label>
                            <input type="text" class="state"  name="state"  maxlength="20" value="" readonly />
                        </div>

                        <div class="text-input-div   address-input-div flex-col">
                            <label for="state">Country:</label>
                            <input type="text" class="country"  name="country"  maxlength="40" value="" readonly />
                        </div>
                    </div>
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
                            <tr>
                                <td>
                                    <div class="flex-row">
                                        <input type="text" name="cartItem" value="C0001" hidden />
                                        <img class="item-img" src="./img/course/beginner_excel.jpg" alt="" />
                                        <p>The Ultimate Excel Programming Course<p>
                                    </div>
                                </td>
                                <td>RM 112.00</td>
                                <td>1</td>
                                <td>RM 112.00</td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="flex-row">
                                        <input type="text" name="cartItem" value="C0002" hidden />
                                        <img class="item-img" src="./img/course/beginner_excel.jpg" alt="" />
                                        <p>The Ultimate Excel Programming Course<p>
                                    </div>
                                </td>
                                <td>RM 112.00</td>
                                <td>1</td>
                                <td>RM 112.00</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3">Order Total (2 item):</td>
                                <td>RM 224.00</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <div class="content-div promo-div flex-col">
                    <h1 class="div-title"><i class="ri-coupon-2-line"></i> Voucher / Promo Code</h1>

                    <div class="promo-input-div flex-row">
                        <input type="text" id="promo" name="promo" class="" value="PROMO1" maxlength="9" />
                    </div>

                    <!--after promo apply show info here-->
                    <div class="promo-detail flex-col">
                        <p class="promo-name">Promo Name: Hapyy Happy Happy</p>
                        <p class="promo-desc">Promo Description: Hapyy Happy Happy</p>
                        <p class="promo-end"><i class="ri-timer-line"></i> End at 2024-09-15</p>
                    </div>

                    <p class="invalid-msg"></p>
                </div>

                <div class="content-div payment-div flex-col">
                    <h1 class="div-title"><i class="ri-money-dollar-box-line"></i> Payment</h1>

                    <div class="unstored-address-div address-div check-address  flex-col">
                        <p class="address-div-title">Payment Detail</p>

                        <div class="text-input-div  flex-col">
                            <label>Payment method:</label>
                            <input type="text" value="" readonly />
                        </div>
                        
                        <!--credit card-->
                        <div class="payment-input-div text-input-div   flex-col">
                            <label for="cardHolder">Card Holder:</label>
                            <input type="text"  value="" readonly/>
                        </div>

                        <div class="payment-input-div text-input-div   flex-col">
                            <label for="cardNo">Card No.:</label>
                            <input type="text"  class=""  value="" readonly/>
                        </div>

                        <div class="payment-card-detail-row flex-row">

                            <div class="payment-input-div text-input-div   flex-col">
                                <label for="expDate">Exp Date:</label>
                                <input type="text"  value="" readonly/>
                            </div>

                            <div class="payment-input-div text-input-div   flex-col">
                                <label for="ccv">CCV:</label>
                                <input type="text" value="" readonly/>
                            </div>

                        </div>
                        
                        <!--bank-->
                        <div class="payment-input-div text-input-div  flex-col">
                            <label for="bank">Bank:</label>
                            <input type="text" value="" readonly/>
                        </div>

                        <div class="payment-input-div text-input-div  flex-col">
                            <label for="bankAccNo">Account No.:</label>
                            <input type="text"  value="" readonly/>
                        </div>


                        <div class="payment-input-div text-input-div flex-col">
                            <label for="bankRemark">Remark:</label>
                            <input type="text" value="" readonly/>
                        </div>
                        
                        <!--tng-->
                        
                    </div>

                    <hr class="total-div-divider"/>

                    <!--show total--> 
                    <div class="total-div flex-col">                        
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Items Total: </h2>
                            <h3 class="total-row-td">RM 224.00</h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Items Discount: </h2>
                            <h3 class="total-row-td">- RM 50.00</h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Promo Discount: </h2>
                            <h3 class="total-row-td">- RM 20.00</h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Shipping Fee: </h2>
                            <h3 class="total-row-td">RM 25.00</h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Shipping Discount: </h2>
                            <h3 class="total-row-td">- RM 5.00</h3>
                        </div>
                        <div class="total-row flex-row">
                            <h2 class="total-row-th">Tax (6%): </h2>
                            <h3 class="total-row-td">RM 11.00</h3>
                        </div>
                        <div class="total-row total-row-overall flex-row">
                            <h2 class="total-row-th">Total Payment: </h2>
                            <h3 class="total-row-td">RM 375.00</h3>
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
