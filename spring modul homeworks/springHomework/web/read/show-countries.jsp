<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/4/2022
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Countries</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<h3>Country list</h3><p></p>

<table class="table" width='50%'>
    <tr><th>Continent Name</th><th>Country Name</th><th>Edit</th><th>Delete</th></tr>

    <c:forEach items="${countries}" var="country">

        <tr>
            <td>${country.name}</td>
            <td>${country.continentName}</td>
            <td><a href = '/updateCountry?id=${country.id}'> edit </a> </td>
            <td><a href = '/deleteCountry?id=${country.id}'> delete </a> </td>
        </tr>

    </c:forEach>
</table><br>

<c:forEach var="j" begin="1" end="${pageCount}">
    <a href='showCountries?page=${j}'> ${j} </a>
</c:forEach>

<br>
<a href ='/home'>Go to home</a><br>

</body>
</html>
