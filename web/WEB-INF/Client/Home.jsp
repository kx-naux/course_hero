<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Home</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/sectionProduct.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <script src="./js/nav.js"></script>
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav class="nav-bar">
            <!-- Logo -->
            <div class="nav-logo-div">
                <a href="http://localhost:8080/CourseHero/home"><img class="nav-logo" src="./img/Logo.png" alt="Course Hero" /></a>
            </div>
            <!--Nav options-->
            <div class="nav-option-div">
                <ul>
                    <li>
                        <a href="http://localhost:8080/CourseHero/home">
                            <div class="nav-option selected">
                                <p>Home</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="http://localhost:8080/CourseHero/about-us">
                            <div class="nav-option">
                                <p>About Us <i class="ri-arrow-down-s-line"></i></p>
                            </div>
                        </a>
                        <div class="nav-option-dropdown">
                            <ul>
                                <li><a href="#">Our Team</a></li>
                                <li><a href="#">Our Office</a></li>
                                <li><a href="#">Contact Us</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="http://localhost:8080/CourseHero/products">
                            <div class="nav-option">
                                <p>Courses</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="http://localhost:8080/CourseHero/learning">
                            <div class="nav-option">
                                <p>Learning</p>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <!--Nav button-->
            <div class="nav-function-div">
                <a href="http://localhost:8080/CourseHero/search">
                    <p class="nav-icon"><i class="ri-search-line"></i></p>
                </a>
                <c:choose>
                    <c:when test="${not empty sessionScope.username}">
                        <!-- Logged in -->
                        <a href="http://localhost:8080/CourseHero/wishlist">
                            <p class="nav-icon"><i class="ri-heart-line"></i></P>
                        </a>
                        <a href="http://localhost:8080/CourseHero/cart">
                            <p class="nav-icon"><i class="ri-shopping-cart-line"></i></P>
                        </a>
                        <p hidden>${sessionScope.username}!</p>
                    </c:when>
                    <c:otherwise>
                        <!-- Not Logged in -->
                        <a href="http://localhost:8080/CourseHero/login">
                            <input class="nav-btn nav-login-btn" type="button" value="Log In" />
                        </a>
                        <a href="http://localhost:8080/CourseHero/sign-up">
                            <input class="nav-btn nav-sign-up-btn" type="button" value="Sign Up" />
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>

        <!-- Image Carousel -->
        <section class="section image-carousel-section">

        </section>

        <!-- Hottest Product -->


        <!-- Unit Collaboration -->
        <section class="section unit-collaboration-section">

        </section>

        <!-- Information section -->
        <section class="section home-information-section">

        </section>

        <!-- Feedback from customer -->
        <section class="section feedback-section">

        </section>

        <!-- Category section -->
        <section class="section category-section">

        </section>

        <!-- FAQ section -->
        <section class="section faq-section">

        </section>

        <!-- Footer section -->
        <section class="section footer-section">

        </section>
    </body>
</html>
