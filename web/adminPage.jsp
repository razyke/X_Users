<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br/><br/>
<form method="post" action="search" name="Search" >
    <table border="0" width="300">
        <tr><td colspan=2 style="font-size:12pt;" align="center">
            <h3>Search User</h3></td></tr>
        <tr><td >User Name <br/> Email <br/> Birthday<br/>(yyyy-mm-dd)</td>
            <td> <input  type="text" name="query" value="">
            </td></tr>
        <tr><td colspan=2 align="center">
            <input  type="submit" name="submit" value="Search"></td></tr>
    </table>
</form>
<br/>
<form method="post" action="UserActive" name="showManagers">
    <input type="hidden" value="1" name="groupID">
    <input type="submit" name="show" value="Developers">
</form>
<br/>
<form method="post" action="UserActive" name="showManagers">
    <input type="hidden" value="2" name="groupID">
    <input type="submit" name="show" value="Managers">
</form>
<br/>
<form method="post" action="UserActive" name="showManagers">
    <input type="hidden" value="3" name="groupID">
    <input type="submit" name="show" value="Testers">
</form>

<p><a href="UserController?action=insert">Add User</a></p>
<p><a href="login">Exit</a></p>

<p style="color: green;">${added}</p>
<p style="color: #dd0f09;">${error}</p>

</body>
</html>
