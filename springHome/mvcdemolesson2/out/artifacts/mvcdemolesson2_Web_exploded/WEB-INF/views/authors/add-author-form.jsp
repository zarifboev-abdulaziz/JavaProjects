<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Add Author</title>
</head>
<body>
<div style="padding: 20px">
    <form action="/authors" method="post">
        <input type="text" class="form-control" placeholder="Enter Full name" name="fullName"><br>
        <input type="text" class="form-control" placeholder="Enter User name" name="userName"><br>
        <input type="email" class="form-control" placeholder="Enter Email" name="email"><br>
        <input type="password" class="form-control" placeholder="Enter password" name="password"><br>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>

</body>
</html>
