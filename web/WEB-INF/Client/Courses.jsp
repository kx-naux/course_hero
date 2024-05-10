<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Course Hero | Courses</title>
    <link rel="icon" type="image/ico" href="./ico/Logo.ico">
    <link type="text/css" href="./css/style.css" rel="stylesheet">
    <link type="text/css" href="./css/courses.css" rel="stylesheet">
    <link type="text/css" href="./css/components/courses_product.css" rel="stylesheet" >
    <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <jsp:useBean id="webpath" class="module.WebPath" scope="application"/>
<!--    <style>
        .product {
            width: 250px; /* Fixed width for each product */
            height: 250px; /* Adjust height automatically */
            border: 1px solid #ccc;
            margin-bottom: 20px;
            padding: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out;
            text-align: center;
            margin-right: 10px; /* Add right margin to create space between products */

        }

        .products-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start; /* Align products to the left */
            margin-left: 200px;
        }

        .product:hover {
            transform: scale(1.05);
        }

        .product h2 {
            font-size: 16px; 
            margin-top: 10px; /* Add margin to separate image and name */
        }

        .product img {
            max-width: 130%; 
            max-height: 130px; 
        }

        h1 {
            margin-left: 200px;
        }
    </style>-->
</head>

<body>
<!--To top button-->
<%@ include file="./Components/to_top_button.html" %>

<!-- Include the navigation bar -->
<%@ include file="./Components/navbar.jsp" %>

<form id="searchForm" action='courses' method='get'>
    
    <section class="section search-result-section flex-col">
        <div class="search-result-info-div flex-col">
            <div class="result-function flex-row">
                <div class="sort-div">
                    <div class="sort-select flex-row">
                        <select id="sort-type" name="sort" onchange="this.form.submit()">
                            <option name='sort' value="relevance" ${param.sort == 'relevance' ? 'selected' : ''}>Most Relevant
                            </option>
                            <option name='sort' value="most-reviewed" ${param.sort == 'most-reviewed' ? 'selected' : ''}>Most
                                Reviewed
                            </option>
                            <option name='sort' value="highest-rated" ${param.sort == 'highest-rated' ? 'selected' : ''}>Highest
                                Rated
                            </option>
                            <option name='sort' value="newest" ${param.sort == 'newest' ? 'selected' : ''}>Newest</option>
                        </select>
                        <div class="sort-select-icon flex-col"><p><i class="ri-arrow-down-s-line"></i></p></div>
                    </div>
                    <label for="sort-type">Sort by</label>
                </div>
            </div>
        </div>
    </section>

                        
    <h1>Courses</h1>
<!--    <div class="products-container">

        
      <%
            // Example products data
            String[] products = {"Product 1", "Product 2", "Product 3", "Product 4",
                                 "Product 5", "Product 6", "Product 7", "Product 8",
                                 "Product 9", "Product 10", "Product 11", "Product 12"};
            
            String[] descriptions = {"Description 1", "Description 2", "Description 3", "Description 4",
                                     "Description 5", "Description 6", "Description 7", "Description 8",
                                     "Description 9", "Description 10", "Description 11", "Description 12"};
            
            String[] imageUrls = {"./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg",
                                  "./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg",
                                  "./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg",
                                  "./img/course/beginner_excel.jpg", "./img/course/beginner_excel.jpg", "./img/course/wrongUrl.jpg"};
            
            // Sorting values corresponding to each product
            String[] sortingValues = {"relevance", "newest", "highest-rated", "newest", "relevance",
                                      "most-reviewed", "highest-rated", "newest", "relevance", "most-reviewed",
                                      "highest-rated", "newest"};

            // Loop through products and display them with their images and descriptions
            for (int i = 0; i < products.length; i++) {
        %>
           <div class="product" data-sort="<%= sortingValues[i] %>">
            <img src="<%= imageUrls[i] %>" alt="Image not found!">
            <h2><%= products[i] %></h2>
            <p><%= descriptions[i] %></p>
            </div> 
        <% } %>
    </div>-->
        <div class="products-container">
    <div class="courses">
        <% for (int i = 0; i < products.length; i++) { %>
            <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
              <div class="course-product" courseID="121238719823" onclick="redirectToProductPage(this)">
                        <div class="cosurse-product-card">
                            <div class="product-card-top">
                                <img src="./img/course/beginner_excel.jpg" onerror="this.src='./img/course/beginner_excel.jpg'" alt="">
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
        <% } %>
    </div>
</div>


                    
    <!--pagination, 20 course per page-->
    <%
        int currentPage = 1;
        int lastPage = 500;
        if (request.getParameter("p") != null) {
            currentPage = Integer.parseInt(request.getParameter("p"));
        }

        boolean previousAllow = currentPage > 1;
        boolean nextAllow = currentPage < lastPage;
    %>
    <input type="number" id="current_page" name="p" value="<%= currentPage%>" hidden/>
    <div class="result-pagination-div flex-row">
        <p class="page-action-btn page-previous-btn <%= previousAllow ? "allow" : "" %>"><i
                class="ri-arrow-left-s-line"></i></p>
        <% if (currentPage - 1 != 0) { %>
        <p class="first-page-number page-number">1</p>
        <% }
            if (currentPage - 1 != 1 && currentPage - 1 != 2 && currentPage - 1 > 0) { %>
        <p class="number-dot">...</p>
        <% }
            if (currentPage - 1 > 1) { %>
        <p class="first-page-number page-number"><%= currentPage - 1 %></p>
        <% } %>
        <p class="first-page-number page-number current-page"><%= currentPage %></p>
        <% if (currentPage + 1 < lastPage) { %>
        <p class="first-page-number page-number"><%= currentPage + 1 %></p>
        <%
            }
            if (currentPage + 1 != lastPage && currentPage + 2 != lastPage && currentPage + 1 < lastPage) {
        %>
        <p class="number-dot">...</p>
        <% }
            if (lastPage > 1 && currentPage != lastPage) { %>
        <p class="last-page-number page-number"><%= lastPage %></p>
        <% } %>
        <p class="page-action-btn page-next-btn <%= nextAllow ? "allow" : "" %>"><i
                class="ri-arrow-right-s-line"></i></p>
    </div>
   </form>
                

                
<script src="./js/search_result/search_result.js"></script>


        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  

</body>
</html>