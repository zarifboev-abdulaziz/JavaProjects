<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/3/2022
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<h3>Continent list</h3><p></p>
<table class="table" width='50%'>
    <tr><th>Continent Name</th><th>Edit</th><th>Delete</th></tr>

    <c:forEach items="${continents}" var="continent">

        <tr>
            <td>${continent.name}</td>
            <td><a href = '/updateContinent?id=${continent.id}'> edit </a> </td>
            <td><a href = '/deleteContinent?id=${continent.id}'> delete </a> </td>
        </tr>

    </c:forEach>


</table><br>
<a href ='/home'>Go to home</a><br>


</body>
</html>
