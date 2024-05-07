<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Cart</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/cart.css" rel="stylesheet" >
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>
        
        <!--put error msg here to show the error msg-->
        <span id="errorMsg" hidden></span>

        <%
            int numberOfItemInCartCourse = 3;
            int numberOfItemInCartMerch = 1;
        %>

        <form>
            <section class="section cart-section">
                <div class="cart-div flex-row" id="cartContent">
                    <div class="cart-div-left flex-col">

                        <!--title-->
                        <div class="flex-col">
                            <h1 class="page-title">Shopping Cart</h1>
                        </div>

                        <div class="flex-col"  id="cartCourseTitle"> 
                            <p class="number-item"><span id="cartCourseNumber"><%= numberOfItemInCartCourse%></span> course in cart</p>
                        </div>

                        <!--course in cart-->
                        <ul class="course-list flex-col" id="cartCourseList">

                            <div class="course-item flex-row" courseID="CR0000001">
                                <div class="course-check flex-col">
                                    <input type="checkbox" class="cart-check" id="cartItemId" name="cartItemId" value="CI0000012" />
                                </div>
                                <div class="course-img flex-col">
                                    <img src="./img/course/beginner_excel.jpg" alt="" />
                                </div>
                                <div class="course-detail flex-col">
                                    <h3 class="course-title">The Ultimate Excel Programming Course</h3>
                                    <p class="course-author">By Woo Yu Beng, Snijders Wang</p>
                                    <p class="course-category">Microsoft Excel</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">4.2</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">123</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>27 Hours</p>
                                        <p>All level</p>
                                    </div>
                                </div>
                                <div class="course-button flex-col">
                                    <button type="button" class="remove-btn">Remove</button>
                                    <button type="button" class="move-btn">Move to Wish</button>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM 112.00</p>    
                                    <p class="course-normal-price">RM 188.00</p>
                                </div>
                            </div>

                            <div class="course-item flex-row" courseID="CR0000002">
                                <div class="course-check flex-col">
                                    <input type="checkbox" class="cart-check" id="cartItemId" name="cartItemId" value="CI0000012" />
                                </div>
                                <div class="course-img flex-col">
                                    <img src="./img/course/beginner_excel.jpg" alt="" />
                                </div>
                                <div class="course-detail flex-col">
                                    <h3 class="course-title">The Ultimate Excel Programming Course</h3>
                                    <p class="course-author">By Woo Yu Beng, Snijders Wang</p>
                                    <p class="course-category">Microsoft Excel</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">4.2</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">123</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>27 Hours</p>
                                        <p>All level</p>
                                    </div>
                                </div>
                                <div class="course-button flex-col">
                                    <button type="button" class="remove-btn">Remove</button>
                                    <button type="button" class="move-btn">Move to Wish</button>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM 112.00</p>    
                                    <p class="course-normal-price">RM 188.00</p>
                                </div>
                            </div>

                            <div class="course-item flex-row" courseID="CR0000003">
                                <div class="course-check flex-col">
                                    <input type="checkbox" class="cart-check" id="cartItemId" name="cartItemId" value="CI0000012" />
                                </div>
                                <div class="course-img flex-col">
                                    <img src="./img/course/beginner_excel.jpg" alt="" />
                                </div>
                                <div class="course-detail flex-col">
                                    <h3 class="course-title">The Ultimate Excel Programming Course</h3>
                                    <p class="course-author">By Woo Yu Beng, Snijders Wang</p>
                                    <p class="course-category">Microsoft Excel</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">4.2</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">123</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>27 Hours</p>
                                        <p>All level</p>
                                    </div>
                                </div>
                                <div class="course-button flex-col">
                                    <button type="button" class="remove-btn">Remove</button>
                                    <button type="button" class="move-btn">Move to Wish</button>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM 112.00</p>    
                                    <p class="course-normal-price">RM 188.00</p>
                                </div>
                            </div>

                        </ul>

                        <div class="flex-col" id="cartMerchTitle"> 
                            <p class="number-item"><span id="cartMerchNumber"><%= numberOfItemInCartMerch%></span> merchandise in cart</p>
                        </div>

                        <ul class="course-list flex-col" id="cartMerchList">

                            <div class="course-item flex-row" courseID="M00000001">
                                <div class="course-check flex-col">
                                    <input type="checkbox" class="cart-check" id="cartItemId" name="cartItemId" value="CI0000012" />
                                </div>
                                <div class="course-img flex-col">
                                    <img src="./img/merchandise/prx_shirt.png" alt="" />
                                </div>
                                <div class="course-detail flex-col">
                                    <h3 class="course-title">Course Hero X PRX T-shirt</h3>
                                    <p class="course-category">Collectible</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">4.2</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">123</span>)</p>
                                    </div>
                                </div>
                                <div class="march-qty flex-row">
                                    <button type="button" class="qty-btn substract"><i class="ri-subtract-fill"></i></button>
                                    <input type="text" class="merch-qty-input" value="1" max="99" />
                                    <button type="button" class="qty-btn add"><i class="ri-add-fill"></i></button>
                                </div>
                                <div class="course-button flex-col">
                                    <button type="button" class="remove-btn">Remove</button>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM 112.00</p>    
                                    <p class="course-normal-price">RM 188.00</p>
                                </div>
                            </div>

                        </ul>

                    </div>

                    <div class="cart-div-right flex-col">
                        <div class="sticky-div flex-col">
                            <div class="cart-price flex-col">
                                <h3 class="cart-price-title">Total (<span id="numberSelected">4</span> items):</h3>
                                <h1 class="total-price">RM <span id="totalPrice">1234.00</span></h1>
                                <h1 class="total-nornmal-price">RM <span id="totalNoramlPrice">1834.00</span></h1>
                                <p class="discount-percentage"><span id="discount-percentage">40</span>% off</p>

                                <input type="submit" class="submit-btn" value="Checkout" />
                            </div>
                        </div>
                    </div>
                </div>

                <!--message show to user when cart is empty-->
                <div class="empty-div flex-col">
                    <img src="./img/cart_wish/empty_cart.png" alt="" />
                    <h1>Your Cart is Empty</h1>
                    <p>It looks like your cart is currently empty. Don't worry, there's plenty to explore and add to your cart! Browse our selection and add any item you'd like to purchase.</p>
                    <a href="<%= webpath.getPageUrl("products")%>">Explore more courses</a>
                </div>
            </section>
        </form>

        <script src="./js/cart/cart.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %> 
    </body>
</html>
