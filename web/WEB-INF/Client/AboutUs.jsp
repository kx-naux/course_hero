<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="module.OfficeData, module.NumberStatisticData" %>
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
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <!-- welcome section -->
        <section class="section welcome-section">
            <div class="welcome-div flex-col">
                
                <div class="welcome-div-content flex-row">
                    <div class="welcome-left flex-row">
                        Welcome to where possible begin
                    </div>
                    <div class="welcome-right flex-row">
                        <img src="" alt="" />
                    </div>
                </div>
                
                <div class="welcome-bot flex-col">
                    <p>Check out our latest company news!</p>
                </div>
                
            </div>
        </section>

        <!-- story section -->
        <section class="section story-section">
            <div class="story-div">

            </div>
        </section>

        <!-- team section -->
        <section class="section team-section" id="team">
            <div class="team-div flex-col">

                <div class="team-div-title flex-col">
                    <h1>Meet Our Team</h1>
                    <p>With a blend of expertise and enthusiasm, we're here to inspire and assist you in reaching your educational goals.</p>
                </div>

                <div class="team-div-content flex-row">
                    
                    <div class="team-card flex-col">
                        <div class="team-img">
                            <img src="./img/about_us/team/yb.png" alt="" />
                        </div>
                        <div class="team-info flex-col">
                            <h1>Woo Yu Beng</h1>
                            <p>Developer</p>
                        </div>
                    </div>
                                        
                     <div class="team-card flex-col">
                        <div class="team-img">
                            <img src="./img/about_us/team/wy.png" alt="" />
                        </div>
                        <div class="team-info flex-col">
                            <h1>Tan Woeiyi</h1>
                            <p>Project Manager</p>
                        </div>
                    </div>
                    
                    <div class="team-card flex-col">
                        <div class="team-img">
                            <img src="./img/about_us/team/sw.png" alt="" />
                        </div>
                        <div class="team-info flex-col">
                            <h1>Snijders Wang</h1>
                            <p>Developer</p>
                        </div>
                    </div>
                    
                    <div class="team-card flex-col">
                        <div class="team-img">
                            <img src="./img/about_us/team/kx.png" alt="" />
                        </div>
                        <div class="team-info flex-col">
                            <h1>Low Kah Xuan</h1>
                            <p>Developer</p>
                        </div>
                    </div>
                    
                    <div class="team-card flex-col">
                        <div class="team-img">
                            <img src="./img/about_us/team/zl.png" alt="" />
                        </div>
                        <div class="team-info flex-col">
                            <h1>Lau Zhan Liang</h1>
                            <p>Developer</p>
                        </div>
                    </div>
                    
                   

                </div>

            </div>
        </section>

        <!-- number section -->
        <section class="section number-section">
            <div class="number-div flex-col">

                <div class="number-div-title flex-col">
                    <h1>Creating impact around the world</h1>
                    <p>With our global catalog spanning the latest skills and topics, people and organization, everywhere are able to adapt to change and thrive.</p>
                </div>

                <div class="number-div-content flex-row">

                    <%
                        List<Map<String, String>> statistic = NumberStatisticData.getStatistic();
                        for (Map<String, String> s : statistic) {
                    %>
                    <div class="number-col flex-col">
                        <h1><%= s.get("number")%></h1>
                        <p><%= s.get("title")%></p>
                    </div>
                    <% } %>

                </div>

            </div>
        </section>

        <!-- reason section -->
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

        <!-- office section -->
        <section class="section office-section" id="office">
            <div class="office-div flex-col">

                <div>
                    <h1 class="office-div-title">Our Office</h1>
                    <p class="office-div-sub-title">Where Creativity Meets Functionality</p>
                </div>

                <%
                    Map<String, String> hq = OfficeData.getHeadquartersOffice();
                %>

                <!--iframe map-->
                <iframe class="office-emb-map" src="<%= hq.get("map")%>" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>

                <!--address-->
                <p class="office-address"><%= hq.get("address")%></p>

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

        <!-- contact section -->
        <section class="section contact-section" id="contact">
            <div class="contact-div flex-col">

                <div class="contact-div-title flex-row">
                    <h1>Contact Us</h1>
                </div>

                <div class="contact-div-content flex-row">

                    <div class="contact-left flex-col">

                        <!--phone number-->
                        <div class="contact-info flex-col">
                            <h1>Call Us <i class="ri-phone-fill"></i></h1>
                            <p>(+60) 11-1760 8595</p>
                        </div>

                        <!--email-->
                        <div class="contact-info flex-col">
                            <h1>Send Email <i class="ri-mail-fill"></i></h1>
                            <p>hello@coursehero.com</p>
                        </div>

                        <!--social media icon-->
                        <div class="contact-social-media flex-row">
                            <a class="facebook" href="#"><i class="ri-facebook-fill"></i></a>
                            <a class="linkedin" href="#"><i class="ri-linkedin-fill"></i></a>
                            <a class="youtube" href="#"><i class="ri-youtube-fill"></i></a>
                            <a class="instagram" href="#"><i class="ri-instagram-fill"></i></a>
                            <a class="tiktok" href="#"><i class="ri-tiktok-fill"></i></a>
                        </div>

                    </div>
                    <div class="contact-right flex-col">
                        <img src="./img/about_us/mail-box.png" alt="" />
                    </div>

                </div>

            </div>
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
