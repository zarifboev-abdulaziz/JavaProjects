<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Author Info</title>
</head>
<body>

<h5>Full Name: ${selectedAuthor.fullName}</h5><br>
<h5>Email: ${selectedAuthor.email}</h5><br>
<h5>Courses</h5><br>

<table class="table">
    <tr>
        <th>name</th>
        <th>View</th>
    </tr>

    <c:forEach var="course" items="${authorCourses}">
        <tr>
            <td>${course.name}</td>
            <td>
                <a class="btn btn-info" href="/courses/info/${course.id}">More...</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/courses/1">Course List</a>


</body>
</html>
