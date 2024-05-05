<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Profile</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/profile.css" rel="stylesheet" >
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

        <!--hidden input field for active the page-->
        <!--1 = Edit profile-->
        <!--2 = Edit address-->
        <!--3 = Change photo-->
        <!--4 = Account security-->
        <!--5 = Close account-->
        <input type="number" value="1" min="1" max="5" hidden />

        <!--hidden input field to show success msg-->
        <input type="text" value="" hidden />

        <section class="section profile-section">
            <div class="profile-div flex-row">

                <div class="profile-div-left flex-col">

                    <div class="profile-user-div flex-col">
                        <img src="./img/user/default.png" alt=""  />
                        <h1 class="profile-username">Kah Xuan</h1>
                    </div>

                    <ul class="profile-sidebar flex-col">
                        <li><a href="<%= webpath.getPageUrl("profile")%>">Profile</a></li>
                        <li><a href="<%= webpath.getPageUrl("profile address")%>">Address</a></li>
                        <li><a href="<%= webpath.getPageUrl("profile photo")%>">Profile Photo</a></li>
                        <li><a href="<%= webpath.getPageUrl("profile security")%>">Account Security</a></li>
                        <li><a href="<%= webpath.getPageUrl("profile close")%>">Close Account</a></li>
                    </ul>

                </div>

                <div class="profile-div-right flex-col">

                    <!--edit profile-->
                    <div class="profile-right-page profile-right-page-1 flex-col">
                        <form>
                            <div class="profile-right-page-header flex-col">
                                <h1>User profile</h1>
                                <p>Edit information about yourself</p>
                            </div>

                            <div class="profile-right-page-content flex-col">
                                <div class="profile-right-page-input flex-col">
                                    <label for="name">Name:</label>
                                    <input type="text" id="name" name="name" value="Kah Xuan" />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="gender">Gender:</label>
                                    <select id="gender" name="gender">
                                        <option value="female">Female</option>
                                        <option value="male" selected>Male</option>
                                    </select>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="dob">Date of birth:</label>
                                    <input type="date" id="dob"  name="dob" placeholder="Select date"/>
                                </div>

                                <p class="invalid-msg"></p>

                                <div class="profile-right-page-submit">
                                    <input type="submit" class="submit-btn" value="Save" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--edit address-->

                    <!--edit photo-->

                    <!--account security-->

                    <!--close account-->

                </div>

            </div>
        </section>


        <script src="./js/profile/profile.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  

    </body>
</html>
