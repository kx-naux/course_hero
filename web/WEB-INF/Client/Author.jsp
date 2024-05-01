<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Author</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/author.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
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
                        <h2 class="author-name">Dr. Angela Yu</h2>
                        <h3 class="author-position">Developer and Lead Instructor</h3>
                        <p class="author-join-date">Join since 18/6/2021</p>
                    </div>

                    <div class="author-about flex-col">
                        <h1 class="author-left-div-title">About me</h1>
                        <P class="about-content">
                            I'm Angela, I'm a developer with a passion for teaching. I'm the lead instructor at the London App Brewery, London's leading Programming Bootcamp. I've helped hundreds of thousands of students learn to code and change their lives by becoming a developer. I've been invited by companies such as Twitter, Facebook and Google to teach their employees.
                        </P> 
                        <P class="about-content">
                            My first foray into programming was when I was just 12 years old, wanting to build my own Space Invader game. Since then, I've made hundred of websites, apps and games. But most importantly, I realised that my greatest passion is teaching.
                        </P>
                    </div>

                    <div class="author-courses flex-col">

                        <h1 class="author-left-div-title">My courses (15)</h1>

                        <div class="course-div flex-row">

                            <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
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
                            </div>

                            <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
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
                            </div>

                            <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
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
                            </div>

                            <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
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
                            </div>
                            <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
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
                            </div>


                        </div>

                        <form id="authorForm">
                            <!--pagination, 20 course per page-->
                            <%
                                int currentPage = 1;
                                int lastPage = 100;
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
                        <a href="#" class="author-link-btn"><i class="ri-link"></i> Website</a>
                        <a href="#" class="author-link-btn"><i class="ri-linkedin-box-fill"></i> LinkedIn</a>
                        <a href="#" class="author-link-btn"><i class="ri-instagram-line"></i> Instagram</a>
                    </div>

                </div>

            </div>
        </section>

        <script src="./js/author/author.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
