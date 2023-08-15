<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.02.2022
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Course</title>
</head>
<body>

<h2>Course List</h2><br>

<table  border='1' width='50%'>
<tr><th>Course Number</th><th>Course Name</th><th>Course Info</th></tr>

    <c:forEach items="${courses}" var="course">
        <tr>
        <td>${course.courseNumber}</td>
        <td>${course.courseName}</td>
        <td>${course.courseInfo}</td>
        </tr>
    </c:forEach>

</table>

<a href="/addCourse">Add new Course</a>

</body>
</html>
