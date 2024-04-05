<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Home</title>
    </head>
    <body>
        <nav>
            <c:choose>
                <c:when test="${not empty sessionScope.username}">
                    <p>Welcome, ${sessionScope.username}!</p>
                </c:when>
                <c:otherwise>
                    <p>You are not logged in.</p>
                </c:otherwise>
            </c:choose>
        </nav>
    </body>
</html>
