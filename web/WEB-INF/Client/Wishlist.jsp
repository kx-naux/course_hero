<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Wishlist</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/wishlist.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
    </head>
    <body>

        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>
        
        <%
            int numberWish = 2;
        %>
        
        <section class="section wishlist-section">
            <div class="wishlist-div flex-col">
                
                <%
                    if (numberWish > 0) {
                %>
                
                <!--title-->
                <div class="flex-col">
                    <h1 class="page-title">Wishlist</h1>
                </div>
                
                <div class="flex-col"> 
                    <p class="number-item"><%= numberWish %> courses in wishlist</p>
                </div>
                
                <!--course in wishlist-->
                <ul class="course-list flex-col">
                    
                    
                    
                </ul>
                
                <% } else { %>
                
                <!--message show to user when wishlist is empty-->
                <div class="empty-div">
                    
                    
                    
                </div>
                
                <% } %>
                
            </div>
        </section>
        
        <script src="./js/wishlist/wishlist.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %> 
    </body>
</html>
