<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="module.CollaborateLogos" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Home</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/home.css" rel="stylesheet" >
        <link type="text/css" href="./css/components/section_product.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <script src="./js/nav.js"></script>   
        <script src="./js/components/section_products.js"></script> 
    </head>
    <body>
        <!--To top button-->
        <button onclick="toTopFunc()" id="toTopBtn" title="Go to top"><i class="ri-arrow-up-s-line"></i></button>

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
                <!--Search course-->
                <a href="http://localhost:8080/CourseHero/search">
                    <p class="nav-icon"><i class="ri-search-line"></i></p>
                </a>
                <div>

                </div>
                <!--JSP conditional rendering-->
                <c:choose>
                    <c:when test="${not empty sessionScope.username}">
                        <!-- Logged in -->
                        <a href="http://localhost:8080/CourseHero/wishlist">
                            <p class="nav-icon"><i class="ri-heart-line"></i></P>
                        </a>
                        <a href="http://localhost:8080/CourseHero/cart">
                            <p class="nav-icon"><i class="ri-shopping-cart-line"></i></P>
                        </a>
                        <input hidden type="text" id="hidden_username" value="${sessionScope.username}"/>
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
            <c:when test="${not empty sessionScope.username}">
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

                    <div class="course-product" courseID="121238719823">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                    <div class="action-btn-field flex-row">
                                        <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                        <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                    </div>
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-category">Microsoft Excel</p>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>9.5 Hours</p>
                                        <p>All Level</p>
                                    </div>
                                    <div class="course-price-field flex-row">
                                        <p class="course-price">RM <span>449.90</span></p>                                      
                                        <p class="course-normal-price">RM <span>650.00</span></p>
                                    </div>
                                    <div class="course-tag-field flex-row">
                                        <p class="course-tag tag-orange">Hot Sell</p>
                                        <p class="course-tag tag-yellow">New Course</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>


                    <div class="course-product" courseID="121238719823">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                    <div class="action-btn-field flex-row">
                                        <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                        <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                    </div>
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-category">Microsoft Excel</p>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>9.5 Hours</p>
                                        <p>All Level</p>
                                    </div>
                                    <div class="course-price-field flex-row">
                                        <p class="course-price">RM <span>449.90</span></p>                                      
                                        <p class="course-normal-price">RM <span>650.00</span></p>
                                    </div>
                                    <div class="course-tag-field flex-row">
                                        <p class="course-tag tag-orange">Hot Sell</p>
                                        <p class="course-tag tag-yellow">New Course</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>


                    <div class="course-product" courseID="121238719823">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                    <div class="action-btn-field flex-row">
                                        <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                        <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                    </div>
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-category">Microsoft Excel</p>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>9.5 Hours</p>
                                        <p>All Level</p>
                                    </div>
                                    <div class="course-price-field flex-row">
                                        <p class="course-price">RM <span>449.90</span></p>                                      
                                        <p class="course-normal-price">RM <span>650.00</span></p>
                                    </div>
                                    <div class="course-tag-field flex-row">
                                        <p class="course-tag tag-red">New</p>
                                        <p class="course-tag tag-green">Free</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>


                    <div class="course-product" courseID="121238719823">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                    <div class="action-btn-field flex-row">
                                        <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                        <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                    </div>
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-category">Microsoft Excel</p>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>9.5 Hours</p>
                                        <p>All Level</p>
                                    </div>
                                    <div class="course-price-field flex-row">
                                        <p class="course-price">RM <span>449.90</span></p>                                      
                                        <p class="course-normal-price">RM <span>650.00</span></p>
                                    </div>
                                    <div class="course-tag-field flex-row">
                                        <p class="course-tag tag-blue">Hot Sell</p>
                                        <p class="course-tag tag-purple">New Course</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>


                    <div class="course-product" courseID="121238719823">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                    <div class="action-btn-field flex-row">
                                        <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                        <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                    </div>
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-category">Microsoft Excel</p>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>9.5 Hours</p>
                                        <p>All Level</p>
                                    </div>
                                    <div class="course-price-field flex-row">
                                        <p class="course-price">RM <span>449.90</span></p>                                      
                                        <p class="course-normal-price">RM <span>650.00</span></p>
                                    </div>
                                    <div class="course-tag-field flex-row">
                                        <p class="course-tag tag-orange">Hot Sell</p>
                                        <p class="course-tag tag-yellow">New Course</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>

                    <div class="course-product" courseID="121238719823">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                    <div class="action-btn-field flex-row">
                                        <button class="cart-Btn" status="0" onclick="cartButtonClick(event)"><i class="ri-shopping-cart-line"></i></button>
                                        <button class="wish-Btn" status="0" onclick="likeButtonClick(event)"><i class="ri-heart-line"></i></button>
                                    </div>
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-category">Microsoft Excel</p>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                    </div>
                                    <div class="course-label flex-row">
                                        <p>9.5 Hours</p>
                                        <p>All Level</p>
                                    </div>
                                    <div class="course-price-field flex-row">
                                        <p class="course-price">RM <span>449.90</span></p>                                      
                                        <p class="course-normal-price">RM <span>650.00</span></p>
                                    </div>
                                    <div class="course-tag-field flex-row">
                                        <p class="course-tag tag-orange">Hot Sell</p>
                                        <p class="course-tag tag-yellow">New Course</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>

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
                        <a href="<%= company.get("link") %>" target="_blank">
                            <img src="<%= company.get("image") %>" alt="<%= company.get("alt") %>" title="<%= company.get("title") %>" />
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
                        <a href="<%= university.get("link") %>" target="_blank">
                            <img src="<%= university.get("image") %>" alt="<%= university.get("alt") %>" title="<%= university.get("title") %>" />
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

        <!-- Information section -->
        <section class="section home-information-section">
            <div class="home-info-div">
                <div class="home-info-div-col">
                    <p class="info-icon"><i class="ri-vidicon-fill"></i></i></p>
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

        <!-- Feedback from customer -->
        <section class="section feedback-section">

        </section>

        <!-- Category section -->
        <section class="section category-section">
            <div class="category-div flex-col">
                <div class="category-div-top">
                    <h1>Explore More Courses</h1>
                </div>
                <div class="category-div-bot">

                    <a href="#">
                        <div class="category-card">
                            <div class="category-card-left">
                                <img src="" alt="" />
                            </div>
                            <div class="category-card-right flex-col">
                                <h1 class="category-name">Data Science</h1>
                                <p class="category-course-qty"><span>12</span> Courses</p>
                            </div>
                        </div>
                    </a>

                </div>
            </div>
        </section>

        <!-- FAQ section -->
        <section class="section faq-section">

        </section>

        <!--JSP conditional rendering-->
        <c:choose>
            <c:when test="${empty sessionScope.username}">
                <!-- Guest ask to sign up section -->
                <section class="section guest-sign-up-section">
                    <div class="guest-sign-up-div flex-row">
                        <div class="guest-left-div">
                            <img src="./img/home/GuestSignUp.png" alt="Learning" />
                        </div>
                        <div class="guest-right-div flex-col">
                            <h1 class="guest-sign-up-title">Start for free</h1>
                            <p class="guest-sign-up-desc">If you’ve made it this far, you must be at least a little curious. Sign up and take the first step toward your goals.</p>
                            <a href="http://localhost:8080/CourseHero/sign-up">
                                Sign Up
                            </a>
                        </div>
                    </div>
                </section>
            </c:when>
        </c:choose>

        <!--Wave svg https://getwaves.io/ -->
        <div class="footer-wave-div">
            <svg id="wave" style="transform:rotate(0deg); transition: 0.3s" viewBox="0 0 1440 290" version="1.1" xmlns="http://www.w3.org/2000/svg">
            <path style="transform:translate(0, 0px); opacity:1" fill="url(#sw-gradient-0)" d="M0,0L80,29C160,58,320,116,480,116C640,116,800,58,960,38.7C1120,19,1280,39,1440,82.2C1600,126,1760,193,1920,198.2C2080,203,2240,145,2400,101.5C2560,58,2720,29,2880,43.5C3040,58,3200,116,3360,120.8C3520,126,3680,77,3840,77.3C4000,77,4160,126,4320,159.5C4480,193,4640,213,4800,203C4960,193,5120,155,5280,154.7C5440,155,5600,193,5760,207.8C5920,222,6080,213,6240,212.7C6400,213,6560,222,6720,198.2C6880,174,7040,116,7200,82.2C7360,48,7520,39,7680,43.5C7840,48,8000,68,8160,62.8C8320,58,8480,29,8640,24.2C8800,19,8960,39,9120,48.3C9280,58,9440,58,9600,72.5C9760,87,9920,116,10080,125.7C10240,135,10400,126,10560,135.3C10720,145,10880,174,11040,188.5C11200,203,11360,203,11440,203L11520,203L11520,290L11440,290C11360,290,11200,290,11040,290C10880,290,10720,290,10560,290C10400,290,10240,290,10080,290C9920,290,9760,290,9600,290C9440,290,9280,290,9120,290C8960,290,8800,290,8640,290C8480,290,8320,290,8160,290C8000,290,7840,290,7680,290C7520,290,7360,290,7200,290C7040,290,6880,290,6720,290C6560,290,6400,290,6240,290C6080,290,5920,290,5760,290C5600,290,5440,290,5280,290C5120,290,4960,290,4800,290C4640,290,4480,290,4320,290C4160,290,4000,290,3840,290C3680,290,3520,290,3360,290C3200,290,3040,290,2880,290C2720,290,2560,290,2400,290C2240,290,2080,290,1920,290C1760,290,1600,290,1440,290C1280,290,1120,290,960,290C800,290,640,290,480,290C320,290,160,290,80,290L0,290Z"></path>
            </svg>
        </div>
        <!--Footer import-->
        <%@ include file="../Static_Components/footer.html" %>  
    </body>
</html>
