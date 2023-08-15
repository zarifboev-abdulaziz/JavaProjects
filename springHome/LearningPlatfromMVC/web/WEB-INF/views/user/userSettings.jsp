<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>My Profile Settings</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

</head>
<body>
<div style="padding: 20px">
    <a class="btn btn-info" href='/studentHome'>Home</a>

    <h5>Profile Settings</h5><br>

    <div style="padding: 20px">
        <form action="/mySettings" method="post">
            <input type="hidden" class="form-control" value="${user.id}" name="id">
            <input type="text" class="form-control" value="${user.firstName}"
                   name="firstName" required><br>
            <input type="text" class="form-control" value="${user.lastName}"
                   name="lastName" required><br>
            <input type="email" class="form-control" value="${user.email}" name="email"
                   required><br>

            <button type="submit" class="btn btn-primary">Edit & Save</button>
        </form>
    </div>

    <a class="btn btn-warning" href='/changePassword'>Change my password</a>

    <c:choose>
        <c:when test="${isChangingPassword == true}">
            <div style="padding: 20px">
                <form action="/changePassword" method="post">
                    <input type="password" class="form-control" placeholder="Enter old Password"
                           name="oldPassword" required><br>
                    <input type="password" class="form-control" placeholder="Enter new Password"
                           name="newPassword" required><br>

                    <button type="submit" class="btn btn-primary">Edit Password</button>
                </form>
            </div>
        </c:when>
    </c:choose>



</div>

</body>
</html>
