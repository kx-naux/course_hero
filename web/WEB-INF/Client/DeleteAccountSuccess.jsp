<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Delete Account</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/forgetPassword.css" rel="stylesheet" >
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
    <body>
        <section class="forget-section flex-col">
            <div class="forget-div flex-col">

                <!--position absolute-->
                <div class="logo-div">
                    <a href="<%= webpath.getPageUrl("home")%>"><img src=${companyLogo} alt=${companyName} /></a>
                </div>

                <!--sign up title-->
                <div class="forget-title-div">
                    <h1>Account Closure Successful!</h1>
                </div>

                <!--success page-->
                <div class="forget-success-div flex-col">
                    <img class="success-img" src="./img/sign_up/clean_up.png" alt="" draggable="false" />

                    <div class="flex-col">
                        <h1 class="success-title">Thank you for being a part of our community!</h1>
                        <p class="success-p" style="max-width: 550px;margin-top: 10px;">Your account has been successfully closed. If you have any questions or need further assistance, please don't hesitate to contact our support team.</p>
                    </div>

                    <a class="login-btn" href="<%= webpath.getPageUrl("home")%>">Back to home</a>
                </div>

            </div>
        </section>

    </body>
</html>
