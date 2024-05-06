<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<% String pageNumber = (String) request.getAttribute("pageNumber"); %>
<% String errField = (String) request.getAttribute("errField"); %>
<% String errMsg = (String) request.getAttribute("errMsg"); %>
<jsp:useBean id="loginFormData" class="entity.LoginFormData" scope="request"/>
<% if (pageNumber == null) {
        pageNumber = "1";
    }
    if (errField == null) {
        errField = "";
    }
    if (errMsg == null) {
        errMsg = "";
    }%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Sign Up</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/sign_up.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>

        <section class="sign-up-section flex-col">
            <div class="sign-up-div flex-col">

                <!--position absolute-->
                <div class="logo-div">
                    <a href="<%= webpath.getPageUrl("home")%>"><img src="./img/Logo.png" alt="Course Hero" /></a>
                </div>

                <!--sign up title-->
                <div class="sign-up-title-div">
                    <h1>Sign Up</h1>
                </div>

                <!--hidden value for step count-->
                <input type="number" min="1" max="6" id="formProgress" value="<%= pageNumber%>" hidden />

                <!--insert error message here if any-->
                <% if (errField.equals("email&username")) { %>
                <input type="text'  id="errorInput" value="email" hidden />
                <input type="text" id="errorInput" value="username" hidden />
                <%} else {%>
                <input type="text"  id="errorInput" value="<%= errField%>" hidden />
                <%}%>
                <% if (errMsg.isEmpty()) { %>
                <span hidden id="errorMsg"></span>
                <%} else {%>
                <span hidden id="errorMsg"><%= errMsg%></span>
                <%}%>


                <!--step progress bar-->
                <div class="form-progress-bar flex-row">
                    <div class="progress-point flex-col">
                        <span class="point-number">1</span>
                        <p class="point-name">User Info</p>
                    </div>
                    <hr class="progress-line" />
                    <div class="progress-point flex-col">
                        <span class="point-number">2</span>
                        <p class="point-name">Address</p>
                    </div>
                    <hr class="progress-line" />
                    <div class="progress-point flex-col">
                        <span class="point-number">3</span>
                        <p class="point-name">Password</p>
                    </div>
                    <hr class="progress-line" />
                    <div class="progress-point flex-col">
                        <span class="point-number">4</span>
                        <p class="point-name">Confirmation</p>
                    </div>
                    <hr class="progress-line" />
                    <div class="progress-point flex-col">
                        <span class="point-number">5</span>
                        <p class="point-name">Verification</p>
                    </div>
                </div>

                <form action="sign-up" method="post" id="signUpForm">
                    <!--1st page or sign up form-->
                    <div class="sign-up-p sign-up-p1 flex-col">
                        <!--email-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="email">Email:</label>
                            <input type="text" id="email"  name="email" placeholder="example@email.com" maxlength="50" value="${loginFormData.email}"/>
                        </div>

                        <!--username-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="username">Username:</label>
                            <input type="text" id="username"  name="username" placeholder="only characters, digits, and the special symbols (!, #, _, .)" maxlength="25" value="${loginFormData.username}"/>
                        </div>

                        <!--display name-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="name">Display Name:</label>
                            <input type="text" id="name"  name="name" placeholder="name" maxlength="30" value="${loginFormData.displayName}"/>
                        </div>

                        <!--gender-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="gender">Gender:</label>
                            <select id="gender" name="gender" value="${loginFormData.gender}">
                                <option hidden value="" >Select gender</option>
                                <option value="female" <%= (loginFormData.getGender() != null && loginFormData.getGender().equalsIgnoreCase("female")) ? "selected" : ""%> >Female</option>
                                <option value="male" <%= (loginFormData.getGender() != null && loginFormData.getGender().equalsIgnoreCase("male")) ? "selected" : ""%>>Male</option>
                            </select>
                        </div>

                        <!--date of birth-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="dob">Date of birth:</label>
                            <input type="date" id="dob"  name="dob" placeholder="Select date" value="${loginFormData.dobStr}"/>
                        </div>

                        <p class="invalid-msg"></p>

                        <div class="sign-up-btn-div flex-col">
                            <input class="sign-up-btn next-btn" type="button" value="Next" />
                            <P class="sign-in-link">Already have account? <a href="<%= webpath.getPageUrl("login")%>">Sign in now</a></p>
                        </div>
                    </div>

                    <!--2nd page or sign up form-->
                    <div class="sign-up-p sign-up-p2 flex-col">
                        <!--address -->
                        <div class="sign-up-input-div flex-col">
                            <label for="address1">Address:</label>
                            <input type="text" id="address1"  name="address1" placeholder="address line 1" maxlength="50" value="${loginFormData.addressLine1}"/>
                            <input type="text" id="address2"  name="address2" placeholder="address line 2" maxlength="50" value="${loginFormData.addressLine2}"/>
                        </div>

                        <!--City-->
                        <div class="sign-up-input-div flex-col">
                            <label for="city">City:</label>
                            <input type="text" id="city"  name="city" placeholder="city name" maxlength="20" value="${loginFormData.city}"/>
                        </div>

                        <!--Postal Code-->
                        <div class="sign-up-input-div flex-col">
                            <label for="postalCode">Postal code:</label>
                            <input type="text" id="postalCode"  name="postalCode" placeholder="postal code" maxlength="9" value="${loginFormData.postalCode}"/>
                        </div>

                        <!--State-->
                        <div class="sign-up-input-div flex-col">
                            <label for="state">State Resides:</label>
                            <input type="text" id="state"  name="state" placeholder="state name" maxlength="20" value="${loginFormData.state}"/>
                        </div>

                        <!--Country-->
                        <div class="sign-up-input-div flex-col">
                            <label for="state">Country:</label>
                            <input type="text" id="country"  name="country" placeholder="country name" maxlength="40" value="${loginFormData.country}"/>
                        </div>

                        <p class="invalid-msg"></p>

                        <div class="sign-up-btn-div flex-col">
                            <input class="sign-up-btn next-btn" type="button" value="Next" />
                            <input class="sign-up-btn back-btn" type="button" value="Back" />
                        </div>
                    </div>

                    <!--3rd page or sign up form-->
                    <div class="sign-up-p sign-up-p3 flex-col">
                        <!--password-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="password">Password:</label>
                            <input type="password" id="password"  name="password" class="password-input" placeholder="Password" value="${loginFormData.password}"/>
                            <span class="password-eye"><i class="ri-eye-line"></i></span>
                        </div>

                        <!--confirm password-->
                        <div class="sign-up-input-div required-input-div flex-col">

                            <label for="password2">Confirm password:</label>
                            <input type="password" id="password2"  name="password2" class="password-input" placeholder="Confirm password" value="${loginFormData.password}"/>

                            <span class="password-eye"><i class="ri-eye-line"></i></span>
                        </div>

                        <ul class="input-requirement">
                            <!--<i class="ri-checkbox-circle-fill"></i>-->
                            <li><i class="ri-close-circle-line"></i> Include at least 1 uppercase letter</li>
                            <li><i class="ri-close-circle-line"></i> Include at least 1 lowercase letter</li>
                            <li><i class="ri-close-circle-line"></i> Include at least 1 digit</li>
                            <li><i class="ri-close-circle-line"></i> Include at least 1 symbol</li>
                            <li><i class="ri-close-circle-line"></i> Have a minimum length of 8</li>
                        </ul>

                        <p class="invalid-msg"></p>
                        <input type="text" name="formType" value="validateData" hidden/>
                        <div class="sign-up-btn-div flex-col">
                            <label for="termAgree" class="term-acpt-label"><input id="termAgree" name="termAgree" type="checkbox" />I agree to the <a>term & condition</a></label>
                            <input class="sign-up-btn submit-btn" type="submit" value="Submit" disabled />
                            <input class="sign-up-btn back-btn" type="button" value="Back" />
                        </div>
                    </div>
                </form>

                <form action="sign-up" method="post">
                    <!--4th page or sign up form-->
                    <div class="sign-up-p sign-up-p4 flex-col">
                        <div class="sign-up-view-div  flex-col">
                            <h1 class="confirmation-title">Confirmation</h1>
                            <p class="confirmation-sub-title">Before proceeding, please confirm the details you've just entered.</p>
                        </div>

                        <!--section 1-->
                        <div class="sign-up-view-div  flex-col">
                            <label>Email:</label>
                            <input type="text" id="email" name="email" value="${loginFormData.email}" readonly/>
                        </div>

                        <div class="sign-up-view-div  flex-col">
                            <label>Username:</label>
                            <input type="text" id="username" name="username" value="${loginFormData.username}" readonly/>
                        </div>

                        <div class="sign-up-view-div  flex-col">
                            <label>Display Name:</label>
                            <input type="text" id="name" name="name" value="${loginFormData.displayName}" readonly/>
                        </div>

                        <div class="sign-up-view-div  flex-col">
                            <label>Gender:</label>
                            <input type="text" id="gender" name="gender" value="${loginFormData.gender}" readonly/>
                        </div>

                        <div class="sign-up-view-div  flex-col">
                            <label>Date of birth:</label>
                            <input type="text" id="dob" name="dob" value="${loginFormData.dobStr}" readonly/>
                        </div>

                        <!--section 2-->
                        <div class="sign-up-view-div flex-col">
                            <label>Address:</label>
                            <input type="text" id="address1" name="address1" value="${loginFormData.addressLine1}" readonly/>
                            <input type="text" id="address2" name="address2" value="${loginFormData.addressLine2}" readonly/>
                        </div>

                        <div class="sign-up-view-div flex-col">
                            <label>City:</label>
                            <input type="text" id="city" name="city" value="${loginFormData.city}" readonly/>
                        </div>

                        <div class="sign-up-view-div flex-col">
                            <label>Postal code:</label>
                            <input type="text" id="postalCode" name="postalCode" value="${loginFormData.postalCode}" readonly/>
                        </div>

                        <div class="sign-up-view-div flex-col">
                            <label>State:</label>
                            <input type="text" id="state" name="state" value="${loginFormData.state}" readonly/>
                        </div>

                        <div class="sign-up-view-div flex-col">
                            <label>Country:</label>
                            <input type="text" id="country" name="country" value="${loginFormData.country}" readonly/>
                        </div>

                        <input type="text" name="formType" value="confirmSubmit" hidden/>
                        <div class="sign-up-btn-div flex-col">
                            <input class="sign-up-btn submit-btn" type="submit" value="Confirm" />
                            <input class="sign-up-btn back-btn" name="userWantToEdit" type="submit" value="I want edit" />
                        </div>
                    </div>
                </form>

                <!--form for resend otp-->
                <form id="resendOTPForm" style="display: none;">
                    <input type="text" name="purpose" value="resend OTP" />
                </form>

                <!--5th page or sign up form-->
                <form action="sign-up" method="post">
                    <div class="sign-up-p sign-up-p5 flex-col">
                        <div class="sign-up-view-div  flex-col">
                            <h1 class="confirmation-title">OTP Verification</h1>
                            <p class="confirmation-sub-title">Code is sent to ${loginFormData.email}, input the code received, and verify to complete the process.</p>
                        </div>

                        <div class="otp-field-div flex-row">
                            <input type="text" id="otp1" name="otp1" class="otp" maxlength="1" autocomplete="off" />
                            <input type="text" id="otp2" name="otp2" class="otp" maxlength="1" autocomplete="off" />
                            <input type="text" id="otp3" name="otp3" class="otp" maxlength="1" autocomplete="off" />
                            <input type="text" id="otp4" name="otp4" class="otp" maxlength="1" autocomplete="off" />
                            <input type="text" id="otp5" name="otp5" class="otp" maxlength="1" autocomplete="off" />
                            <input type="text" id="otp6" name="otp6" class="otp" maxlength="1" autocomplete="off" />
                        </div>

                        <p class="invalid-msg"></p>
                        <input type="text" id="otp" name="otp" hidden />
                        <input type="text" name="formType" value="OTPForm" hidden/>

                        <div class="sign-up-btn-div otp-submit-div flex-col">
                            <input class="sign-up-btn submit-btn" type="submit" value="Submit" disabled />
                            <span class="resend-otp" id="resendBtn">Resend OTP</span>
                        </div>
                    </div>
                </form>

                <!--6th page successful message-->
                <div class="sign-up-p sign-up-p6 sign-up-success-div flex-col">
                    <img class="success-img" src="./img/sign_up/sign_up_success.png" alt="" draggable="false" />

                    <div class="flex-col">
                        <h1 class="success-title">Success</h1>
                        <p class="success-p">Start exploring and making the most of your experience with us.</p>
                        <p class="success-p">Feel free to reach out if you need any assistance. Happy exploring!</p>
                    </div>

                    <a class="login-btn" href="<%= webpath.getPageUrl("login")%>">Log in now</a>
                </div>

                <!--back to home btn-->
                <a class="back-to-home-a" href="<%= webpath.getPageUrl("home")%>">Back to home page <i class="ri-arrow-go-back-line"></i></a>
            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/@tsparticles/confetti@3.0.3/tsparticles.confetti.bundle.min.js"></script>
        <script src="./js/sign_up/sign_up.js"></script>
    </body>
</html>
