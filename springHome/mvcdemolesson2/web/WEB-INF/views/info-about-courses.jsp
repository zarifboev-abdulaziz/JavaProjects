<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Information</title>
</head>
<body>

<h5>Name: ${selectedCourse.name}</h5><br>
<h5>Description: ${selectedCourse.description}</h5><br>

<h5>Authors: </h5><br>

<table>

<c:forEach var="author" items="${authors}">

    <tr>
    <td>
     ${author.fullName}  <a class="btn btn-info" href="/courses/author/${author.id}">More...</a>
    </td>
    </tr>

</c:forEach>

</table>
<br>

<a href="/courses/1">Course List</a>

</body>
</html>
