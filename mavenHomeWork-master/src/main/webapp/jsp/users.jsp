
<%@ page import="java.util.List" %>
<%@ page import="Models.User" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>USERNAME</th>
            <th>EMAIL</th>

        </tr>
        <c:forEach items="${usersForJsp}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.userEmail}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
