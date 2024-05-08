<%@page import="JPAEntity.Courses"%>
<%@page import="java.util.Map"%>
<%@page import="JPAEntity.Authors"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Wishlist</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/wishlist.css" rel="stylesheet" >
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
        <%long totalWishlistItems = ((Long) request.getAttribute("totalWishlistItems")).longValue();%>
        <jsp:useBean id="allCourseWishlist" class="List<JPAEntity.Courses>" scope="request" />
        <jsp:useBean id="authorContribution" class=" Map<String, List<JPAEntity.Authors>>" scope="request" />
    </head>
    <body>

        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <section class="section wishlist-section">
            <div class="wishlist-div flex-col" id="wishContent">

                <!--title-->
                <div class="flex-col">
                    <h1 class="page-title">Wishlist</h1>
                </div>

                <div class="flex-col"> 
                    <p class="number-item"><span id="wishNumber"><%= totalWishlistItems%></span> <span id="wishNumberNoun"><%= (totalWishlistItems > 1) ? "courses" : "course"%></span> in wishlist</p>
                </div>

                <!--course in wishlist-->
                <ul class="course-list flex-col" id="courseList">

                    <%for (Courses eachCourse : allCourseWishlist) {%>
                    <div class="course-item flex-row" courseID=<%=eachCourse.getCourseId()%>>
                        <div class="course-img flex-col">
                            <img src="./img/course/beginner_excel.jpg" alt="" />
                        </div>
                        <div class="course-detail flex-col">
                            <h3 class="course-title"><%=eachCourse.getProductId().getProdName()%></h3>

                            <%
                                List<Authors> currentAuthors = authorContribution.get(eachCourse.getCourseId());
                                String concatAuthors = "";
                                for (int i = 0; i < currentAuthors.size(); i++) {
                                    concatAuthors += currentAuthors.get(i).getAuthorName();
                                    if (i < currentAuthors.size() - 1) {
                                        concatAuthors += ", ";
                                    }
                                }
                            %>

                            <p class="course-author"><%=concatAuthors%></p>
                            <p class="course-category"><%= eachCourse.getCoursecatId().getCategoryName()%></p>
                            <div class="course-review flex-row">
                                <p class="rating-digit"><%=String.format("%.2f", eachCourse.getProductId().getAvgRating())%></p>
                                <i class="ri-star-fill"></i>
                                <p class="rating-number-field">(<span class="raing-number"><%=eachCourse.getProductId().getRateWeightage()%></span>)</p>
                            </div>
                            <div class="course-label flex-row">
                                <p><%=String.format("%.2f", eachCourse.getLengthHour())%> Hours</p>
                                <p><%=eachCourse.getCourseLevel()%></p>
                            </div>
                        </div>
                        <div class="course-button flex-col">
                            <button type="button" class="remove-btn">Remove</button>
                            <button type="button" class="move-btn">Move to Cart</button>
                        </div>
                        <div class="course-price-field flex-col">
                            <%
                                double originalPrice = eachCourse.getProductId().getPrice();
                                double discountedPrice = (100 - eachCourse.getProductId().getDiscount()) / 100 * (eachCourse.getProductId().getPrice());
                            %>
                            <p class="course-price">RM <%=String.format("%.2f", (originalPrice == discountedPrice) ? originalPrice : discountedPrice)%></p>
                            <%if (originalPrice != discountedPrice) {%>
                            <p class="course-normal-price">RM <%=String.format("%.2f", originalPrice)%></p>
                            <%}%>

                        </div>
                    </div>
                    <%}%>

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
