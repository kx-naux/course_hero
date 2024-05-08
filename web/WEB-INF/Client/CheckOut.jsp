<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Wishlist</title>
        <link rel="icon" type="image/ico" href=${companyIcon}>
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/check_out.css" rel="stylesheet" >
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>
        <script src="./js/components/to_top.js"></script>
        
        <!--to show a toast error message--> 
        <span id="errorMsg" hidden=""></span>
        
        <!--title section-->
        <section class="section title-section">
            <div class="title-div flex-row">
                <img class="title-logo" src="${companyLogo}" alt="" />
                <h1 class="title">Check Out</h1>
            </div>
        </section>
        
        <section class="section content-section">
            <div class="ship-div flex-col">
                
            </div>
        </section>

        <script src="./js/check_out/check_out.js"></script>
        
    </body>
</html>
