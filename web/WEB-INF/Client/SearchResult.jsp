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
                    <button class="filter-btn"><i class="ri-filter-3-fill"></i> Filter</button>
                    <div class="sort-div">
                        <div class="sort-select flex-row">
                            <select id="sort-type">
                                <option value="most-reviewed">Most Reviewed</option>
                                <option value="highest-rated">Highest Rated</option>
                                <option value="relevance">Most Relevant</option>
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

            </div>
        </section>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
