<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/5/2022
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>s
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<h1>Registration Process</h1><br>

<form action="/register" method="post">
    <input type="text" placeholder="First Name" name="firstName"><br>
    <input type="text" placeholder="Last Name" name="lastName"><br>
    <input type="text" placeholder="email" name="email"><br>
    <input type="password" placeholder="password" name="password"><br>
    <button type="submit"> Submit </button><br>
</form>


</body>
</html>
