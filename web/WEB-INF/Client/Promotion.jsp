<%@page import="JPAEntity.Promotions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Promotion</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet">
        <link type="text/css" href="./css/promotion.css" rel="stylesheet">
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
        <jsp:useBean id="webpath" class="module.WebPath" scope="application"/>
        <jsp:useBean id="promotions" class="List<JPAEntity.Promotions>" scope="request" />
        <%long numOfPromotionsRec = ((Long) request.getAttribute("numberOfPromotions")).longValue();%>
        <style>
            .promo-div {
                gap: 20px;
                padding: 60px 0 30px;
                min-height: 100vh;
            }

            h1{
                margin-left:25px;
                font-weight: 600;
                font-size: var(--font-size-xl);
                color: var(--color-navy-blue);
            }

            table{
                margin: 0 auto;
                border-collapse: separate;
                border-spacing: 5px;
                cursor: default;
            }

            table,th,td{
                border:1px solid black;
                padding:8px;
                border-radius: 10px;
            }

            th,td{
                text-align:center;
                font-size: var(--font-size-s);
            }

            th {
                background-color:var(--color-blue-2);
                font-weight: 600;
                font-size: var(--font-size-xm);
                color: var(--color-white);
            }

            tbody tr:nth-child(even) {
                background-color: var(--color-light-grey-2);
            }

            tr:has(td:hover) td {
                background-color: var(--color-sky-blue);
            }

        </style>
    </head>

    <body>
        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>


        <section class="section promo-section">
            <div class="promo-div flex-col">

                <h1>Promotions and Discounts</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Promo Code</th>
                            <th>Progress</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody id="promotionTableBody">
                        <%
                            for (Promotions eachPromotion : promotions) {
                            
                        %>
                        <tr>
                            <td><%= eachPromotion.getPromotionName()%></td>
                            <td><%= eachPromotion.getPromoDescription()%></td>
                            <td><%= eachPromotion.getFormattedDate(eachPromotion.getStartTime())%></td>
                            <td><%= eachPromotion.getFormattedDate(eachPromotion.getEndTime())%></td>
                            <td><%= eachPromotion.getPromoCode()%></td>
                            <td><%= eachPromotion.calculateDuration(eachPromotion.getStartTime(), eachPromotion.getEndTime())%></td>
                            <td><%= eachPromotion.getStatus()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>

                <!--pagination, 4 promotion per page-->

                <form id="promotionForm">
                    <%
                        long currentPage = 1;
                        long dataPerPage = 4;
                        long lastPage = ((numOfPromotionsRec - 1) / dataPerPage) + 1;

                        if (request.getParameter (
                                 
                            "p") != null) {
                            currentPage = Integer.parseInt(request.getParameter("p"));
                        }

                        boolean previousAllow = currentPage > 1;
                        boolean nextAllow = currentPage < lastPage;
                    %>
                    <input type="text" id="id" name="id" value="${param.id}" hidden />
                    <input type="number" id="current_page" name="p" value="<%= currentPage%>" hidden />
                    <div class="result-pagination-div flex-row">
                        <p class="page-action-btn page-previous-btn <%= previousAllow ? "allow" : ""%>"><i class="ri-arrow-left-s-line"></i></p>
                            <% if (currentPage

                                
                                
                                - 1 != 0) { %>
                        <p class="first-page-number page-number">1</p>
                        <% }
                            if (currentPage - 1 != 1 && currentPage - 1 != 2 && currentPage

                            
                            
                            - 1 > 0) { %>
                        <p class="number-dot">...</p>
                        <% }
                            if (currentPage

                            
                                - 1 > 1) {%>
                        <p class="first-page-number page-number"><%= currentPage - 1%></p>
                        <% }%>
                        <p class="first-page-number page-number current-page"><%= currentPage%></p>
                        <% if (currentPage

                            
                                + 1 < lastPage) {%>
                        <p class="first-page-number page-number"><%= currentPage + 1%></p>
                        <%
                            }
                            if (currentPage + 1 != lastPage && currentPage + 2 != lastPage && currentPage

                            
                            
                            + 1 < lastPage) {
                        %>
                        <p class="number-dot">...</p>
                        <% }
                            if (lastPage > 1 && currentPage != lastPage

                            
                                ) {%>
                        <p class="last-page-number page-number"><%= lastPage%></p>
                        <% }%>
                        <p class="page-action-btn page-next-btn <%= nextAllow ? "allow" : ""%>"><i class="ri-arrow-right-s-line"></i></p>
                    </div>
                </form>


            </div>
        </section>

        <script src="./js/promotion/promotion.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  

    </body>
</html>