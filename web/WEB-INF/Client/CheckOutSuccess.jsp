<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<% String transId = (String) request.getAttribute("TransactionNumber"); %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Checkout Review</title>
        <link rel="icon" type="image/ico" href="${companyIcon}">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/check_out.css" rel="stylesheet" >
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />

    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>
        <script src="./js/components/to_top.js"></script>

        <!--title section-->
        <section class="section title-section">
            <div class="title-div flex-row">
                <div class="flex-row">
                    <img class="title-logo" src="${companyLogo}" alt="" />
                    <h1 class="title">Order Confirmed</h1>
                </div>
                <%--<a class="cancel-btn" href="<%= webpath.getPageUrl("cart")%>">Cancel</a>--%>
            </div>
        </section>

        <section class="section content-section flex-col">

            <div class="content-div success-div flex-col">

                <img class="success-img" src="./img/check_out/success.png" alt="alt"/>

                <div>
                    <h1>Payment successful. Your transaction is complete.</h1>
                    <% if(transId!=null){ %>
                        <p>Transaction number: <%= transId %></p>     
                    <%}else{%>
                    <%}%>               
                </div>

                <a class="home-btn" href="<%= webpath.getPageUrl("home")%>">Back to home</a>

            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/@tsparticles/confetti@3.0.3/tsparticles.confetti.bundle.min.js"></script>
        <script>
            const duration = 1.5 * 1000,
                    animationEnd = Date.now() + duration,
                    defaults = {startVelocity: 30, spread: 360, ticks: 60, zIndex: 0};

            function randomInRange(min, max) {
                return Math.random() * (max - min) + min;
            }

            const interval = setInterval(function () {
                const timeLeft = animationEnd - Date.now();

                if (timeLeft <= 0) {
                    return clearInterval(interval);
                }

                const particleCount = 50 * (timeLeft / duration);

                // since particles fall down, start a bit higher than random
                confetti(
                        Object.assign({}, defaults, {
                            particleCount,
                            origin: {x: randomInRange(0.1, 0.3), y: Math.random() - 0.2}
                        })
                        );
                confetti(
                        Object.assign({}, defaults, {
                            particleCount,
                            origin: {x: randomInRange(0.7, 0.9), y: Math.random() - 0.2}
                        })
                        );
            }, 250);
        </script>

    </body>
</html>
