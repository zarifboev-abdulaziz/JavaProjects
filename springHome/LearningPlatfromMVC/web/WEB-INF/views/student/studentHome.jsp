<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/12/2022
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<h3>${message}</h3><br>

<div style="padding: 20px">
    <a class="m-4 btn btn-success" href="activeCourses">Buy Course</a>
    <a class="m-4 btn btn-success" href="myCourses">My Courses</a>
    <a class="m-4 btn btn-success" href="/mySettings">Profile Settings</a>
    <a class="m-4 btn btn-success" href="/myBalance">Show my Balance</a>
    <a class="m-4 btn btn-success" href="/logout">Log out</a>
</div>

</body>
</html>
