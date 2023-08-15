<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Authors</title>
</head>
<body>
<a class="m-4 btn btn-success" href="/authors/addForm">+Add new Author</a>
<a class="m-4 btn btn-success" href="/courses">Courses</a>

<table class="table">
    <tr>
        <th>Full Name</th>
        <th>Email</th>
        <th>Actions</th>
        <th>View</th>
    </tr>

    <c:forEach var="author" items="${authorList}">
        <tr>
            <td>${author.fullName}</td>
            <td>${author.email}</td>
            <td>
                <a class="btn btn-warning" href='/authors/editForm/${author.id}'>Edit</a>
                <a class="btn btn-danger" href="/authors/delete/${author.id}">Delete</a>
            </td>
            <td>
                <a class="btn btn-info" href="/courses/author/${author.id}">More...</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
