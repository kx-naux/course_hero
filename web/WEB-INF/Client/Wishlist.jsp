<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Wishlist</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/wishlist.css" rel="stylesheet" >
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

        <%
            int numberOfItemInWish = 3;
        %>

        <section class="section wishlist-section">
            <div class="wishlist-div flex-col" id="wishContent">

                <!--title-->
                <div class="flex-col">
                    <h1 class="page-title">Wishlist</h1>
                </div>

                <div class="flex-col"> 
                    <p class="number-item"><span id="wishNumber"><%= numberOfItemInWish%></span> courses in wishlist</p>
                </div>

                <!--course in wishlist-->
                <ul class="course-list flex-col" id="courseList">

                    <div class="course-item flex-row" courseID="CR0000001">
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
                            <button type="button" class="move-btn">Move to Cart</button>
                        </div>
                        <div class="course-price-field flex-col">
                            <p class="course-price">RM 112.00</p>    
                            <p class="course-normal-price">RM 188.00</p>
                        </div>
                    </div>
                    
                    <div class="course-item flex-row" courseID="CR0000002">
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
                            <button type="button" class="move-btn">Move to Cart</button>
                        </div>
                        <div class="course-price-field flex-col">
                            <p class="course-price">RM 112.00</p>    
                            <p class="course-normal-price">RM 188.00</p>
                        </div>
                    </div>
                    
                    <div class="course-item flex-row" courseID="CR0000003">
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
                            <button type="button" class="move-btn">Move to Cart</button>
                        </div>
                        <div class="course-price-field flex-col">
                            <p class="course-price">RM 112.00</p>    
                            <p class="course-normal-price">RM 188.00</p>
                        </div>
                    </div>

                </ul>

            </div>

            <!--message show to user when wishlist is empty-->
            <div class="empty-div flex-col">
                <img src="./img/cart_wish/empty_wish.png" alt="" />
                <h1>Empty Wishlist</h1>
                <p>It seems like your wishlist is empty at the moment. Why not start adding some items you're interested in?</p>
                <a href="<%= webpath.getPageUrl("products")%>">Explore more courses</a>
            </div>
        </section>

        <script src="./js/wishlist/wishlist.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %> 
    </body>
</html>
