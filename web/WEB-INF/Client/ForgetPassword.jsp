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
                <input type="number" min="1" max="4" id="formProgress" value="1" hidden />

                <!--insert error message here if any-->
                <input type="text"  id="errorInput" value="" hidden />
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
                        <p class="point-name">New password</p>
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
                            <input class="forget-btn next-btn" type="button" value="Next" />
                            <P class="sign-in-link">Want to login? <a href="<%= webpath.getPageUrl("login")%>">Sign in now</a></p>
                        </div>
                    </div>
                </form>

                <!--submit otp-->
                <form action="=" method="post" id="otpForm">

                </form>

                <!--submit new password-->
                <form action="=" method="post" id="passwordForm">

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
