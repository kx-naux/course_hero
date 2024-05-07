<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Survey</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/new_user.css" rel="stylesheet" >
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>

        <!--hidden field to trace page number-->
        <input type="number" value="1" hidden />

        <form method="post" id="newUserSurveyForm">
            <section class="survey-section flex-col">
                <div class="survey-div flex-col">

                    <!--sign up title-->
                    <div class="survey-title-div">
                        <h1>New User Survey</h1>
                    </div>

                </div>
            </section>
        </form>

        <script src="./js/new_user/new_user.js"></script>

    </body>
</html>
