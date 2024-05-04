<%@ page contentType="text/html" pageEncoding="UTF-8"%>

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
        <form action="sign-up" method="post" id="signUpForm">
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
                    <input type="number" min="1" max="4" id="formProgress" value="1" hidden />
                    
                    <!--insert error message here if any-->
                    <input type="text"  id="errorInput" value="" hidden />
                    <span hidden id="errorMsg"></span>

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
                            <p class="point-name">Complete</p>
                        </div>
                    </div>

                    <!--1st page or sign up form-->
                    <div class="sign-up-p sign-up-p1 flex-col">
                        <!--email-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="email">Email:</label>
                            <input type="text" id="email"  name="email" placeholder="example@email.com" maxlength="50" />
                        </div>

                        <!--username-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="username">Username:</label>
                            <input type="text" id="username"  name="username" placeholder="only characters, digits, and the special symbols (!, #, _, .)" maxlength="25" autocomplete="off" />
                        </div>

                        <!--display name-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="name">Display Name:</label>
                            <input type="text" id="name"  name="name" placeholder="name" maxlength="30" autocomplete="off"  />
                        </div>

                        <!--gender-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="gender">Gender:</label>
                            <select id="gender">
                                <option selected hidden>Select gender</option>
                                <option value="female">Female</option>
                                <option value="male">Male</option>
                            </select>
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
                            <input type="text" id="address1"  name="address1" placeholder="address line 1" maxlength="50" />
                            <input type="text" id="address2"  name="address2" placeholder="address line 2" maxlength="50" />
                        </div>

                        <!--City-->
                        <div class="sign-up-input-div flex-col">
                            <label for="city">City:</label>
                            <input type="text" id="city"  name="city" placeholder="city name" maxlength="20" />
                        </div>

                        <!--Postal Code-->
                        <div class="sign-up-input-div flex-col">
                            <label for="postalCode">Postel code:</label>
                            <input type="text" id="postalCode"  name="postalCode" placeholder="postal code" maxlength="9" />
                        </div>

                        <!--State-->
                        <div class="sign-up-input-div flex-col">
                            <label for="state">State Resides:</label>
                            <input type="text" id="state"  name="state" placeholder="state name" maxlength="20" />
                        </div>

                        <!--Country-->
                        <div class="sign-up-input-div flex-col">
                            <label for="state">Country:</label>
                            <input type="text" id="country"  name="country" placeholder="country name" maxlength="40" />
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
                            <input type="password" id="password"  name="password" placeholder="Password" />
                        </div>

                        <!--confirm password-->
                        <div class="sign-up-input-div required-input-div flex-col">
                            <label for="password2">Password:</label>
                            <input type="password" id="password2"  name="password2" placeholder="Confirm password" />
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

                        <div class="sign-up-btn-div flex-col">
                            <label for="termAgree" class="term-acpt-label"><input id="termAgree" name="termAgree" type="checkbox" />I agree to the <a>term & condition</a></label>
                            <input class="sign-up-btn submit-btn" type="submit" value="Submit" disabled />
                            <input class="sign-up-btn back-btn" type="button" value="Back" />
                        </div>
                    </div>

                    <!--back to home btn-->
                    <a class="back-to-home-a" href="<%= webpath.getPageUrl("home")%>">Back to home page <i class="ri-arrow-go-back-line"></i></a>
                </div>
            </section>
        </form>

        <script src="./js/sign_up/sign_up.js"></script>
    </body>
</html>
