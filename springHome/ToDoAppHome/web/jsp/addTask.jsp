<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/6/2022
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Task</title>
</head>
<body>
<h2>Add new Task</h2>

<form action="/addTask" method="post">

    <input type="text" placeholder="Enter title" name="title"><hr>
    <input type="text" placeholder="Enter description" name="description"><hr>
    <input type="datetime-local" placeholder="Enter deadline" name="deadline"><hr>
    <input type="datetime-local" placeholder="Enter start date" name="created_at"><hr>
    <button type="submit"> Submit </button>

</form>

</body>
</html>
