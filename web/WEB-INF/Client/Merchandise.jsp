<%@page import="JPAEntity.Ratings"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="merchData" class="JPAEntity.Merchandise" scope="request"/>
<jsp:useBean id="rateStats" class="entity.UserRatingStatistic" scope="request"/>
<% String stockStatus = (String) request.getAttribute("stockStatus"); %>
<% long ratingCount = ((Long) request.getAttribute("ratingCount")).longValue(); %>
<% List<Ratings> ratingList = (List<Ratings>) request.getAttribute("ratingList"); %>
<% long totalSold = ((Long) request.getAttribute("totalSold")).longValue();%>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Merchandise</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/merchandise.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>

        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>


        <section class="section merch-section" merchID="${param.id}">
            <div class="merch-top-div flex-row">
                <div class="merch-div-left flex-col">
                    <!--merchandise img-->
                    <div class="merch-img">
                        <img src="./img/merchandise/prx_shirt.png" alt="" draggable="false" />
                    </div>
                </div>

                <div class="merch-div-right flex-col">
                    <div class="merch-right-top flex-col">
                        <!--merch info-->
                        <div class="flex-col">
                            <h1 class="merch-name">${merchData.productId.prodName}</h1>
                            <div class="rating-stat flex-row">
                                <div class="merch-rating flex-row">
                                    <p>${merchData.productId.avgRating}</p>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                </div>
                                <p><%= ratingCount%> Ratings</p>
                                <p><%= totalSold%> Sold</p>
                            </div>
                        </div>

                        <!--merch price-->
                        <div class="merch-price flex-row">
                            <p class="course-price">RM <span>${merchData.productId.price - merchData.productId.discount}</span></p>                                      
                            <p class="course-normal-price">RM <span>${merchData.productId.price}</span></p>
                            <p class="course-offer"><%= String.format("%.2f", ((double) merchData.getProductId().getDiscount()) / merchData.getProductId().getPrice() * 100)%>% off</p>
                        </div>

                        <!--merch tag field-->
                        <div class="merch-tag-field flex-row">
                            <p class="course-tag tag-orange">Hot Sell</p>
                            <p class="course-tag tag-yellow">New Course</p>
                        </div>
                    </div>

                    <div class="merch-right-bot flex-col">
                        <!--buy merch div-->
                        <div class="merch-buy-div flex-col">
                            <!--merch qty-->
                            <div class="merch-qty flex-col">
                                <label for="order_qty">Quantity: ${merchData.stockBalance}</label>
                                <div class="flex-row">
                                    <button id="order_qty_substract" class="qty-btn substract"><i class="ri-subtract-fill"></i></button>
                                    <input type="text" id="order_qty" class="qty-input"  min="1" max="${merchData.stockBalance}" value="1" />
                                    <button id="order_qty_add" class="qty-btn add"><i class="ri-add-fill"></i></button>
                                </div>
                            </div>

                            <!--merch add to cart-->
                            <div class="merch-add-cart flex-col">
                                <% if (stockStatus.equalsIgnoreCase("In Stock") || stockStatus.equalsIgnoreCase("Low Stock Balance")) {%>
                                <button class="add-cart-btn">Add to cart</button>
                                <% } else { %>
                                <button class="add-cart-btn" disabled="true">Add to cart</button>
                                <%}%>
                            </div>
                        </div>

                        <!--merch shop info-->
                        <div class="merch-shop-info">
                            <ul class="flex-col">
                                <%if(stockStatus.equals("In Stock")){%>
                                    <li><i class="ri-checkbox-circle-fill"></i> In Stock</li>
                                <%}else if(stockStatus.equals("Discontinued")){%>
                                    <li><i class="ri-close-circle-fill"></i> Discontinued</li>
                                <%}else if(stockStatus.equals("Out of Stock")){%>
                                    <li><i class="ri-close-circle-fill"></i> Sold out</li>
                                <%}else if(stockStatus.equals("Low Stock Balance")){%>
                                    <li><i class="ri-error-warning-fill"></i> Low stock level</li>
                                <%}%>
                                <li><i class="ri-truck-fill"></i> Delivery within 7 working days</li>
                            </ul>
                        </div>

                    </div>

                </div>
            </div>

            <div class="merch-bot-div flex-col">

                <!--merch specification-->
                <div class="merch-spec flex-col">
                    <h1 class="merch-bot-div-title">Specification</h1>
                    <table>
                        <tbody>
                            <tr>
                                <th>Category</th>
                                <td>${merchData.merchcatId.categoryName}</td>
                            </tr>
                            <tr>
                                <th>Dimension (Width x Length x Height)cm</th>
                                <td>${merchData.dimensionWCm}cm x ${merchData.dimensionLCm}cm x ${merchData.dimensionHCm}cm</td>
                            </tr>
                            <tr>
                                <th>Weight (KG)</th>
                                <td>${merchData.weightKg}kg</td>
                            </tr>
                            <tr>
                                <th>Stock</th>
                                <td>${merchData.stockBalance}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!--merchandise description-->
                <div class="merch-desc flex-col">
                    <h1 class="merch-bot-div-title">Description</h1>
                    <%
                        // Retrieve article content from the database
                        String descContent = merchData.getProductId().getDescription(); // Assume this method retrieves the article content from the database

                        // Split the article content into paragraphs
                        String modifiedContent = descContent.replaceAll("\\\\n", "\n");
                        String[] paragraphs = modifiedContent.split("\\r?\\n"); // Splitting based on newline character

                        // Display paragraphs in HTML
                        for (String paragraph : paragraphs) {
                    %>
                    <p><%= paragraph%></p>
                    <%
                        }
                    %>

                </div>

                <!--product warning-->
                <div class="merch-shop-info">
                    <h1 class="merch-bot-div-title">Product Warning</h1>
                    <ul class="flex-col">
                        <li>Recommended to flip the T-shirt inside out when ironing, washing and drying to preserve the quality of the T-shirt.</li>
                        <li>Do not wash above 40℃.</li>
                        <li>Do not bleach.</li>
                    </ul>
                </div>

            </div>

            <!--user review-->
            <div class="merch-review merch-bot-div flex-row">
                <div class="merch-review-left flex-col">

                    <h1 class="merch-bot-div-title">User Review</h1>

                    <div class="merch-overall-review flex-col">

                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar flex-col">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 70%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                            </div>
                            <p class="review-number">${rateStats.fiveStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 20%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.fourStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 4%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.threeStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 1%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.twoStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar"  style="width: 1%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.oneStarCounts}</p>
                        </div>
                    </div>
                </div>
                <div class="merch-review-right flex-col">
                    <form id="merchReview">
                        <div class="merch-review-content flex-row">
                            <% for (Ratings rating : ratingList) {%> 
                            <div class="user-review flex-col">
                                <div class="user-review-top flex-row">
                                    <div class="user-img">
                                        <img src="./img/user/default.png" alt="" />
                                    </div>
                                    <div class="flex-col">
                                        <p class="user-name"><%= rating.getUserId().getDisplayName()%></p>
                                        <div class="review-top-bot flex-row">
                                            <div class="rating-stars flex-row">
                                                <% for (int i = rating.getScore(); i > 0; i--) { %>
                                                <%      if (i == 0.5) { %>
                                                            <i class="ri-star-half-fill"></i>
                                                <%      } else {%>
                                                            <i class="ri-star-fill"></i>
                                                <%      }%>
                                                <%}%>
                                            </div>
                                            <p class="review-date"><%= rating.getTimeRated()%></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="user-review-bot flex-col">
                                    <%String rateContent = rating.getComment();
                                        String modifiedrateContent = rateContent.replaceAll("\\\\n", "\n");
                                        String[] rateContentParagraphs = modifiedrateContent.split("\\r?\\n"); // Splitting based on newline character
                                        // Display paragraphs in HTML
                                        for (String paragraph : rateContentParagraphs) {
                                    %>
                                    <p><%= paragraph%></p>
                                    <%}%>
                                    <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                                </div>
                            </div>
                            <%}%>

                           

                        </div>
                             <!--pagination, 20 course per page-->
                            <%
                                int currentPage = 1;
                                int lastPage = 10;
                                if (request.getParameter("p") != null) {
                                    currentPage = Integer.parseInt(request.getParameter("p"));
                                }

                                boolean previousAllow = currentPage > 1;
                                boolean nextAllow = currentPage < lastPage;
                            %>
                            <input type="number" id="current_page" name="p" value="<%= currentPage%>" hidden />
                            <div class="result-pagination-div flex-row">
                                <p class="page-action-btn page-previous-btn <%= previousAllow ? "allow" : ""%>"><i class="ri-arrow-left-s-line"></i></p>
                                    <% if (currentPage - 1 != 0) { %>
                                <p class="first-page-number page-number">1</p>
                                <% }
                                    if (currentPage - 1 != 1 && currentPage - 1 != 2 && currentPage - 1 > 0) { %>
                                <p class="number-dot">...</p>
                                <% }
                                    if (currentPage - 1 > 1) {%>
                                <p class="first-page-number page-number"><%= currentPage - 1%></p>
                                <% }%>
                                <p class="first-page-number page-number current-page"><%= currentPage%></p>
                                <% if (currentPage + 1 < lastPage) {%>
                                <p class="first-page-number page-number"><%= currentPage + 1%></p>
                                <%
                                    }
                                    if (currentPage + 1 != lastPage && currentPage + 2 != lastPage && currentPage + 1 < lastPage) {
                                %>
                                <p class="number-dot">...</p>
                                <% }
                                    if (lastPage > 1 && currentPage != lastPage) {%>
                                <p class="last-page-number page-number"><%= lastPage%></p>
                                <% }%>
                                <p class="page-action-btn page-next-btn <%= nextAllow ? "allow" : ""%>"><i class="ri-arrow-right-s-line"></i></p>
                            </div>
                    </form>
                </div>
            </div>
        </section>


        <script src="./js/merchandise/merchandise.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  

    </body>
</html>
