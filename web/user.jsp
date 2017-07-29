<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new user</title>
</head>
<body>
<script>
    $(function() {
        $('input[name=Birthday]').datepicker();
    });
</script>

<c:choose>
    <c:when test="${user.active eq ('true') || user.idUser == null}">

<form method="POST" action='UserController' name="frmAddUser">
    <c:choose>
        <c:when test="${user.idUser != null}">
            Time created: <fmt:formatDate pattern="MM-dd-yyyy" value="${user.createdTimeStamp}"/> <br />

            Last time updated: <fmt:formatDate pattern="MM-dd-yyyy HH:mm" value="${user.lastupdateTimeStamp}"/> <br />
            <br />

            User ID : <c:out value="${user.idUser}" />  <br /><br/>
            <input type="hidden" name="userId" value="<c:out value="${user.idUser}" />"/>
        </c:when>
        <c:otherwise>
            Password: <input
                type="text" name="Password"
                 /> <br /><br/>
        </c:otherwise>
    </c:choose>

    First Name : <input
        type="text" name="First_name"
        value="<c:out value="${user.firstName}" />" /> <br /><br/>
    Last Name : <input
        type="text" name="Last_name"
        value="<c:out value="${user.lastName}" />" /> <br /><br/>
    Login Name : <input
        type="text" name="Login_name"
        value="<c:out value="${user.loginName}" />" /> <br /><br/>

    Email : <input type="text" name="email"
                   value="<c:out value="${user.email}" />" /> <br /><br/>
    Birthday : <input
        type="text" name="Birthday"
        value="<fmt:formatDate pattern="MM/dd/yyyy" value="${user.birthday}" />" /> <br /><br/>
    isActive :
    <c:choose>
        <c:when test="${user.active eq ('true')}">
            <input
                    type="checkbox" name="isActive" checked="checked"
                    value="true" /><br /><br/>
        </c:when>
        <c:otherwise>
            <input
                    type="checkbox" name="isActive"
                    value="true" /><br /><br/>
        </c:otherwise>
    </c:choose>

    isAdmin :
    <c:choose>
        <c:when test="${user.admin eq ('true')}">
            <input
                    type="checkbox" name="isAdmin" checked="checked"
                    value="true" /><br /><br/>
        </c:when>
        <c:otherwise>
            <input
                    type="checkbox" name="isAdmin"
                    value="true" /><br /><br/>
        </c:otherwise>
    </c:choose>

    Group :
    <c:choose>
    <c:when test="${user.groupClass.idGroup == '1'}">
    <select id="group" name="groupId">
        <option selected value="1">Developer</option>
        <option value="2">Manager</option>
        <option value="3">Tester</option>
    </select>
    <br/>
    </c:when>
    <c:when test="${user.groupClass.idGroup == '2'}">
    <select id="group" name="groupId">
        <option value="1">Developer</option>
        <option selected value="2">Manager</option>
        <option value="3">Tester</option>
    </select>
    <br/>
    </c:when>
    <c:when test="${user.groupClass.idGroup == '3'}">
    <select id="group" name="groupId">
        <option value="1">Developer</option>
        <option value="2">Manager</option>
        <option selected value="3">Tester</option>
    </select>
    <br/>
    </c:when>
    <c:otherwise>
    <select id="group" name="groupId">
        <option value="1">Developer</option>
        <option value="2">Manager</option>
        <option value="3">Tester</option>
    </select>
    <br/>
    </c:otherwise>
    </c:choose>
    <br />


    ZIP: <input
        type="text" name="zip"
        value="<c:out value="${user.adressClass.zip}"/>"/><br /><br/>
    Country: <input
        type="text" name="Country"
        value="<c:out value="${user.adressClass.country}"/>"/><br /><br/>
    City: <input
        type="text" name="City"
        value="<c:out value="${user.adressClass.city}"/>"/><br /><br/>
    District: <input
        type="text" name="District"
        value="<c:out value="${user.adressClass.district}"/>"/><br /><br/>
    Street: <input
        type="text" name="Street"
        value="<c:out value="${user.adressClass.street}"/>"/><br /><br/>

    <input
        type="submit" value="Submit" />
</form>

        <c:choose>
            <c:when test="${user.idUser != null}">
                <br/>
                <form method="post" action="UserActive">
                    <input type="hidden" value="<c:out value="${user.idUser}"/>" name="userId">
                    <input type="hidden" value="true" name="userAdmin">
                    <input type="hidden" value="pas" name="changePass">
                    <input
                            type="submit" value="Change Password">
                </form>
            </c:when>
        </c:choose>

    </c:when>
    <c:otherwise>
<form method="POST" action='UserActive' name="frmActivateUser">
    User ID: <c:out value="${user.idUser}"/>
    <input type="hidden" name="ID" value="<c:out value="${user.idUser}" />" />
    <br/>
    <br/>
        To work with account please activate user:
        <input
                type="checkbox" name="Activate"
                value="true" /><br /><br/>
        <br/>
        <input
                type="submit" value="Submit" />
</form>
    </c:otherwise>
</c:choose>
<br/>
<a href="adminPage.jsp">Back</a>
<br/>
<br/>
<p style="color: green;">${changed}</p>
<p style="color: #d01c19;">${notchanged}</p>
</body>
</html>
