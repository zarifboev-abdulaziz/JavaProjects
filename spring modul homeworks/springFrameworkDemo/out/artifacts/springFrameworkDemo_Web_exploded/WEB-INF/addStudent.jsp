<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.02.2022
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<h2>
  Add new Student
</h2>
<form action="/addStudent" method="post">
    <label>
    <input type="text" placeholder="Enter full name" name="fullName"><br>
    </label>
    <label>
        <input type="text" placeholder="Enter age" name="age"><br>
    </label>
    <label>
        <input type="text" placeholder="Enter phone number" name="phoneNumber"><br>
    </label>

    <button>Submit</button>
</form>

</body>
</html>
