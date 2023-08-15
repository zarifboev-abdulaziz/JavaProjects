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
    <title>Student</title>
</head>
<body>

<a href="/addStudent">Add new student</a>

<h2>Student List</h2><br>

<tr>
<c:forEach items="${students}" var="student">

        <td>${student.fullName}</td>
        <td>${student.age}</td>
        <td>${student.phoneNumber}</td>
    <p></p>

</c:forEach>
</tr>

</body>
</html>
