<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <!-- Hottest Product -->
        <section class="section hottest-course-section">
            <div class="course-section-div">
                <div class="course-section-title flex-row">
                    <h1>Hottest Courses in 2024</h1>
                    <a href="#"><p>View More</p></a>
                </div>
                <div class="courses flex-row">

                    <div class="course-product" courseID="">
                        <a href="#">
                            <div class="course-product-card">
                                <div class="product-card-top">
                                    <img src="./img/course/beginner_excel.jpg" alt="">
                                </div>
                                <div class="product-card-bottom flex-col">
                                    <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                    <p class="course-author">Woo Yu Beng, Snijders Wang Wang, Low Kah Xuan</p>
                                    <div class="course-review flex-row">
                                        <p class="rating-digit">3.5</p>
                                        <i class="ri-star-fill"></i>
                                        <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
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
                        <div class="course-product-desc">
                            <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                            <p class="course-category">Microsoft Excel</p>
                            <p class="course-update">Updated on <span class="course-update-date">8/4/2024</span></p>
                            <div class="course-label flex-row">
                                <p>9.5 Hours</p>
                                <p>All Level</p>
                            </div>
                            <p class="course-desc">This course is ultimate course for excel users.</p>
                            <ul class="course-features">
                                <li><p>Go from complete beginner to proficient Excel user within 10 hours of content.</p></li>
                                <li><p>Learn to create workbooks and spreadsheets everyone will be jealous of.</p></li>
                                <li><p>Acquire critical Excel knowledge within the first 2 hours of tutorials.</p></li>
                            </ul>
                            <div class="flex-row">
                                <input class="cart-btn" type="button" value="Add to Cart" onclick="" />
                                <p class="wish-btn"><i class="ri-heart-line"></i></p>
                            </div>
                        </div>
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
                        <img src="./img/home/collaborate/companies/amazon.svg" alt="Amazon" />
                        <img src="./img/home/collaborate/companies/apple.svg" alt="Apple" />
                        <img src="./img/home/collaborate/companies/mooiko.png" alt="Mooiko" />
                        <img src="./img/home/collaborate/companies/mysoftware.png" alt="MySoftware" />
                        <img src="./img/home/collaborate/companies/penta.png" alt="Pentamaster" />       
                        <img src="./img/home/collaborate/companies/sip.png" alt="SIP Technology" />
                        <img src="./img/home/collaborate/companies/qm.webp" alt="Quantum Metal" />
                        <img src="./img/home/collaborate/companies/cisco.svg" alt="Cisco" />
                        <img src="./img/home/collaborate/companies/google.svg" alt="Google" />
                        <img src="./img/home/collaborate/companies/ibm.svg" alt="IBM" />
                        <img src="./img/home/collaborate/companies/meta.svg" alt="Meta" />
                        <img src="./img/home/collaborate/companies/microsoft.svg" alt="Microsoft" />
                        <img src="./img/home/collaborate/companies/tiktok.svg" alt="Tik Tok" />
                        <img src="./img/home/collaborate/companies/riot.svg" alt="Riot Games" />
                    </div>
                </div>
                <div class="logos logos-row-2">
                    <div class="logos-slide logos-slide-row-2">
                        <img src="./img/home/collaborate/universities/Cambridge.svg" alt="University of Cambridge" />
                        <img src="./img/home/collaborate/universities/MIT.svg" alt="Massachusetts Institute of Technology" />
                        <img src="./img/home/collaborate/universities/Oxford.svg" alt="University of Oxford" />
                        <img src="./img/home/collaborate/universities/Peking.svg" alt="Peking University" />
                        <img src="./img/home/collaborate/universities/Princeton.svg" alt="Princeton University" />
                        <img src="./img/home/collaborate/universities/tarumt.png" alt="TARUMT" />
                        <img src="./img/home/collaborate/universities/Stanford.svg" alt="Stanford University" />
                        <img src="./img/home/collaborate/universities/Tsinghua.png" alt="Tsing Hua University" />
                        <img src="./img/home/collaborate/universities/harvard.png" alt="Harvard University" />
                    </div>
                </div>
            </div>
        </div>
        <!--JS for the miving logo-->
        <script>
                    var copy1 = document.querySelector(".logos-slide-row-1").cloneNode(true);
                    document.querySelector(".logos-row-1").appendChild(copy1);

                    var copy2 = document.querySelector(".logos-slide-row-2").cloneNode(true);
                    document.querySelector(".logos-row-2").appendChild(copy2);
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

    <!-- Footer section -->
    <section class="section footer-section">

    </section>
</body>
</html>
