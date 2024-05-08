<%@page import="java.util.Base64"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<% String successMsg = (String) request.getAttribute("successMsg"); %>
<% SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = simpleDateFormat.format(userData.getDob());%>
<% String pageNumber = (String) request.getAttribute("profilePageNumber");
    if (pageNumber == null) {
        pageNumber = "1";
    }%>
<% String errMsg = (String) request.getAttribute("errMsg"); %>
<% String emailErrMsg = (String) request.getAttribute("emailErrMsg"); %>
<% String deleteAccErrMsg = (String) request.getAttribute("deleteAccErrMsg"); %>
<% String passErrMsg = (String) request.getAttribute("passErrMsg"); %>
<% String emailInput = (String) session.getAttribute("newEmailToChangeBuffer"); %>
<% String otpForWhichForm = (String) request.getAttribute("otpForWhichForm");
    if (otpForWhichForm == null) {
        otpForWhichForm = "";
    }%>
<% String otpErrMsg = (String) request.getAttribute("otpErrMsg"); %>
<% String showOTPForm = (String) request.getAttribute("showOTPForm");
    if (showOTPForm == null) {
        showOTPForm = "0";
    }%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Profile</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/profile.css" rel="stylesheet" >
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
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
        <input type="number" id="profilePage" value="<%= pageNumber%>" min="1" max="5" hidden />

        <!--otp for page 4 put 1 into value-->
        <input type="number" id="otpDiv" value="<%= showOTPForm%>" hidden />

        <!--if otp error put 1-->
        <input type="number" id="otpError" value="0"  hidden />

        <!--hidden input field to show success msg-->
        <% if (successMsg == null) { %>
        <input type="text" id="succssMsg" value="" hidden />
        <%} else {%>
        <input type="text" id="succssMsg" value="<%= successMsg%>" hidden />
        <%}%>


        <section class="section profile-section">
            <div class="profile-div flex-row">

                <div class="profile-div-left flex-col">

                    <div class="profile-user-div flex-col">
                        <%String base64ImageData = "";
                            if (userData.getImgData() != null) {
                                base64ImageData = Base64.getEncoder().encodeToString((byte[]) userData.getImgData());
                            }%>
                        <img src="data:image/jpeg;base64,<%= base64ImageData%>" onerror="this.src='./img/user/default.png';" alt=""  />
                        <h1 class="profile-username">${userData.accountId.username}</h1>
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
                        <form class="flex-col" id="editProfileForm" action="UpUserProfileBasic" method="post">
                            <div class="profile-right-page-header flex-col">
                                <h1>User profile</h1>
                                <p>Edit information about yourself</p>
                            </div>

                            <!--input form-->
                            <div class="profile-right-page-content flex-col profile-edit-div">
                                <div class="profile-right-page-input flex-col">
                                    <label for="name">Name:</label>
                                    <input type="text" id="name" name="name" value="${userData.displayName}"  placeholder="name" maxlength="30"  />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="gender">Gender:</label>
                                    <select id="gender" name="gender">
                                        <option value="female" <%= (userData.getGender() != null && userData.getGender().equalsIgnoreCase("female")) ? "selected" : ""%> >Female</option>
                                        <option value="male" <%= (userData.getGender() != null && userData.getGender().equalsIgnoreCase("male")) ? "selected" : ""%>>Male</option>
                                    </select>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="dob">Date of birth:</label>


                                    <input type="date" id="dob"  name="dob" value="<%= formattedDate%>" />
                                </div>


                                <p class="invalid-msg"></p>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="button" class="submit-btn form-1-submit" value="Save" onclick="form1_confirm()" />
                                </div>
                            </div>

                            <!--confirmation-->
                            <div class="profile-right-page-content flex-col confirmation-div">
                                <p>Please review the details below before saving your changes:</p>

                                <div class="profile-right-page-input flex-col">
                                    <label for="name">Name:</label>
                                    <input type="text" id="nameC" name="nameC" value="" readonly  />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="gender">Gender:</label>
                                    <input type="text" id="genderC" name="genderC" value="" readonly  />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="dob">Date of birth:</label>
                                    <input type="text" id="dobC"  name="dobC" value="" readonly />
                                </div>

                                <p>Are you sure you want to save these changes?</p>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="button" class="submit-btn form-1-submit" value="Confirm" onclick="form1_submit()" />
                                    <input type="button" class="submit-btn form-1-submit back-btn" value="Back" onclick="form1_back()" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--edit address-->
                    <div class="profile-right-page profile-right-page-2 flex-col">
                        <form class="flex-col" id="editAddressForm" method="post" action="edit-user-address">
                            <div class="profile-right-page-header flex-col">
                                <h1>Address</h1>
                                <p>Edit information about your address</p>
                            </div>

                            <!--input form-->
                            <div class="profile-right-page-content flex-col profile-edit-div">
                                <div class="profile-right-page-input flex-col">
                                    <label>Address:</label>
                                    <input type="text" id="address1" name="address1" value="${userData.addressId.line1}" placeholder="address line 1" maxlength="50" />
                                    <input type="text" id="address2" name="address2" value="${userData.addressId.line2}" placeholder="address line 2" maxlength="50" />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="city">City:</label>
                                    <input type="text" id="city" name="city" value="${userData.addressId.city}" placeholder="city name" maxlength="20" />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="postalCode">Postal code:</label>
                                    <input type="text" id="postalCode"  name="postalCode" placeholder="postal code" maxlength="9" value="${userData.addressId.postalcode}"/>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="state">State Resides:</label>
                                    <input type="text" id="state"  name="state" placeholder="state name" maxlength="20" value="${userData.addressId.stateResides}"/>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="state">Country:</label>
                                    <input type="text" id="country"  name="country" placeholder="country name" maxlength="40" value="${userData.addressId.country}"/>
                                </div>

                                <p class="invalid-msg"></p>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="button" class="submit-btn" value="Save" onclick="form2_confirm()" />
                                </div>
                            </div>

                            <!--confirmation div-->
                            <div class="profile-right-page-content flex-col confirmation-div">
                                 <p>Please review the details below before saving your changes:</p>
                                
                                <div class="profile-right-page-input flex-col">
                                    <label>Address:</label>
                                    <input type="text" id="address1C" name="address1C" value="" readonly />
                                    <input type="text" id="address2C" name="address2C" value="" readonly />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="city">City:</label>
                                    <input type="text" id="cityC" name="cityC" value="" readonly />
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="postalCode">Postal code:</label>
                                    <input type="text" id="postalCodeC"  name="postalCodeC" value="" readonly/>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="state">State Resides:</label>
                                    <input type="text" id="stateC"  name="stateC"  value="" readonly/>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label for="state">Country:</label>
                                    <input type="text" id="countryC"  name="countryC" value="" readonly/>
                                </div>
                                
                                <p>Are you sure you want to save these changes?</p>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="button" class="submit-btn" value="Confirm" onclick="form2_submit()" />
                                    <input type="button" class="submit-btn form-1-submit back-btn" value="Back" onclick="form2_back()" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--edit photo-->
                    <div class="profile-right-page profile-right-page-3 flex-col">
                        <form class="flex-col" id="editPhotoForm" action="update-user-pfp" method="POST" enctype="multipart/form-data">
                            <div class="profile-right-page-header flex-col">
                                <h1>Profile photo</h1>
                                <p>Add a nice photo of yourself for your profile</p>
                            </div>

                            <div class="profile-right-page-content flex-col">
                                <div class="profile-right-page-input flex-col">
                                    <label>Image Preview:</label>
                                    <div class="image-preview-div flex-col">
                                        <img id="profilePreview" src="data:image/jpeg;base64,<%= base64ImageData%>" alt=""  onerror="this.src='./img/user/default.png';"/>


                                    </div>
                                </div>

                                <div class="profile-right-page-input flex-col">
                                    <label>Add / Change Image:</label>
                                    <input type="file" id="profilePic" name="profilePic" accept="image/*" hidden />
                                    <label for="profilePic" class="image-input-label flex-row">
                                        <span class="file-value">No file chosen</span>
                                        <span class="upload-btn">Upload image</span>
                                    </label>
                                </div>

                                <% if (errMsg == null) { %>
                                <p class="invalid-msg"></p>
                                <%} else {%>
                                <p class="invalid-msg"><%= errMsg%></p>
                                <%}%>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="submit" class="submit-btn form-3-submit" value="Save" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--account security-->
                    <div class="profile-right-page profile-right-page-4 flex-col">

                        <div class="profile-right-page-header flex-col">
                            <h1>Security</h1>
                            <p>Edit your account settings and change your password here</p>
                        </div>

                        <!--change email form-->
                        <form class="flex-col" id="changeEmailForm" method="post" action="edit-security">
                            <div class="profile-right-page-content flex-col">

                                <div class="profile-right-page-input flex-col">
                                    <label for="email">New email:</label>
                                    <!--put invalid-input if error-->
                                    <% if (emailInput == null) { %>
                                    <input type="text" id="newEmail" class=""  name="email" placeholder="example@email.com" maxlength="50" value="${userData.accountId.email}"/>
                                    <%} else {%>
                                    <input type="text" id="newEmail" class=""  name="email" placeholder="example@email.com" maxlength="50" value="<%= emailInput%>"/>
                                    <%}%>
                                </div>

                                <% if (emailErrMsg == null) { %>
                                <p class="invalid-msg" id="changeEmail"></p>
                                <%} else {%>
                                <p class="invalid-msg" id="changeEmail"><%= emailErrMsg%></p>
                                <%}%>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="text" id="formTypeChangeEmail" name="formType" value="ChangeEmail" hidden />
                                    <input type="button" id="changeEmailBtn" class="submit-btn form-4-submit" value="Change email" />
                                </div>
                            </div>
                        </form>

                        <!--change password form-->
                        <form class="flex-col profile-right-border-top" id="changePassowrdForm" method="post" action="edit-security">
                            <div class="profile-right-page-content flex-col">

                                <!--password-->
                                <div class="profile-right-page-input flex-col">
                                    <label for="password">New Password:</label>
                                    <input type="password" id="passwordChange"  name="password" class="password-input" placeholder="Password" />
                                    <span class="password-eye"><i class="ri-eye-line"></i></span>
                                </div>

                                <!--confirm password-->
                                <div class="profile-right-page-input flex-col">
                                    <label for="password2">Confirm Password:</label>
                                    <input type="password" id="passwordChange2"  name="password2" class="password-input" placeholder="Confirm password" />
                                    <span class="password-eye"><i class="ri-eye-line"></i></span>
                                </div>

                                <ul class="input-requirement">
                                    <li><i class="ri-close-circle-line"></i> Include at least 1 uppercase letter</li>
                                    <li><i class="ri-close-circle-line"></i> Include at least 1 lowercase letter</li>
                                    <li><i class="ri-close-circle-line"></i> Include at least 1 digit</li>
                                    <li><i class="ri-close-circle-line"></i> Include at least 1 symbol</li>
                                    <li><i class="ri-close-circle-line"></i> Have a minimum length of 8</li>
                                </ul>

                                <% if (passErrMsg == null) { %>
                                <p class="invalid-msg" id="changePassword"></p>
                                <%} else {%>
                                <p class="invalid-msg" id="changePassword"><%= passErrMsg%></p>
                                <%}%>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="text" id="formTypeChangePassword" name="formType" value="ChangePassword" hidden />
                                    <input type="button" id="changePasswordBtn"  class="submit-btn form-4-submit" value="Change Password" />
                                </div>
                            </div>
                        </form>

                        <!--otp form-->
                        <form class="flex-col" id="otpForm" style="display: none;" method="post" action="edit-security">
                            <div class="profile-right-page-content flex-col">
                                <div class="sign-up-view-div  flex-col">
                                    <h1 class="confirmation-title">OTP Verification</h1>
                                    <p class="confirmation-sub-title">Code is sent to example@email.com, input the code received, and verify to complete the process.</p>
                                </div>

                                <div class="otp-field-div flex-row">
                                    <input type="text" id="otp1" name="otp1" class="otp" maxlength="1" autocomplete="off" />
                                    <input type="text" id="otp2" name="otp2" class="otp" maxlength="1" autocomplete="off" />
                                    <input type="text" id="otp3" name="otp3" class="otp" maxlength="1" autocomplete="off" />
                                    <input type="text" id="otp4" name="otp4" class="otp" maxlength="1" autocomplete="off" />
                                    <input type="text" id="otp5" name="otp5" class="otp" maxlength="1" autocomplete="off" />
                                    <input type="text" id="otp6" name="otp6" class="otp" maxlength="1" autocomplete="off" />
                                </div>

                                <input type="text" id="otp" name="otp" hidden />

                                <% if (otpErrMsg == null) { %>
                                <p class="invalid-msg" id="otpVerify"></p>
                                <%} else {%>
                                <p class="invalid-msg" id="otpVerify"><%= otpErrMsg%></p>
                                <%}%>


                                <div class="profile-right-page-submit flex-row">
                                    <input type="text" id="formTypeOTP" name="formType" value="OTPSubmission" hidden />
                                    <input type="text" id="otpForWhichForm" name="otpForWhichForm" value="<%= otpForWhichForm%>" hidden />
                                    <input type="button" id="otpSubmitBtn" class="submit-btn form-4-submit" value="Submit" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--close account-->
                    <div class="profile-right-page profile-right-page-5 flex-col" >
                        <form class="flex-col" id="closeAccountForm" method="post" action="user-delete-account">
                            <div class="profile-right-page-header flex-col">
                                <h1>Close Account</h1>
                                <p>Close your account permanently</p>
                            </div>

                            <div class="profile-right-page-content flex-col">
                                <p class="close-warning"><span><i class="ri-alert-line"></i> Warning:</span> If you close your account, you will will lose access forever.</p>

                                <div class="profile-right-page-input flex-col">
                                    <label for="password">Password:</label>
                                    <!--add invalid-input to input class if error-->
                                    <input type="password" id="passwordCloseAcc"  name="password" class="password-input" placeholder="Enter password to proceed to close account" />
                                    <span class="password-eye"><i class="ri-eye-line"></i></span>
                                </div>
                                <% if (deleteAccErrMsg == null) { %>
                                <p class="invalid-msg"></p>
                                <%} else {%>
                                <p class="invalid-msg"><%= deleteAccErrMsg%></p>
                                <%}%>

                                <div class="profile-right-page-submit flex-row">
                                    <input type="button" class="submit-btn form-5-submit" value="Close account" />
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
        </section>


        <script src="./js/profile/profile.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  

    </body>
</html>
