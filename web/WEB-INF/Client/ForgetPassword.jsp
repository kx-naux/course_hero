<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Forget Password</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/forgetPassword.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>

        <section class="forget-section flex-col">
            <div class="forget-div flex-col">

                <!--position absolute-->
                <div class="logo-div">
                    <a href="<%= webpath.getPageUrl("home")%>"><img src="./img/Logo.png" alt="Course Hero" /></a>
                </div>

                <!--sign up title-->
                <div class="forget-title-div">
                    <h1>Forget Password</h1>
                </div>

                <!--hidden value for step count-->
                <!--1 = email-->
                <!--2 = otp-->
                <!--3 = password-->
                <!--4 = success-->
                <input type="number" min="1" max="4" id="formProgress" value="2" hidden />

                <!--insert error message here if any-->
                <input type="text" id="errorInput" value="" hidden />
                <span hidden id="errorMsg"></span>

                <!--step progress bar-->
                <div class="form-progress-bar flex-row">
                    <div class="progress-point flex-col">
                        <span class="point-number">1</span>
                        <p class="point-name">Email</p>
                    </div>
                    <hr class="progress-line" />
                    <div class="progress-point flex-col">
                        <span class="point-number">2</span>
                        <p class="point-name">Verification</p>
                    </div>
                    <hr class="progress-line" />
                    <div class="progress-point flex-col">
                        <span class="point-number">3</span>
                        <p class="point-name">Set password</p>
                    </div>
                </div>

                <!--submit email-->
                <form action="=" method="post" id="emailForm">
                    <!--1st page or sign up form-->
                    <div class="forget-p forget-p1 flex-col">
                        <!--email-->
                        <div class="forget-input-div required-input-div flex-col">
                            <label for="email">Email:</label>
                            <input type="text" id="email"  name="email" placeholder="example@email.com" maxlength="50" value="${loginFormData.email}"/>
                        </div>

                        <p class="invalid-msg"></p>

                        <div class="forget-btn-div flex-col">
                            <input class="forget-btn next-btn" id="submitEmailBtn" type="button" value="Next" />
                            <P class="sign-in-link">Want to login? <a href="<%= webpath.getPageUrl("login")%>">Sign in now</a></p>
                        </div>
                    </div>
                </form>

                <!--submit otp-->
                <form action="=" method="post" id="otpForm">
                    <div class="forget-p forget-p5 flex-col">
                        <div class="forget-view-div  flex-col">
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

                        <p class="invalid-msg"></p>
                        <input type="text" id="otp" name="otp" hidden />
                        <input type="text" name="formType" value="OTPForm" hidden/>

                        <div class="forget-btn-div otp-submit-div flex-col">
                            <input class="forget-btn submit-btn" type="submit" value="Submit" disabled />
                            <span class="resend-otp" id="resendBtn">Resend OTP</span>
                        </div>
                    </div>
                </form>

                <!--form for resend otp-->
                <form id="resendOTPForm" style="display: none;">
                    <input type="text" name="purpose" value="resend OTP" />
                </form>

                <!--submit new password-->
                <form action="=" method="post" id="passwordForm">
                    <div class="forget-p forget-p3 flex-col">
                        <!--password-->
                        <div class="forget-input-div required-input-div flex-col">
                            <label for="password">Password:</label>
                            <input type="password" id="password"  name="password" class="password-input" placeholder="Password" value="${loginFormData.password}"/>
                            <span class="password-eye"><i class="ri-eye-line"></i></span>
                        </div>

                        <!--confirm password-->
                        <div class="forget-input-div required-input-div flex-col">

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
                                               
                        <div class="forget-btn-div flex-col">
                            <input class="forget-btn submit-btn" type="submit" value="Submit" disabled />
                        </div>
                    </div>
                </form>

                <!--success page-->
                <div class="forget-p forget-p6 forget-success-div flex-col">
                    <img class="success-img" src="./img/sign_up/sign_up_success.png" alt="" draggable="false" />

                    <div class="flex-col">
                        <h1 class="success-title">Password Successfully Updated</h1>
                        <p class="success-p">Your password has been successfully updated.</p>
                        <p class="success-p">Feel free to reach out if you need any assistance. Happy exploring!</p>
                    </div>

                    <a class="login-btn" href="<%= webpath.getPageUrl("login")%>">Log in now</a>
                </div>

                <!--back to home btn-->
                <a class="back-to-home-a" href="<%= webpath.getPageUrl("home")%>">Back to home page <i class="ri-arrow-go-back-line"></i></a>

            </div>
        </section>

        <script src="./js/forget_password/forget_password.js"></script>        

    </body>
</html>
