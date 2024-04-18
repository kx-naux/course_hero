<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Login</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/login.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <script src="./js/login/login.js"></script>
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <section class="login-section flex-col">
            <div class="login-div flex-row">
                <div class="login-panel login-left flex-col">
                    <div class="login-left-row-1">
                        <a href="<%= webpath.getPageUrl("home") %>"><img class="login-logo" src="./img/Logo.png" alt="Course Hero" /></a>
                    </div>
                    <div class="login-form-div flex-col">
                        <h1>Welcome Back</h1>
                        <div class="login-form-input-div flex-col">
                            <div class="input-field">
                                <input type="text" id="username" class="form-input" placeholder="Username / Email" oninput="reset_error_msg()" />
                                <label for="username" class="form-label">Username / Email</label>
                            </div>
                            <div class="input-field">
                                <input type="password" id="password" class="form-input" placeholder="Password" oninput="reset_error_msg()" />
                                <label for="password" class="form-label">Password</label>
                            </div>
                            <a class="forget-password" href="<%= webpath.getPageUrl("forget passworde") %>"><p>Forget password</p></a>
                        </div>
                        <p class="invalid-msg">Invalid login Credential</p>
                        <div class="login-form-bot flex-col">
                            <input type="button" value="Sign In" onclick="" />
                            <P>Doesn't have account? <a class="sign-up" href="<%= webpath.getPageUrl("sign up") %>">Sign up now</a></P>
                        </div>
                    </div>
                </div>
                <div class="login-panel login-right">
                    <div class="login-right-content">
                        
                    </div>
                    <div class="login-right-background">
                        <div class="right-backgound-div shape-1"></div>
                        <div class="right-backgound-div shape-2"></div>
                        <div class="right-backgound-div shape-3"></div>
                    </div>
                </div>
                <a class="back-to-home-a" href="<%= webpath.getPageUrl("home") %>">Back to home page <i class="ri-arrow-go-back-line"></i></a>
            </div>
        </section>
    </body>
</html>
