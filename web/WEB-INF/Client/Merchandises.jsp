<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="JPAEntity.Merchandise"%>
<%@page import="JPAEntity.MerchCategory"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Merchandises</title>
        <link rel="icon" type="image/ico" href="${companyIcon}">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/merchandises.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="merchItemsByCategory" class="Map<String, List<JPAEntity.Merchandise>>" scope="request" />
        <% List<MerchCategory> merchCategories = (List<MerchCategory>) request.getAttribute("merchCategories");
        %>

    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <section class="section show-merch-section">
            <div class="show-merch-div">
                <!--bg pic-->
                <img src="./img/merchandises/merch.png" alt="" />

                <!--merch item in pic-->
                <div class="merch-pic-div prx-tshirt flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero X PRX T-shirt</h1>
                                <p class="merch-info-price">RM <span>58.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div bottle1 flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Bottle 1</h1>
                                <p class="merch-info-price">RM <span>58.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div cap flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero Cap</h1>
                                <p class="merch-info-price">RM <span>18.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div hoodie flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero X SEN Hoodie</h1>
                                <p class="merch-info-price">RM <span>118.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div note2 flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero Planner</h1>
                                <p class="merch-info-price">RM <span>28.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div bear flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero Teddy bear</h1>
                                <p class="merch-info-price">RM <span>32.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div bag flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero Bag</h1>
                                <p class="merch-info-price">RM <span>12.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div pad flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero Mouse Pad</h1>
                                <p class="merch-info-price">RM <span>28.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div mug flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero X LOL Mug</h1>
                                <p class="merch-info-price">RM <span>22.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="merch-pic-div note1 flex-row">
                    <button type="button"><span></span></button>
                    <div class="outer-info">
                        <div class="merch-pic-info flex-row" merchID="123123">
                            <div class="merch-pic-info-left flex-col">
                                <h1 class="merch-info-title">Course Hero Notebook</h1>
                                <p class="merch-info-price">RM <span>11.00</span></p>
                            </div>
                            <div class="merch-pic-info-right flex-col">
                                <i class="ri-arrow-right-line"></i>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>

        <section class="section merch-list-section">
            <div class="merch-list-div flex-col">
                <h1 class="merch-list-title" style="margin-top: 50px;">All merchandises</h1>


                <div class="merch-list-div flex-col" style="margin-top: -30px;">
                    <%
                        for (MerchCategory eachCategory : merchCategories) {
                            List<Merchandise> merchandise = merchItemsByCategory.get(eachCategory.getMerchcatId());
                            if (merchandise != null && !merchandise.isEmpty()) {
                    %>
                    <div class="merch-category flex-col">
                        <div class="merch-category-title-div flex-row" merchcatId="<%=eachCategory.getMerchcatId()%>">
                            <h1><%=eachCategory.getCategoryName()%></h1>
                            <p><i class="ri-add-fill"></i></p>
                        </div>
                        <div class="merch-category-item-list flex-row">
                            <% for (Merchandise mer : merchandise) {%>
                            <div class="merch-list-item flex-col" merchID="<%=mer.getMerchId()%>">
                                <!-- Display merchandise item details -->
                                <div class="merch-item-img flex-col">
                                    <img src="./img/merchandise/hoodie.png" alt="<%=mer.getProductId().getProdName()%>" />
                                </div>
                                <div class="merch-item-info flex-col">
                                    <h1 class="merch-item-name"><%=mer.getProductId().getProdName()%></h1>
                                    <div class="merch-review flex-row">
                                        <p class="rating-digit"><%=mer.getProductId().getAvgRating()%></p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                    </div>
                                    <div class="merch-tag-field flex-row">
                                        <p class="merch-tag tag-orange">Hot Sell</p>
                                        <p class="merch-tag tag-yellow">New</p>
                                    </div>
                                    <div class="merch-price-field flex-row">
                                        <%
                                            double originalPrice = mer.getProductId().getPrice();
                                            double discountedPrice = (100 - mer.getProductId().getDiscount()) / 100 * (mer.getProductId().getPrice());
                                        %>
                                        <p class="merch-price">RM <span><%=String.format("%.2f", (originalPrice == discountedPrice) ? originalPrice : discountedPrice)%></span></p>
                                        <% if (originalPrice != discountedPrice) {%>
                                        <p class="merch-normal-price">RM <span><%=String.format("%.2f", originalPrice)%></span></p>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                            <% } // end for loop %>
                        </div>
                    </div>
                    <% } // end if %>
                    <% } // end outer for loop %>
                </div>
            </div>
        </section>


        <!--        <section class="section merch-list-section">
                    <div class="merch-list-div flex-col">
        
                        <h1 class="merch-list-title">All merchandises</h1>
        
                        <div class="merch-category flex-col">
                            <div class="merch-category-title-div flex-row">
                                category name
                                <h1>Apparel</h1>
                                <p><i class="ri-add-fill"></i></p>
                            </div>
                            <div class="merch-category-item-list flex-row">
        
                                <div class="merch-list-item flex-col" merchID="M00000001">
                                    <div class="merch-item-img flex-col">
                                        <img src="./img/merchandise/prx_shirt.png" alt="" />
                                    </div>
                                    <div class="merch-item-info flex-col">
                                        <h1 class="merch-item-name">Course Hero X PRX T-shirt</h1>
                                        <div class="merch-review flex-row">
                                            <p class="rating-digit">4.5</p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                        </div>
                                        <div class="merch-tag-field flex-row">
                                            <p class="merch-tag tag-orange">Hot Sell</p>
                                            <p class="merch-tag tag-yellow">New</p>
                                        </div>
                                        <div class="merch-price-field flex-row">
                                            <p class="merch-price">RM <span>88.00</span></p>
                                            <p class="merch-normal-price">RM <span>110.00</span></p>
                                        </div>
                                    </div>
                                </div>
        
                                <div class="merch-list-item flex-col" merchID="M00000001">
                                    <div class="merch-item-img flex-col">
                                        <img src="./img/merchandise/hoodie.png" alt="" />
                                    </div>
                                    <div class="merch-item-info flex-col">
                                        <h1 class="merch-item-name">Course Hero X SEN Hoodie Course Hero X SEN Hoodie</h1>
                                        <div class="merch-review flex-row">
                                            <p class="rating-digit">4.5</p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                        </div>
                                        <div class="merch-tag-field flex-row">
                                            <p class="merch-tag tag-orange">Hot Sell</p>
                                            <p class="merch-tag tag-yellow">New</p>
                                        </div>
                                        <div class="merch-price-field flex-row">
                                            <p class="merch-price">RM <span>88.00</span></p>
                                            <p class="merch-normal-price">RM <span>110.00</span></p>
                                        </div>
                                    </div>
                                </div>
        
                                <div class="merch-list-item flex-col" merchID="M00000001">
                                    <div class="merch-item-img flex-col">
                                        <img src="./img/merchandise/hoodie.png" alt="" />
                                    </div>
                                    <div class="merch-item-info flex-col">
                                        <h1 class="merch-item-name">Course Hero X SEN Hoodie</h1>
                                        <div class="merch-review flex-row">
                                            <p class="rating-digit">4.5</p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                        </div>
                                        <div class="merch-tag-field flex-row">
                                            <p class="merch-tag tag-orange">Hot Sell</p>
                                            <p class="merch-tag tag-yellow">New</p>
                                        </div>
                                        <div class="merch-price-field flex-row">
                                            <p class="merch-price">RM <span>88.00</span></p>
                                            <p class="merch-normal-price">RM <span>110.00</span></p>
                                        </div>
                                    </div>
                                </div>
        
                                <div class="merch-list-item flex-col" merchID="M00000001">
                                    <div class="merch-item-img flex-col">
                                        <img src="./img/merchandise/hoodie.png" alt="" />
                                    </div>
                                    <div class="merch-item-info flex-col">
                                        <h1 class="merch-item-name">Course Hero X SEN Hoodie</h1>
                                        <div class="merch-review flex-row">
                                            <p class="rating-digit">4.5</p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                        </div>
                                        <div class="merch-tag-field flex-row">
                                            <p class="merch-tag tag-orange">Hot Sell</p>
                                            <p class="merch-tag tag-yellow">New</p>
                                        </div>
                                        <div class="merch-price-field flex-row">
                                            <p class="merch-price">RM <span>88.00</span></p>
                                            <p class="merch-normal-price">RM <span>110.00</span></p>
                                        </div>
                                    </div>
                                </div>
        
                                <div class="merch-list-item flex-col" merchID="M00000001">
                                    <div class="merch-item-img flex-col">
                                        <img src="./img/merchandise/hoodie.png" alt="" />
                                    </div>
                                    <div class="merch-item-info flex-col">
                                        <h1 class="merch-item-name">Course Hero X SEN Hoodie</h1>
                                        <div class="merch-review flex-row">
                                            <p class="rating-digit">4.5</p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                        </div>
                                        <div class="merch-tag-field flex-row">
                                            <p class="merch-tag tag-orange">Hot Sell</p>
                                            <p class="merch-tag tag-yellow">New</p>
                                        </div>
                                        <div class="merch-price-field flex-row">
                                            <p class="merch-price">RM <span>88.00</span></p>
                                            <p class="merch-normal-price">RM <span>110.00</span></p>
                                        </div>
                                    </div>
                                </div>
        
                            </div>
                        </div>
        
                        <div class="merch-category flex-col">
                            <div class="merch-category-title-div flex-row">
                                <h1>Drinkware</h1>
                                <p><i class="ri-add-fill"></i></p>
                            </div>
                            <div class="merch-category-item-list flex-row">
        
                                <div class="merch-list-item flex-col" merchID="M00000001">
                                    <div class="merch-item-img flex-col">
                                        <img src="./img/merchandise/mug.png" alt="" />
                                    </div>
                                    <div class="merch-item-info flex-col">
                                        <h1 class="merch-item-name">Course Hero X LOL Mug</h1>
                                        <div class="merch-review flex-row">
                                            <p class="rating-digit">4.5</p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">5</span>)</p>
                                        </div>
                                        <div class="merch-tag-field flex-row">
                                            <p class="merch-tag tag-orange">Hot Sell</p>
                                            <p class="merch-tag tag-yellow">New</p>
                                        </div>
                                        <div class="merch-price-field flex-row">
                                            <p class="merch-price">RM <span>88.00</span></p>
                                            <p class="merch-normal-price">RM <span>110.00</span></p>
                                        </div>
                                    </div>
                                </div>
        
                            </div>
                        </div>
        
                    </div>
                </section>-->

        <script src="./js/merchandise/merchandises.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>