<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2022
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Country</title>
</head>
<body>
<h2>Add new country</h2>
<p></p>
<form action="/addCountry" method="post">

    <select name='country' style='width:150px'>
        <option value="1">Asia</option>
        <option value="2">Africa</option>
        <option value="3">Antarctica</option>
        <option value="4">Europe</option>
        <option value="5">Australia</option>
        <option value="6">North America</option>
        <option value="7">South America</option>
        <option value="8">Other</option>
    </select>

    <input type="text" name="name" placeholder="Enter continent name"><br>
    <button>Submit</button>
</form>


</body>
</html>