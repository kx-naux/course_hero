<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="module.OfficeData" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | About Us</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/about_us.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <script src="./js/nav.js"></script>   
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!--To top button-->
        <button onclick="toTopFunc()" id="toTopBtn" title="Go to top"><i class="ri-arrow-up-s-line"></i></button>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <!-- welcome section -->
        <section class="section welcome-section">
            <div class="welcome-div">

            </div>
        </section>

        <!-- story section -->
        <section class="section story-section">
            <div class="story-div">

            </div>
        </section>

        <!-- team section -->
        <section class="section team-section">
            <div class="team-div">

            </div>
        </section>

        <!-- number section -->
        <section class="section number-section">
            <div class="number-div">

            </div>
        </section>

        <!-- reason1 section -->
        <section class="section reason-section">
            <div class="reason-div">
                <!--title-->
                <div class="reason-title">
                    <h1>Skills are the key to unlocking potential</h1>
                    <p>Whether you want to learn a new skill, train your teams, or share what you know with the world, you're in the right place, as a leader in online learning, we're here to help you achieve your goals and transform your life.</p>
                </div>

                <!-- video-->
                <div>

                </div>

                <!--Bento UI-->
                <div>

                </div>
            </div>
        </section>

        <!-- contact section -->
        <section class="section contact-section">
            <div class="contact-div">

            </div>
        </section>

        <!-- office section -->
        <section class="section office-section">
            <div class="office-div flex-col">

                <div>
                    <h1 class="office-div-title">Our Office</h1>
                    <p class="office-div-sub-title">Where Creativity Meets Functionality</p>
                </div>

                <% 
                    Map<String, String> hq = OfficeData.getHeadquartersOffice();
                %>
                
                <!--iframe map-->
                <iframe class="office-emb-map" src="<%= hq.get("map") %>" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>

                <!--address-->
                <p class="office-address"><%= hq.get("address") %></p>

                <!--offices-->
                <div class="offices flex-row">
                    <%
                        List<Map<String, String>> offices = OfficeData.getOffices();
                        String selected = "";
                        String image = "";
                        for (Map<String, String> office : offices) {
                            image = office.get("image");

                            if (office.get("city").equals("Malaysia - Kuala Lumpur")) {
                                selected = "selected";
                                image = image.replace(".png", "_slc.png");
                            } else {
                                selected = "";
                            }
                    %>
                    <div class="office flex-col <%= selected%>" onclick="changeOffice(this)">
                        <span hidden class="office-map"><%= office.get("map")%></span>
                        <span hidden class="office-address"><%= office.get("address")%></span>
                        <div class="office-img-div">
                            <img src="<%= image%>" alt="" />
                        </div>
                        <p><%= office.get("city")%></p>
                    </div>
                    <% }%>

                </div>

            </div>

            <!--import office js-->
            <script src="./js/about_us/office.js"></script>
        </section>

        <!-- link section -->
        <section class="section link-section">
            <div class="link-div flex-row">

                <div class="link-col flex-col">
                    <hr />
                    <h1>Work with us</h1>
                    <p>At Course Hero, we’re all learners and instructors. We live out our values every day to create a culture that is diverse, inclusive, and committed to helping employees thrive.</p>
                    <a href="#">Join our team<i class="ri-arrow-right-s-line"></i></a>
                </div>

                <div class="link-col flex-col">
                    <hr />
                    <h1>See our research</h1>
                    <p>We’re committed to improving lives through learning. Dig into our original research to learn about the forces that are shaping the modern workplace.</p>
                    <a href="#">Learn more<i class="ri-arrow-right-s-line"></i></a>
                </div>

                <div class="link-col flex-col">
                    <hr />
                    <h1>Read our blog</h1>
                    <p>Want to know what we’ve been up to lately? Check out the Course Hero blog to get the scoop on the latest news, ideas and projects, and more.</p>
                    <a href="#">Read now<i class="ri-arrow-right-s-line"></i></a>
                </div>

            </div>
        </section>

        <!--Footer import-->
        <%@ include file="./Components/footer_wave.html" %>  
        <%@ include file="./Components/footer.jsp" %> 
    </body>
</html>
