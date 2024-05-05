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
        <input type="number" id="profilePage" value="2" min="1" max="5" hidden />

        <!--hidden input field to show success msg-->
        <input type="text" id="succssMsg" value="" hidden />

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
                        <form class="flex-col" id="editProfileForm">
                            <div class="profile-right-page-header flex-col">
                                <h1>User profile</h1>
                                <p>Edit information about yourself</p>
                            </div>

                            <div class="profile-right-page-content flex-col">
                                <div class="profile-right-page-input flex-col">
                                    <label for="name">Name:</label>
                                    <input type="text" id="name" name="name" value="Kah Xuan"  placeholder="name" maxlength="30"  />
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
                                    <input type="date" id="dob"  name="dob" value="2021-01-04" />
                                </div>

                                <p class="invalid-msg"></p>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="button" class="submit-btn form-1-submit" value="Save" onclick="form1_submit()" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--edit address-->
                    <div class="profile-right-page profile-right-page-2 flex-col">
                        <form class="flex-col" id="editProfileForm">
                            <div class="profile-right-page-header flex-col">
                                <h1>Address</h1>
                                <p>Edit information about your address</p>
                            </div>

                            <div class="profile-right-page-content flex-col">
                                <div class="profile-right-page-input flex-col">
                                    <label>Address:</label>
                                    <input type="text" id="address1" name="address1" value="" placeholder="address line 1" maxlength="50" />
                                    <input type="text" id="address2" name="address2" value="" placeholder="address line 2" maxlength="50" />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="city">City:</label>
                                    <input type="text" id="city" name="city" value="" placeholder="city name" maxlength="20" />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="postalCode">Postal code:</label>
                                    <input type="text" id="postalCode"  name="postalCode" placeholder="postal code" maxlength="9" value=""/>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="state">State Resides:</label>
                                    <input type="text" id="state"  name="state" placeholder="state name" maxlength="20" value=""/>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="state">Country:</label>
                                    <input type="text" id="country"  name="country" placeholder="country name" maxlength="40" value=""/>
                                </div>

                                <p class="invalid-msg"></p>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="submit" class="submit-btn" value="Save" />
                                </div>
                            </div>
                        </form>
                    </div>

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
