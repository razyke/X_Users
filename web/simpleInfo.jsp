<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>User info page</title>
</head>
<body>
        <form method="GET" action='login' name="viewSimpleInfo">

            User    ID :  <c:out value="${user.idUser}"/><br/>
            First Name :  <c:out value="${user.firstName}"/><br/>
            Last  Name :  <c:out value="${user.lastName}"/><br/>
            Login Name :  <c:out value="${user.loginName}"/><br/>
            Email : <c:out value="${user.email}" /><br/>
            Birthday :  <fmt:formatDate pattern="MM/dd/yyyy" value="${user.birthday}"/><br/>
            Active :
            <c:choose>
                <c:when test="${user.active eq ('true')}">
                    Yes
                    <br/>
                </c:when>
                <c:otherwise>
                    No
                    <br/>
                </c:otherwise>
            </c:choose>
            Group :
            <c:choose>
                <c:when test="${user.groupClass.idGroup == '1'}">
                    Developer
                    <br/>
                </c:when>
                <c:when test="${user.groupClass.idGroup == '2'}">
                    Manager
                    <br/>
                </c:when>
                <c:when test="${user.groupClass.idGroup == '3'}">
                    Tester
                    <br/>
                </c:when>
            </c:choose>
            ZIP:  <c:out value="${user.adressClass.zip}"/><br/>
            Country:  <c:out value="${user.adressClass.country}"/><br/>
            City: <c:out value="${user.adressClass.city}"/><br/>
            District:  <c:out value="${user.adressClass.district}"/><br/>
            Street:  <c:out value="${user.adressClass.street}"/><br/><br/>
            <input
                    type="submit" value="Exit" />
        </form>
        <c:choose>
            <c:when test="${user.active eq ('true')}">
                <form method="post" action="UserActive">
                <input type="hidden" value="<c:out value="${user.idUser}"/>" name="userId">
                <input type="hidden" value="<c:out value="${user.admin}"/>" name="userAdmin">
                <input type="hidden" value="pas" name="changePass">
                <input
                type="submit" value="Change Password">
                </form>
                <p style="color: green;">${changed}</p>
                <p style="color: #d01c19;">${notchanged}</p>
            </c:when>
        </c:choose>

</body>
</html>
