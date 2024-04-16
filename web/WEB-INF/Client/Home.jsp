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

        <!-- Footer section -->
        <section class="section footer-section">
            <div class="footer-div flex-col">
                <div class="footer-top flex-row">
                    <div class="flex-col">
                        <p class="footer-list-title">Courses</p>
                        <ul class="footer-list">
                            <li><a href="#">Catalog</a></li>
                            <li><a href="#">Promotion</a></li>
                        </ul>
                    </div>
                    <div class="flex-col">
                        <p class="footer-list-title">Service</p>
                        <ul class="footer-list">
                            <li><a href="#">Live Support</a></li>
                            <li><a href="#">Help center</a></li>
                            <li><a href="#">How to buy</a></li>
                            <li><a href="#">How to sell</a></li>
                        </ul>
                    </div>
                    <div class="flex-col">
                        <p class="footer-list-title">Company</p>
                        <ul class="footer-list">
                            <li><a href="#">About</a></li>
                            <li><a href="#">News</a></li>
                            <li><a href="#">Investor</a></li>
                            <li><a href="#">Careers</a></li>
                            <li><a href="#">FAQs</a></li>
                            <li><a href="#">Term</a></li>
                        </ul>
                    </div>
                    <!--contact div-->
                    <div class="flex-col">
                        <p class="footer-list-title">Contact</p>
                        <p class="footer-contact"><a href="https://maps.app.goo.gl/mN2fXzWxSR8NDedD7" target="_blank">77, Lorong Lembah Permai 3, 11200 Tanjung Bungah, Pulau Pinang</a></p>  
                        <p class="footer-contact"><a href="tel:+601117608595" target="_blank"><i class="ri-phone-line"></i>(+60) 11-1760 8595</a></p>
                        <p class="footer-contact"><a href="mailto:sales@coursehero.com" target="_blank"><i class="ri-mail-line"></i>sales@coursehero.com</a></p>
                    </div>
                </div>
                <hr class="footer-divider" />
                <div class="footer-bot flex-row">
                    <!--logo and copyright div-->
                    <div class="footer-bot-left flex-row">
                        <a href="http://localhost:8080/CourseHero/home"><img class="footer-logo" src="./img/Logo.png" alt="Course Hero" /></a>
                        <p>@2024 Course Hero Inc. All right reserved.</p>
                    </div>
                    <!--social media div-->
                    <div class="footer-bot-right">
                        
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
