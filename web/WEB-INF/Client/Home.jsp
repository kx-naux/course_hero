<%@page import="java.util.Date"%>
<%@page import="JPAEntity.Authors"%>
<%@page import="JPAEntity.Courses"%>
<%@page import="JPAEntity.CourseCategory"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="module.CollaborateLogos, module.TestimonialData" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Home</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/home.css" rel="stylesheet" >
        <link type="text/css" href="./css/components/section_product.css" rel="stylesheet" >
        <link type="text/css" href="./css/home/swiper-bundle.min.css" rel="stylesheet" >
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
        <script src="./js/components/section_products.js"></script> 
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
        <jsp:useBean id="courseCategoryList" class="List<JPAEntity.CourseCategory>" scope="request" />
        <jsp:useBean id="eachCourseCatRegistered" class="List<Integer>" scope="request" />
        <jsp:useBean id="programmingCourse" class="List<JPAEntity.Courses>" scope="request" />
        <jsp:useBean id="programmingAuthorContribution" class="Map<String, List<JPAEntity.Authors>>" scope="request" />
        <jsp:useBean id="programmingItemStatus" class="Map<String, List<Integer>>" scope="request" />
        <jsp:useBean id="designCourse" class="List<JPAEntity.Courses>" scope="request" />
        <jsp:useBean id="designAuthorContribution" class="Map<String, List<JPAEntity.Authors>>" scope="request" />
        <jsp:useBean id="designItemStatus" class="Map<String, List<Integer>>" scope="request" />
        <jsp:useBean id="allCourse" class="List<JPAEntity.Courses>" scope="request" />
        <jsp:useBean id="allAuthorContribution" class="Map<String, List<JPAEntity.Authors>>" scope="request" />
    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <!-- Image Carousel -->

        <section class="section slideshow-section flex-col">
            <div class="slideshow-frame">
                <div id="first_slide" class="pic-show fade">
                    <div class="idx-show"></div>
                    <img class="img-slide" src="./img/home/carousel/img_carousel_1.jpg">
                </div>

                <div class="pic-show fade">
                    <div class="idx-show"></div>
                    <img class="img-slide" src="./img/home/carousel/img_carousel_2.jpg">
                </div>

                <div class="pic-show fade">
                    <div class="idx-show"></div>
                    <img class="img-slide" src="./img/home/carousel/img_carousel_3.jpg">
                </div>

                <div class="pic-show fade">
                    <div class="idx-show"></div>
                    <img class="img-slide" src="./img/home/carousel/img_carousel_4.jpg">
                </div>

                <a class="prev" onclick="manual(-1)">&#10094;</a>
                <a class="next" onclick="manual(1)">&#10095;</a>
            </div>

            <div id="dot-below">
                <span class="dot active-dot" onclick="show(1)" title="1"></span>
                <span class="dot" onclick="show(2)" title="2"></span>
                <span class="dot" onclick="show(3)" title="3"></span>
                <span class="dot" onclick="show(4)" title="4"></span>
            </div>
            <script src="./js/home/carousel.js"></script>
        </section>

        <!-- Learning section -->
        <!--JSP conditional rendering-->
        <c:choose>
            <c:when test="${not empty userData.accountId.username}">
                <section class="section learning-section">
                    <div>

                    </div>
                </section>
            </c:when>
        </c:choose>

        <!-- Hottest Product -->
        <section class="section hottest-course-section">
            <div class="course-section-div">
                <div class="course-section-title flex-row">
                    <h1>Hottest Courses in 2024</h1>
                    <a href="#"><p>View More</p></a>
                </div>
                <div class="courses flex-row">

                    <%
                        int allCounter = 0;
                        for (Courses eachAllCourse : allCourse) {
                            if ("Active".equals(eachAllCourse.getProductId().getStatus())) {
                    %>

                    <div class="course-product" courseID="<%=eachAllCourse.getCourseId()%>" onclick="redirectToProductPage(this)">
                        <div class="course-product-card">
                            <div class="product-card-top">
                                <img src="./img/course/beginner_excel.jpg" alt="">
                                <div class="action-btn-field flex-row">
                                    <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                    <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                </div>
                            </div>
                            <div class="product-card-bottom flex-col">
                                <h1 class="course-title"><%=eachAllCourse.getProductId().getProdName()%></h1>
                                <p class="course-category"><%=eachAllCourse.getCoursecatId().getCategoryName()%></p>
                                <%
                                    List<Authors> currentAuthors = allAuthorContribution.get(eachAllCourse.getCourseId());
                                    String concatAuthors = "";
                                    for (int i = 0; i < currentAuthors.size(); i++) {
                                        concatAuthors += currentAuthors.get(i).getAuthorName();
                                        if (i < currentAuthors.size() - 1) {
                                            concatAuthors += ", ";
                                        }
                                    }
                                %>
                                <p class="course-author"><%=concatAuthors%></p>
                                <div class="course-review flex-row">
                                    <p class="rating-digit"><%=String.format("%.1f", eachAllCourse.getProductId().getAvgRating())%></p>
                                    <i class="ri-star-fill"></i>
                                    <p class="rating-number-field">(<span class="raing-number"><%=eachAllCourse.getProductId().getRateWeightage()%></span>)</p>
                                </div>
                                <div class="course-label flex-row">
                                    <p><%=String.format("%.2f", eachAllCourse.getLengthHour())%> Hours</p>
                                    <p><%=eachAllCourse.getCourseLevel()%></p>
                                </div>
                                <div class="course-price-field flex-row">
                                    <%
                                        double originalPrice = eachAllCourse.getProductId().getPrice();
                                        double discountedPrice = (100 - eachAllCourse.getProductId().getDiscount()) / 100 * (eachAllCourse.getProductId().getPrice());
                                    %>
                                    <p class="course-price">RM <span><%=String.format("%.2f", (originalPrice == discountedPrice) ? originalPrice : discountedPrice)%></span></p>                                      
                                    <%if (originalPrice != discountedPrice) {%>
                                    <p class="course-normal-price">RM <span><%=String.format("%.2f", originalPrice)%></span></p>
                                    <%}%>
                                </div>
                                <div class="course-tag-field flex-row">
                                    <p class="course-tag tag-orange">Hot Sell</p>
                                    <%
                                        long difference = new Date().getTime() - eachAllCourse.getDateAdded().getTime();
                                        long millisecondsInMonth = 30L * 24 * 60 * 60 * 1000;
                                        if (difference < millisecondsInMonth) {
                                    %>
                                    <p class="course-tag tag-yellow">New Course</p>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>

                    <% }
                            allCounter++;
                        } %>

                </div>
            </div>
        </section>

        <!-- Unit Collaboration -->
        <section class="section unit-collaboration-section">
            <div class="unit-collborate-div">
                <p>We collaborate with <span>325+ leading companies and universities</span></p>
                <div class="logos logos-row-1">
                    <div class="logos-slide logos-slide-row-1">
                        <%
                            List<Map<String, String>> companies = CollaborateLogos.getCompanies();
                            for (Map<String, String> company : companies) {
                        %>
                        <a href="<%= company.get("link")%>" target="_blank">
                            <img src="<%= company.get("image")%>" alt="<%= company.get("alt")%>" title="<%= company.get("title")%>" />
                        </a>
                        <% } %>
                    </div>
                </div>
                <div class="logos logos-row-2">
                    <div class="logos-slide logos-slide-row-2">
                        <%
                            List<Map<String, String>> universities = CollaborateLogos.getUniversities();
                            for (Map<String, String> university : universities) {
                        %>
                        <a href="<%= university.get("link")%>" target="_blank">
                            <img src="<%= university.get("image")%>" alt="<%= university.get("alt")%>" title="<%= university.get("title")%>" />
                        </a>
                        <% } %>
                    </div>
                </div>
            </div>
            <!--JS for the moving logo-->
            <script>
                // Clone and append elements from each row
                for (var i = 1; i <= 2; i++) {
                    var sourceRow = document.querySelector(".logos-slide-row-" + i);
                    var targetRow = document.querySelector(".logos-row-" + i);
                    targetRow.appendChild(sourceRow.cloneNode(true));
                }
            </script>
        </section>

        <!-- Design Product -->
        <section class="section hottest-course-section">
            <div class="course-section-div">
                <div class="course-section-title flex-row">
                    <h1>Top courses in Design</h1>
                    <a href="#"><p>View More</p></a>
                </div>
                <div class="courses flex-row">

                    <%
                        int designCounter = 0;
                        for (Courses design : designCourse) {
                            if ("Active".equals(design.getProductId().getStatus())) {
                    %>

                    <div class="course-product" courseID=<%=design.getCourseId()%> onclick="redirectToProductPage(this)">
                        <div class="course-product-card">
                            <div class="product-card-top">
                                <img src="./img/course/beginner_excel.jpg" alt="">
                                <%
                                    List<Integer> designCurrentStatus = designItemStatus.get(design.getCourseId());
                                %>
                                <div class="action-btn-field flex-row">
                                    <button class="cart-Btn" status=<%=designCurrentStatus.get(0)%> onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                    <button class="wish-Btn" status=<%=designCurrentStatus.get(1)%> onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                </div>
                            </div>
                            <div class="product-card-bottom flex-col">
                                <h1 class="course-title"><%=design.getProductId().getProdName()%></h1>
                                <p class="course-category">Design</p>
                                <%
                                    List<Authors> currentAuthors = designAuthorContribution.get(design.getCourseId());
                                    String concatAuthors = "";
                                    for (int i = 0; i < currentAuthors.size(); i++) {
                                        concatAuthors += currentAuthors.get(i).getAuthorName();
                                        if (i < currentAuthors.size() - 1) {
                                            concatAuthors += ", ";
                                        }
                                    }
                                %>
                                <p class="course-author"><%=concatAuthors%></p>
                                <div class="course-review flex-row">
                                    <p class="rating-digit"><%=String.format("%.1f", design.getProductId().getAvgRating())%></p>
                                    <i class="ri-star-fill"></i>
                                    <p class="rating-number-field">(<span class="raing-number"><%=design.getProductId().getRateWeightage()%></span>)</p>
                                </div>
                                <div class="course-label flex-row">
                                    <p><%=String.format("%.2f", design.getLengthHour())%> Hours</p>
                                    <p><%=design.getCourseLevel()%></p>
                                </div>
                                <div class="course-price-field flex-row">
                                    <%
                                        double originalPrice = design.getProductId().getPrice();
                                        double discountedPrice = (100 - design.getProductId().getDiscount()) / 100 * (design.getProductId().getPrice());
                                    %>
                                    <p class="course-price">RM <span><%=String.format("%.2f", (originalPrice == discountedPrice) ? originalPrice : discountedPrice)%></span></p>                                      
                                    <%if (originalPrice != discountedPrice) {%>
                                    <p class="course-normal-price">RM <span><%=String.format("%.2f", originalPrice)%></span></p>
                                    <%}%>
                                </div>
                                <div class="course-tag-field flex-row">
                                    <p class="course-tag tag-orange">Hot Sell</p>
                                    <%
                                        long difference = new Date().getTime() - design.getDateAdded().getTime();
                                        long millisecondsInMonth = 30L * 24 * 60 * 60 * 1000;
                                        if (difference < millisecondsInMonth) {
                                    %>
                                    <p class="course-tag tag-yellow">New Course</p>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>

                    <% }
                            designCounter++;
                        } %>

                </div>
            </div>
        </section>

        <!-- Information section -->
        <section class="section home-information-section">
            <div class="home-info-div">
                <div class="home-info-div-col">
                    <p class="info-icon"><i class="ri-vidicon-fill"></i></p>
                    <p class="info-desc">Learn in-demand skill with over 210,000 video courses</p>
                </div>
                <div class="home-info-div-col">
                    <p class="info-icon"><i class="ri-bard-fill"></i></p>
                    <p class="info-desc">Choose course taught by real-world experts</p>
                </div>
                <div class="home-info-div-col">
                    <p class="info-icon"><i class="ri-pass-valid-fill"></i></p>
                    <p class="info-desc">Learn at your own pace, with life time access</p>
                </div>
            </div>
        </section>

        <!-- Programming Product -->
        <section class="section hottest-course-section">
            <div class="course-section-div">
                <div class="course-section-title flex-row">
                    <h1>Top courses in Programming</h1>
                    <a href="#"><p>View More</p></a>
                </div>
                <div class="courses flex-row">

                    <%
                        int programmingCounter = 0;
                        for (Courses programming : programmingCourse) {
                            if ("Active".equals(programming.getProductId().getStatus())) {
                    %>

                    <div class="course-product" courseID=<%=programming.getCourseId()%> onclick="redirectToProductPage(this)">
                        <div class="course-product-card">
                            <div class="product-card-top">
                                <img src="./img/course/beginner_excel.jpg" alt="">
                                <%
                                    List<Integer> programmingCurrentStatus = programmingItemStatus.get(programming.getCourseId());
                                %>
                                <div class="action-btn-field flex-row">
                                    <button class="cart-Btn" status=<%=programmingCurrentStatus.get(0)%> onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                    <button class="wish-Btn" status=<%=programmingCurrentStatus.get(1)%> onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                </div>
                            </div>
                            <div class="product-card-bottom flex-col">
                                <h1 class="course-title"><%=programming.getProductId().getProdName()%></h1>
                                <p class="course-category">Programming</p>
                                <%
                                    List<Authors> currentAuthors = programmingAuthorContribution.get(programming.getCourseId());
                                    String concatAuthors = "";
                                    for (int i = 0; i < currentAuthors.size(); i++) {
                                        concatAuthors += currentAuthors.get(i).getAuthorName();
                                        if (i < currentAuthors.size() - 1) {
                                            concatAuthors += ", ";
                                        }
                                    }
                                %>
                                <p class="course-author"><%=concatAuthors%></p>
                                <div class="course-review flex-row">
                                    <p class="rating-digit"><%=String.format("%.1f", programming.getProductId().getAvgRating())%></p>
                                    <i class="ri-star-fill"></i>
                                    <p class="rating-number-field">(<span class="raing-number"><%=programming.getProductId().getRateWeightage()%></span>)</p>
                                </div>
                                <div class="course-label flex-row">
                                    <p><%=String.format("%.2f", programming.getLengthHour())%> Hours</p>
                                    <p><%=programming.getCourseLevel()%></p>
                                </div>
                                <div class="course-price-field flex-row">
                                    <%
                                        double originalPrice = programming.getProductId().getPrice();
                                        double discountedPrice = (100 - programming.getProductId().getDiscount()) / 100 * (programming.getProductId().getPrice());
                                    %>
                                    <p class="course-price">RM <span><%=String.format("%.2f", (originalPrice == discountedPrice) ? originalPrice : discountedPrice)%></span></p>
                                    <%if (originalPrice != discountedPrice) {%>
                                    <p class="course-normal-price">RM <span><%=String.format("%.2f", originalPrice)%></span></p>
                                    <%}%>
                                </div>
                                <div class="course-tag-field flex-row">
                                    <p class="course-tag tag-orange">Hot Sell</p>
                                    <%
                                        long difference = new Date().getTime() - programming.getDateAdded().getTime();
                                        long millisecondsInMonth = 30L * 24 * 60 * 60 * 1000;
                                        if (difference < millisecondsInMonth) {
                                    %>
                                    <p class="course-tag tag-yellow">New Course</p>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>

                    <% }
                            programmingCounter++;
                        } %>

                </div>
            </div>
        </section>

        <!-- Feedback from customer -->
        <svg class="testimonial-wave-top" style="transform:rotate(180deg); transition: 0.3s" viewBox="0 0 1440 250" version="1.1" xmlns="http://www.w3.org/2000/svg"><path style="transform:translate(0, 0px); opacity:1;" d="M0,50L48,62.5C96,75,192,100,288,120.8C384,142,480,158,576,137.5C672,117,768,58,864,62.5C960,67,1056,133,1152,145.8C1248,158,1344,117,1440,95.8C1536,75,1632,75,1728,91.7C1824,108,1920,142,2016,162.5C2112,183,2208,192,2304,179.2C2400,167,2496,133,2592,129.2C2688,125,2784,150,2880,141.7C2976,133,3072,92,3168,100C3264,108,3360,167,3456,183.3C3552,200,3648,175,3744,170.8C3840,167,3936,183,4032,183.3C4128,183,4224,167,4320,145.8C4416,125,4512,100,4608,108.3C4704,117,4800,158,4896,150C4992,142,5088,83,5184,58.3C5280,33,5376,42,5472,62.5C5568,83,5664,117,5760,116.7C5856,117,5952,83,6048,58.3C6144,33,6240,17,6336,12.5C6432,8,6528,17,6624,50C6720,83,6816,142,6864,170.8L6912,200L6912,250L6864,250C6816,250,6720,250,6624,250C6528,250,6432,250,6336,250C6240,250,6144,250,6048,250C5952,250,5856,250,5760,250C5664,250,5568,250,5472,250C5376,250,5280,250,5184,250C5088,250,4992,250,4896,250C4800,250,4704,250,4608,250C4512,250,4416,250,4320,250C4224,250,4128,250,4032,250C3936,250,3840,250,3744,250C3648,250,3552,250,3456,250C3360,250,3264,250,3168,250C3072,250,2976,250,2880,250C2784,250,2688,250,2592,250C2496,250,2400,250,2304,250C2208,250,2112,250,2016,250C1920,250,1824,250,1728,250C1632,250,1536,250,1440,250C1344,250,1248,250,1152,250C1056,250,960,250,864,250C768,250,672,250,576,250C480,250,384,250,288,250C192,250,96,250,48,250L0,250Z"></path></svg>
        <section class="section testimonial-section">
            <div class="testimonial-div flex-col">

                <!--testimonial title-->
                <div class="testimonial-top flex-col">
                    <h1>Feedback From Hero Community</h1>
                    <p>100K+ people have already enrolled</p>
                </div>

                <!--testimonial cards-->
                <div class="testimonial-container swiper">
                    <div class="testimonial-content swiper-wrapper">
                        <!-- Slides -->
                        <%
                            List<Map<String, String>> testimonials = TestimonialData.getTestimonials();
                            for (Map<String, String> testimonial : testimonials) {
                        %>
                        <article class="testimonial-slide flex-col swiper-slide">
                            <div class="testimonial-slide-top flex-col">
                                <img class="testimonial-img" src="<%= testimonial.get("image")%>" alt="" />
                                <h1 class="testimonial-name"><%= testimonial.get("name")%></h1>
                                <h2 class="tsetimonial-country"><%= testimonial.get("country")%></h2>
                                <h3 class="testimonial-position"><%= testimonial.get("position")%></h3>
                            </div>
                            <hr class="testimonial-slide-divider" />
                            <div class="testimonial-slide-bot flex-col">
                                <p class="testimonial-feedback"><%= testimonial.get("comment")%></p>
                            </div>
                        </article>
                        <% }%>

                    </div>

                    <!-- Navigation buttons -->
                    <div class="swiper-button-next">
                        <i class="ri-arrow-right-s-line"></i>
                    </div>

                    <div class="swiper-button-prev">
                        <i class="ri-arrow-left-s-line"></i>
                    </div>

                </div>

            </div>

            <!--=============== Swiper js ===============-->
            <script src="./js/home/swiper-bundle.min.js"></script>

            <!--=============== Testimonial js ===============-->
            <script src="./js/home/testimonial.js"></script>
        </section>
        <svg class="testimonial-wave-bot" style="transform:rotate(0deg); transition: 0.3s" viewBox="0 0 1440 250" version="1.1" xmlns="http://www.w3.org/2000/svg"><path style="transform:translate(0, 0px); opacity:1;" d="M0,50L48,62.5C96,75,192,100,288,120.8C384,142,480,158,576,137.5C672,117,768,58,864,62.5C960,67,1056,133,1152,145.8C1248,158,1344,117,1440,95.8C1536,75,1632,75,1728,91.7C1824,108,1920,142,2016,162.5C2112,183,2208,192,2304,179.2C2400,167,2496,133,2592,129.2C2688,125,2784,150,2880,141.7C2976,133,3072,92,3168,100C3264,108,3360,167,3456,183.3C3552,200,3648,175,3744,170.8C3840,167,3936,183,4032,183.3C4128,183,4224,167,4320,145.8C4416,125,4512,100,4608,108.3C4704,117,4800,158,4896,150C4992,142,5088,83,5184,58.3C5280,33,5376,42,5472,62.5C5568,83,5664,117,5760,116.7C5856,117,5952,83,6048,58.3C6144,33,6240,17,6336,12.5C6432,8,6528,17,6624,50C6720,83,6816,142,6864,170.8L6912,200L6912,250L6864,250C6816,250,6720,250,6624,250C6528,250,6432,250,6336,250C6240,250,6144,250,6048,250C5952,250,5856,250,5760,250C5664,250,5568,250,5472,250C5376,250,5280,250,5184,250C5088,250,4992,250,4896,250C4800,250,4704,250,4608,250C4512,250,4416,250,4320,250C4224,250,4128,250,4032,250C3936,250,3840,250,3744,250C3648,250,3552,250,3456,250C3360,250,3264,250,3168,250C3072,250,2976,250,2880,250C2784,250,2688,250,2592,250C2496,250,2400,250,2304,250C2208,250,2112,250,2016,250C1920,250,1824,250,1728,250C1632,250,1536,250,1440,250C1344,250,1248,250,1152,250C1056,250,960,250,864,250C768,250,672,250,576,250C480,250,384,250,288,250C192,250,96,250,48,250L0,250Z"></path></svg>

        <!-- Category section -->
        <section class="section category-section">
            <div class="category-div flex-col">
                <div class="category-div-top">
                    <h1>Explore More Courses</h1>
                </div>
                <div class="category-div-bot">
                    <%
                        int courseCatCounter = 0;
                        for (CourseCategory course : courseCategoryList) {
                            String qtyMsg = "New Course Coming Soon";
                            if (eachCourseCatRegistered.get(courseCatCounter) > 1) {
                                qtyMsg = " Courses";
                            } else if (eachCourseCatRegistered.get(courseCatCounter) == 1) {
                                qtyMsg = " Course";
                            }
                    %>
                    <a href="#">
                        <div class="category-card  flex-row">
                            <div class="category-card-left">
                                <img src="./img/categories/math.png" alt="Mathematics" />
                            </div>
                            <div class="category-card-right flex-col">
                                <h1 class="category-name"><%= course.getCategoryName()%></h1>
                                <p class="category-course-qty"><span><%= (eachCourseCatRegistered.get(courseCatCounter) > 0) ? eachCourseCatRegistered.get(courseCatCounter) : ""%></span><%=qtyMsg%></p>
                            </div>
                        </div>
                    </a>
                    <%  courseCatCounter++;
                        }%>
                </div>
            </div>
        </section>

        <!-- FAQ section -->
        <section class="section faq-section" id="faq">
            <div class="faq-div flex-col">

                <!--faq title-->
                <div class="faq-div-top flex-col">
                    <h1>Frequently Asked Questions</h1>
                    <p>Quick answer to question you might have about the courses, service, or topic</p>
                </div>

                <!--faq content-->
                <div class="faq-div-content flex-col">

                    <!--Row 1--> 
                    <div class="faq-row flex-row">
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-store-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>What is Course Hero?</h1>
                                <p>Course Hero is an online platform that offers a diverse range of courses in various subjects. We aim to provide high-quality educational content to learners worldwide.</p>
                            </div>
                        </div>
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-book-3-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>How do I enroll in a course?</h1>
                                <p>Enrolling in a course is simple! Just browse our course catalog, select the course you're interested in, and click on the "Add to cart" button. Follow the prompts to complete the enrollment process.</p>
                            </div>
                        </div>
                    </div>

                    <!--Row 2--> 
                    <div class="faq-row flex-row">
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-user-3-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>Are the courses self-paced or scheduled?</h1>
                                <p>All of our courses are self-paced, allowing you to learn at your own convenience. You can find this information on the course page.</p>
                            </div>
                        </div>
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-computer-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>Can I access my courses on any device?</h1>
                                <p>Yes, you can access your courses on any device with an internet connection, including desktop computers, laptops, tablets, and smartphones.</p>
                            </div>
                        </div>
                    </div>

                    <!--Row 3--> 
                    <div class="faq-row flex-row">
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-award-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>Are there any prerequisites for the courses?</h1>
                                <p>Prerequisites vary depending on the course. Some courses may require prior knowledge or experience in a specific subject, while others are suitable for beginners. Check the course description for details on prerequisites.</p>
                            </div>
                        </div>
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-file-text-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>Can I preview a course before enrolling?</h1>
                                <p>Yes, you can! Many of our courses offer a free preview or introductory lesson, allowing you to get a feel for the content before enrolling.</p>
                            </div>
                        </div>
                    </div>

                    <!--Row 4--> 
                    <div class="faq-row flex-row">
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-pass-valid-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>Are certificates offered upon course completion?</h1>
                                <p>Yes, we provide certificates of completion for many of our courses. These certificates can be a valuable addition to your resume or portfolio.</p>
                            </div>
                        </div>
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-money-dollar-box-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>What payment methods do you accept?</h1>
                                <p>We accept various payment methods, including credit/debit cards, Touch N Go, and other secure online payment options. You can choose the payment method that is most convenient for you during the checkout process.</p>
                            </div>
                        </div>
                    </div>

                    <!--Row 5--> 
                    <div class="faq-row flex-row">
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-hand-heart-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>Is financial aid available for courses?</h1>
                                <p>We strive to make education accessible to everyone. Some courses may offer financial aid or scholarships. Check the course page for details on available assistance options.</p>
                            </div>
                        </div>
                        <div class="faq-col flex-row">
                            <div class="faq-icon">
                                <p><i class="ri-book-read-line"></i></p>
                            </div>
                            <div class="faq-text flex-col">
                                <h1>How long do I have access to a course after enrolling?</h1>
                                <p>You will have lifetime access to any course you enroll in. This means you can revisit course materials and resources at any time, even after you've completed the course.</p>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="faq-div-bot flex-col">
                    <button class="faq-more-btn" status="0" onclick="faqShow(this)">Show more</button>

                    <div class="faq-help flex-row">
                        <img src="./img/home/customer-service.png" alt="Live Support" />
                        <div class="faq-help-text flex-col">
                            <h1>Still have questions?</h1>
                            <p>Can't find the answer you're looking for? Please chat to our friendly team.</p>
                        </div>
                        <a class="faq-help-btn" href="#">Get in touch <i class="ri-send-plane-fill"></i></a>
                    </div>
                </div>

            </div>

            <!--import js file for faq section-->
            <script src="./js/home/faq_read_more.js"></script>
        </section>

        <!--JSP conditional rendering-->
        <c:choose>
            <c:when test="${empty userData.accountId.username}">
                <!-- Guest ask to sign up section -->
                <section class="section guest-sign-up-section">
                    <div class="guest-sign-up-div flex-row">
                        <div class="guest-left-div">
                            <img src="./img/home/GuestSignUp.png" alt="Learning" />
                        </div>
                        <div class="guest-right-div flex-col">
                            <h1 class="guest-sign-up-title">Start for free</h1>
                            <p class="guest-sign-up-desc">If you’ve made it this far, you must be at least a little curious. Sign up and take the first step toward your goals.</p>
                            <a href="<%= webpath.getPageUrl("sign up")%>">
                                Sign Up
                            </a>
                        </div>
                    </div>
                </section>
            </c:when>
        </c:choose>

        <!--Footer import-->
        <%@ include file="./Components/footer_wave.html" %>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
