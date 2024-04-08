<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Home</title>
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav>
            <div class="nav-logo-div">
                
            </div>
            <div class="nav-option-div">

            </div>
            <div class="nav-function-div">
                <c:choose>
                    <c:when test="${not empty sessionScope.username}">
                        <!-- Logged in -->
                        <p>Welcome, ${sessionScope.username}!</p>
                    </c:when>
                    <c:otherwise>
                        <!-- Not Logged in -->
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>

    </body>
</html>
