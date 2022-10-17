<%-- 
    Document   : users
    Created on : 17-Oct-2022, 2:46:41 PM
    Author     : Khanh Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        
        <table border="1">
            <tr>
                <td>Email</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Role</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${users}" var="account">
                <tr>
                    <td>${account.email}</td>
                    <td>${account.firstname}</td>
                    <td>${account.lastname}</td>
                    <td>${account.role}</td>
                    <td><a href="account?username=${account.username}">Edit</a></td>
                    <td><a href="account?username=${account.username}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>

        <h2>Add User</h2>
        <form action="users" method="post">
            Email: <input type="text" name="email" > <br>
            First name: <input type="text" name="firstname" > <br>
            Last name: <input type="text" name="lastname" > <br>
            Password: <input type="password" name="password" > <br>
            Role: <select name="roles" id="roles">
                <option value="system admin">system admin</option>
                <option value="regular user">regular user</option>
            </select> <br>

            <input type="hidden" name="action" value="add user">
            <br>
            <input type="submit" value="Add user">
        </form>
        <p>${message}</p>
    </body>
</html>
