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
                    
                </div>            
                
                <div class="author-right flex-col">
                    
                    <div class="author-img">
                        <img src="./img/author/angelayu.jpg" alt=""/>
                    </div>
                    
                    <div class="author-link flex-col">
                        <a href="#" class="author-link-btn"><i class="ri-link"></i> Website</a>
                        <a href="#" class="author-link-btn"><i class="ri-linkedin-box-fill"></i> LinkedIn</a>
                        <a href="#" class="author-link-btn"><i class="ri-instagram-fill"></i> Instagram</a>
                    </div>
                    
                </div>

            </div>
        </section>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
