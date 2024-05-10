<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/ico" href=${companyLogo}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/error.css" rel="stylesheet" >
        <link type="text/css" href="../css/error.css" rel="stylesheet" >
        <link type="text/css" href="../css/style.css" rel="stylesheet" >
        <title>Error ${param.code}</title>
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!--logo-->
        <a href="<%= webpath.getPageUrl("home")%>"><img class="nav-logo" src="./img/Logo.png" onerror="" alt="Course Hero" /></a>
        <div class="error-content-div flex-row">
            <!--error message-->
            <div class="error-content-left flex-col">
                <h1>Error ${param.code}</h1>
                <h2>${sessionScope.errorDesc}</h2>
                <p>${sessionScope.errorDetail}</p>  
                <a href="<%= webpath.getPageUrl("home")%>">Back to home</a>
            </div>
            <div class="error-content-right flex-col">
                <div class="astro-bg astro-bg-1"></div>
                <div class="astro-bg astro-bg-2"></div>
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
