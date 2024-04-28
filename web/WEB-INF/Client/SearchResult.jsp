<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Search</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/search_result.css" rel="stylesheet" >
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

        <!-- Search Result Information -->
        <section class="section search-result-section flex-col">
            <div class="search-result-info-div flex-col">

                <h1><span class="result-amount">10,000</span> results for “<span class="search-query">${param.query}</span>”</h1>

                <div class="result-function flex-row">
                    <button class="filter-btn"><i class="ri-filter-3-fill"></i> Filter <span class="filter-count"></span></button>
                    <div class="sort-div">
                        <div class="sort-select flex-row">
                            <select id="sort-type" onchange="applyFilter()">
                                <option value="most-reviewed">Most Reviewed</option>
                                <option value="highest-rated">Highest Rated</option>
                                <option value="relevance" selected>Most Relevant</option>
                                <option value="newest">Newest</option>
                            </select>
                            <div class="sort-select-icon flex-col"><p><i class="ri-arrow-down-s-line"></i></p></div>
                        </div>
                        <label for="sort-type">Sort by</label>
                    </div>
                    <button class="clear-filter-btn"><i class="ri-delete-back-2-fill"></i> Clear filters</button>
                </div>

            </div>

            <div class="search-result-div flex-row">
                <!--filter div-->
                <div class="search-filter-div">                


                        <button class="apply-filter-btn" onclick="applyFilter()">Apply Filter</button>

                        <!--rating filter-->
                        <div class="filter-div flex-col">
                            <div class="filter-type-title flex-row">
                                <h1>Rating</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="rating-filter-div flex-col">
                                <label for="filter-rating-1" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-1" type="radio" value="4.5" />
                                    <span class="filter-icon flex-row">
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-half-fill"></i>
                                    </span>
                                    <span class="filter-desc">4.5 & up</span>
                                    <span class="result-count">(6000)</span>
                                </label>
                                <label for="filter-rating-2" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-2" type="radio" value="4.0" />
                                    <span class="filter-icon flex-row">
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-line"></i>
                                    </span>
                                    <span class="filter-desc">4.0 & up</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>

                        </div>

                    <!--Video Duration-->

                    <!--Course Level-->

                    <!--Price-->

                    <!--course category-->                   

                </div>

                <!--result course-->
                <div class="result-course-div flex-col">



                </div>
            </div>

            <!--pagination, 20 course per page-->
            <div class="result-pagination-div flex-row">
                <p class="page-action-btn page-previous-btn"><i class="ri-arrow-left-s-line"></i></p>
                <p class="first-page-number page-number current-page">1</p>
                <p class="page-number">2</p>
                <p class="page-number">3</p>
                <p>...</p>
                <p class="last-page-number page-number">500</p>
                <p class="page-action-btn page-nect-btn"><i class="ri-arrow-right-s-line"></i></p>
            </div>
        </section>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
