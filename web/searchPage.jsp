<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Birthday</th>
        <th>Active</th>
        <th>Admin</th>
        <th>Created</th>
        <th>Last Update</th>
        <th>Group</th>
        <th>zip</th>
        <th>Country</th>
        <th>City</th>
        <th>District</th>
        <th>Street</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.idUser}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.lastName}" /></td>
            <td><c:out value="${user.loginName}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.birthday}" /></td>
            <c:choose>
                <c:when test="${user.active eq ('true')}">
                    <td><c:out value="Yes"/></td>
                </c:when>
                <c:otherwise>
                    <td><c:out value="No"/></td>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${user.admin eq ('true')}">
                    <td><c:out value="Yes"/></td>
                </c:when>
                <c:otherwise>
                    <td><c:out value="No"/></td>
                </c:otherwise>
            </c:choose>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${user.createdTimeStamp}" /></td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${user.lastupdateTimeStamp}" /></td>

            <c:choose>
                <c:when test="${user.groupClass.idGroup == '1'}">
                    <td><c:out value="Developer"/></td>
                </c:when>
                <c:when test="${user.groupClass.idGroup == '2'}">
                    <td><c:out value="Manager"/></td>
                </c:when>
                <c:when test="${user.groupClass.idGroup == '3'}">
                    <td><c:out value="Tester"/></td>
                </c:when>
            </c:choose>


            <td><c:out value="${user.adressClass.zip}" /></td>
            <td><c:out value="${user.adressClass.country}" /></td>
            <td><c:out value="${user.adressClass.city}" /></td>
            <td><c:out value="${user.adressClass.district}" /></td>
            <td><c:out value="${user.adressClass.street}" /></td>

            <td><a href="UserController?action=edit&userId=<c:out value="${user.idUser}"/>">Update</a></td>
            <td><a href="UserController?action=delete&userId=<c:out value="${user.idUser}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
