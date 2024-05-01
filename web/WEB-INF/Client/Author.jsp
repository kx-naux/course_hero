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
                        <h3 class="authoir-position">Developer and Lead Instructor</h3>
                    </div>

                    <div class="author-about flex-col">
                        <h1 class="about-title">About me</h1>
                        <P class="about-content">
                            I'm Angela, I'm a developer with a passion for teaching. I'm the lead instructor at the London App Brewery, London's leading Programming Bootcamp. I've helped hundreds of thousands of students learn to code and change their lives by becoming a developer. I've been invited by companies such as Twitter, Facebook and Google to teach their employees.
                            My first foray into programming was when I was just 12 years old, wanting to build my own Space Invader game. Since then, I've made hundred of websites, apps and games. But most importantly, I realised that my greatest passion is teaching.
                        </P>
                    </div>

                    <div class="author-courses">
                        <h1>My courses (15)</h1>
                    </div>

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
