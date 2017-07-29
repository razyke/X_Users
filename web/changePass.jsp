<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Pass Change</title>
</head>
<body>
    <form method="POST" action='UserActive' name="ChangePass">
    User ID: <c:out value="${user.idUser}"/>
        <input type="hidden" name="passtochange" value="<c:out value="${user.idUser}" />" />
    <br/>
    <br/>
        <c:choose>
            <c:when test="${user.admin eq ('true')}">
                Write new password for user:
            </c:when>
            <c:otherwise>
                Type new password:
            </c:otherwise>
        </c:choose>
        <input type="hidden" name="admin" value="<c:out value="${user.admin}"/>"/>

        <input
                type="text" name="password"
                value=""/>
        <br/>
        <br/>
    <input
    type="submit" value="Change"/>
    </form>
</body>
</html>
