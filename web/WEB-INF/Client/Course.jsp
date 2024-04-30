<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Course</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/course.css" rel="stylesheet" >
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

        <section class="section course-section">
            <div class="course-div flex-row">

                <div class="course-div-left flex-col">

                    <!--Course General Info-->
                    <div class="course-general-div">

                    </div>

                    <!--What you will learn-->
                    <div class="course-learn-div">

                    </div>

                    <!--Course Include-->
                    <div class="course-include-div"> 

                    </div>

                    <!--Course syllabus-->
                    <div class="course-syllabus-div">

                    </div>

                    <!--Course requirements-->
                    <div class="course-requirement-div">

                    </div>

                    <!--Course description-->
                    <div class="course-desc-div">

                    </div>

                    <!--Course description-->
                    <div class="course-author-div">

                    </div>
                    
                    <!--Course rating-->
                    <div class="course-rating-div">

                    </div>

                </div>

                <div class="course-div-right flex-col">

                    <div class="course-sticky-div flex-col">   
                        <!--Course Preview Video-->
                        <div class="sticky-div-video">
                            <video controls>
                                <source src="./video/course/python_course.mp4" type="video/mp4">
                            </video>
                        </div>

                        <!--Course Price-->
                        <div class="sticky-div-price">
                            <p class="course-price">RM <span>449.90</span></p>                                      
                            <p class="course-normal-price">RM <span>650.00</span></p>
                            <p class="course-offer">50% OFF</p>
                        </div>

                        <!--Course add to cart or wish list-->
                        <div class="sticky-div-cart">
                            <button class="course-cart">Add to cart</button>
                            <button class="course-cart"><i class="ri-heart-line"></i></button>
                        </div>

                        <!--Information-->
                        <div class="sticky-div-info">
                            <p>30-Day Money-Back Guarantee</p>
                            <p>Full Lifetime Access</p>
                        </div>
                    </div>

                </div>

            </div>
        </section>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
