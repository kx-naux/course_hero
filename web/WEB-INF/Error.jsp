<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/error.css" rel="stylesheet" >
        <title>Error ${param.code}</title>
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!-- Include the navigation bar -->
        <%@ include file="./Client/Components/navbar.jsp" %>

        <div class="error-content-div flex-row">
            <div class="error-content-left flex-col">
                <h1>${param.code} - error</h1>
                <h2>${sessionScope.errorDesc}</h2>
                <p>${sessionScope.errorDetail}</p>
                <a href="<%= webpath.getPageUrl("home") %>">Back to home</a>
            </div>
            <div class="error-content-right flex-col">
                <div data-js="astro" class="astronaut">
                    <div class="head"></div>
                    <div class="arm arm-left"></div>
                    <div class="arm arm-right"></div>
                    <div class="body">
                        <div class="panel"></div>
                    </div>
                    <div class="leg leg-left"></div>
                    <div class="leg leg-right"></div>
                    <div class="schoolbag"></div>
                </div>
            </div>
        </div>
    </body>
</html>
