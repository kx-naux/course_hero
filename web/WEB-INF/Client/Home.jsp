<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Home</title>
        <link rel="icon" type="image/png" href="./img/Logo.png">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav>
            <div class="nav-logo-div">

            </div>
            <div class="nav-option-div">

            </div>
            <div class="nav-function-div">
                <c:choose>
                    <c:when test="${not empty sessionScope.username}">
                        <!-- Logged in -->
                        <p>Welcome, ${sessionScope.username}!</p>
                    </c:when>
                    <c:otherwise>
                        <!-- Not Logged in -->
                        <input class="" type="button" value="Log In" />
                        <input type="button" value="Sign Up"  />
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>

        <!-- Image Carousel -->
        <section class="image-carousel-section">

        </section>

        <!-- Hottest Product -->
        

        <!-- Unit Collaboration -->
        <section class="unit-collaboration-section">

        </section>

        <!-- Information section -->
        <section class="home-information-section">

        </section>

        <!-- Feedback from customer -->
        <section class="feedback-section">

        </section>

        <!-- Category section -->
        <section class="category-section">

        </section>

        <!-- FAQ section -->
        <section class="faq-section">

        </section>

        <!-- Footer section -->
        <section class="footer-section">

        </section>
    </body>
</html>
