<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/6/2022
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>My Tasks</h1><hr>

<table class="table">
    <tr><td>Title </td><td>Description </td><td>Deadline </td><td>Start date </td><td>Edit
    </td><td>Delete</td></tr>
    <c:forEach var="task" items="${taskList}">
        <tr>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.deadline}</td>
            <td>${task.created_at}</td>
            <td><a href="/updateTask?id=${task.id}">Edit</a></td>
            <td><a href="/deleteTask?id=${task.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
