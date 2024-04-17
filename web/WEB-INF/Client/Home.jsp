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
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!--To top button-->
        <button onclick="toTopFunc()" id="toTopBtn" title="Go to top"><i class="ri-arrow-up-s-line"></i></button>

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
                        <div class="category-card flex-row">
                            <div class="category-card-left">
                                <img src="./img/categories/data_science.png" alt="Data Science" />
                            </div>
                            <div class="category-card-right flex-col">
                                <h1 class="category-name">Data Science</h1>
                                <p class="category-course-qty"><span>12</span> Courses</p>
                            </div>
                        </div>
                    </a>

                    <a href="#">
                        <div class="category-card flex-row">
                            <div class="category-card-left">
                                <img src="./img/categories/business.png" alt="Business" />
                            </div>
                            <div class="category-card-right flex-col">
                                <h1 class="category-name">Business</h1>
                                <p class="category-course-qty"><span>8</span> Courses</p>
                            </div>
                        </div>
                    </a>

                    <a href="#">
                        <div class="category-card  flex-row">
                            <div class="category-card-left">
                                <img src="./img/categories/math.png" alt="Mathematics" />
                            </div>
                            <div class="category-card-right flex-col">
                                <h1 class="category-name">Mathematics</h1>
                                <p class="category-course-qty"><span>12</span> Courses</p>
                            </div>
                        </div>
                    </a>

                    <a href="#">
                        <div class="category-card flex-row">
                            <div class="category-card-left">
                                <img src="./img/categories/language_learning.png" alt="Language Learning" />
                            </div>
                            <div class="category-card-right flex-col">
                                <h1 class="category-name">Language Learning</h1>
                                <p class="category-course-qty"><span>7</span> Courses</p>
                            </div>
                        </div>
                    </a>

                </div>
            </div>
        </section>

        <!-- FAQ section -->
        <section class="section faq-section">
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
                    <button class="faq-more-btn" status="0" onclick="faqShow(this)">Show More</button>

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

        <!--Footer import-->
        <%@ include file="./Components/footer_wave.html" %>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
