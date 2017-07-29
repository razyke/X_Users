<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h3>Login Page</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="login">
    <table border="0">
        <tr>
            <td>Login Name</td>
            <td><input type="text" name="userName" value= "${user.loginName}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td colspan ="1">
                <input type="submit" value= "Login" />
            </td>
        </tr>
    </table>
</form>

<p style="color:blue;">Login Name: razyke, password: 12345 (Admin) or otw / 12321 (User)</p>

</body>
</html>
