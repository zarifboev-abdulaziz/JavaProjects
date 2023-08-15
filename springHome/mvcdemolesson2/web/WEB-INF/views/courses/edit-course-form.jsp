<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Edit Course</title>
</head>
<body>

<div style="padding: 20px">
    <form action="/courses" method="post">
        <input type="hidden" value="${selectedCourse.id}" name="id">

        <input type="text" class="form-control" placeholder="Enter course name" name="name" value="${selectedCourse.name}"><br>
        <textarea class="form-control"
                  placeholder="Enter course description" rows="3"
                  name="description">${selectedCourse.description}</textarea> <br>
        <button type="submit" class="btn btn-primary">Save & Edit</button>

    </form>
</div>

</body>
</html>
