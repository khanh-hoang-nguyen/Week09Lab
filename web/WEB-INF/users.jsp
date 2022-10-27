<%-- 
    Document   : users
    Created on : 17-Oct-2022, 2:46:41 PM
    Author     : Khanh Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <p>${message}</p>
        <table border="1">
            <tr>
                <td>Email</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Role</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td name="email">${user.email}</td>
                    <td name="firstname">${user.firstName}</td>
                    <td name="lastname">${user.lastName}</td>
                    <td name="role">${user.role}</td>
                    <td>
                        <c:url value="/users" var="editUser">
                            <c:param name="action" value="edit" />
                            <c:param name="email" value="${user.email}" />
                        </c:url>
                        <a href=${editUser}>Edit</a>
                    </td>
                    <td>
                        <c:url value="/users" var="deleteUser">
                            <c:param name="action" value="delete" />
                            <c:param name="email" value="${user.email}" />
                        </c:url>
                        <a href=${deleteUser}>Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedUser eq null}">
            <h2>Add User</h2>
            <form action="users" method="post">
                Email: <input type="text" name="email" > <br>
                First name: <input type="text" name="firstname" > <br>
                Last name: <input type="text" name="lastname" > <br>
                Password: <input type="password" name="password" > <br>
                Role: <select name="role" id="roles">
                    <option value="system admin">system admin</option>
                    <option value="regular user">regular user</option>
                </select> <br>

                <br>
                <input type="hidden" name="action" value="create">
                <input type="submit" value="Add user">
            </form>
        </c:if>

        <c:if test="${selectedUser ne null}">
            <h2>Edit User</h2>
            <form action="users" method="post">
                <p>Email ${selectedUser.email}</p>
                First name: <input type="text" name="firstname" value="${selectedUser.firstName}" > <br>
                Last name: <input type="text" name="lastname" value="${selectedUser.lastName}" > <br>
                Password: <input type="password" name="password" > <br>
                Role: <select name="role" id="roles">
                    <c:forEach items="${roles}" var="myRole">
                        <option value="${myRole.roleName}" ${myRole.roleName == selectedUser.role ? selectedUser.role : myRole.roleName}>${selectedUser.role}</option>
                    </c:forEach>

                </select> <br>
            </form>
            <form action="users" method="post">
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Update">
            </form>
            <form action="users">
                <input type="submit" value="Cancel" />
            </form>
        </c:if>

    </body>
</html>
