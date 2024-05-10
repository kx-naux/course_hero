<%@page import="JPAEntity.SocialMediaLinks"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JPAEntity.Courses"%>
<%@page import="JPAEntity.AuthorContribution"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<jsp:useBean id="authorData" class="JPAEntity.Authors" scope="request" />
<%List<Courses> authCourses = new ArrayList<Courses>(); %>
<%authCourses = (List<Courses>) request.getAttribute("authorCourses"); %>
<%List<SocialMediaLinks> socialMediaLinks = new ArrayList<SocialMediaLinks>();%>
<%socialMediaLinks = (List<SocialMediaLinks>) request.getAttribute("socialMediaLinks");%>
<%long numOfCoursesRec = ((Long) request.getAttribute("numberOfCourses")).longValue();%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Author</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/author.css" rel="stylesheet" >
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />

    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <section class="section author-section">
            <div class="author-div flex-row">

                <div class="author-left flex-col">

                    <div class="author-info flex-col">
                        <h1 class="author-title">Author</h1>
                        <h2 class="author-name">${authorData.authorName}</h2>
                        <h3 class="author-position">${authorData.authorPosition}</h3>
                        <h4 class="author-insitution"><i class="ri-graduation-cap-fill"></i> ${authorData.institution}</h4>
                        <h4 class="author-award"><i class="ri-award-fill"></i> ${authorData.awardsHonors}</h4>
                        <p class="author-join-date"><i class="ri-information-line"></i> Join on: ${authorData.dateJoined}</p>
                    </div>

                    <div class="author-about flex-col">
                        <h1 class="author-left-div-title">About me</h1>
                        <p class="about-content">
                            ${authorData.biography}
                        </p> 
                    </div>

                    <div class="author-courses flex-col">

                        <h1 class="author-left-div-title">My courses (<%= numOfCoursesRec%>)</h1>

                        <div class="course-div flex-row">
                            <% for (Courses course : authCourses) {%>
                            <div class="course-product" courseID="<%= course.getCourseId()%>" onclick="redirectToProductPage(this)">
                                <div class="course-product-card">
                                    <div class="product-card-top">
                                        <img src="<%= course.getProductId().getImagePath() %>"  onerror="this.src='./img/course/beginner_excel.jpg'" alt="">
                                        <div class="action-btn-field flex-row">
                                            <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                            <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                        </div>
                                    </div>
                                    <div class="product-card-bottom flex-col">
                                        <h1 class="course-title"><%= course.getProductId().getProdName()%></h1>
                                        <p class="course-category"><%= course.getCoursecatId().getCategoryName()%></p>

                                        <% String authorsStr = ""; %>
                                        <% for (AuthorContribution authContri : course.getAuthorContributionList()) {
                                                authorsStr = authContri.getAuthorId().getAuthorName() + ", ";
                                            }%>
                                        <% authorsStr = authorsStr.substring(0, authorsStr.length() - 2);%>

                                        <p class="course-author"><%= authorsStr%></p>
                                        <div class="course-review flex-row">
                                            <p class="rating-digit"><%= course.getProductId().getAvgRating()%></p>
                                            <i class="ri-star-fill"></i>
                                            <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                        </div>
                                        <div class="course-label flex-row">
                                            <p><%= course.getLengthHour()%> Hour(s)</p>
                                            <p><%= course.getCourseLevel()%></p>
                                        </div>
                                        <div class="course-price-field flex-row">
                                            <p class="course-price">RM <span><%= course.getProductId().getPrice() - course.getProductId().getDiscount()%></span></p>                                      
                                            <p class="course-normal-price">RM <span><%= course.getProductId().getPrice()%></span></p>
                                        </div>
                                        <div class="course-tag-field flex-row">
                                            <p class="course-tag tag-orange">Hot Sell</p>
                                            <p class="course-tag tag-yellow">New Course</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>




                        </div>
                        <% if (numOfCoursesRec != 0) { %>


                        <%}%>
                        <form id="authorForm">
                            <!--pagination, 20 course per page-->
                            <%
                                long currentPage = 1;
                                long dataPerPage = 4;
                                long lastPage = ((numOfCoursesRec - 1) / dataPerPage) + 1;
                                if (request.getParameter("p") != null) {
                                    currentPage = Integer.parseInt(request.getParameter("p"));
                                }

                                boolean previousAllow = currentPage > 1;
                                boolean nextAllow = currentPage < lastPage;
                            %>
                            <input type="text" id="id" name="id" value="${param.id}" hidden />
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

                <div class="author-right flex-col">

                    <div class="author-img">
                        <img src="./img/author/angelayu.jpg" alt=""/>
                    </div>

                    <div class="author-link flex-col">
                        <%for (SocialMediaLinks link : socialMediaLinks) {
                            if (link.getSocialmediaName().equalsIgnoreCase("LinkedIn")) {%>
                                <a href=<%= link.getDestLink()%> class="author-link-btn"><i class="ri-linkedin-box-fill"></i> LinkedIn</a>
                        <%}else if (link.getSocialmediaName().equalsIgnoreCase("Youtube")) {%>
                        <a href=<%= link.getDestLink()%> class="author-link-btn"><i class="ri-linkedin-box-fill"></i> Youtube</a>
                        <%}else if (link.getSocialmediaName().equalsIgnoreCase("X")) {%>
                        <a href=<%= link.getDestLink()%> class="author-link-btn"><i class="ri-linkedin-box-fill"></i> X</a>
                        <%}else if (link.getSocialmediaName().equalsIgnoreCase("Instagram")) {%>
                        <a href=<%= link.getDestLink()%> class="author-link-btn"><i class="ri-linkedin-box-fill"></i> Instagram</a>
                        <%}else{%>
                            <a href=<%= link.getDestLink()%> class="author-link-btn"><i class="ri-link"></i> <%= link.getSocialmediaName()%></a>
                        <%}%>
                        <%}%>
                    </div>

                </div>

            </div>
        </section>

        <script src="./js/author/author.js"></script>

        <!--join as author-->
        <section class="section welcome-author-section">
            <div class="welcome-author-div flex-row">
                <div class="welcome-author-left flex-col">
                    <img src="./img/author_page/join_author.png" alt="" />
                </div>
                <div class="welcome-author-right flex-col">
                    <h1>Become an author</h1>
                    <p>Authors from around the world teach millions of learners on Course Hero. We provide the tools and skills to teach what you love.</p>
                    <a href="#">Start teaching today</a>
                </div>
            </div>
        </section>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
