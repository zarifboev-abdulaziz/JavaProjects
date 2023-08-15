<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/6/2022
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
</head>
<body>

<h2>Edit My Profile</h2>

<form action="/editUserProfile" method="post">

    <input type="text" placeholder="Enter first Name" name="firstName"><hr>
    <input type="text" placeholder="Enter last Name" name="lastName"><hr>
    <input type="text" placeholder="Enter email" name="email"><hr>
    <input type="text" placeholder="Enter password" name="password"><hr>
    <button type="submit"> Submit </button>

</form>

</body>
</html>
