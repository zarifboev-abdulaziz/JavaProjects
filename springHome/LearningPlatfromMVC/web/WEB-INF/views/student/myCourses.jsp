<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>My Courses</title>
</head>
<body>
<div style="padding: 20px">
    <a class="btn btn-info" href='/studentHome'>Home</a>

    <h3>My Courses</h3>
    <table class="table">
        <c:forEach var="course" items="${myCourseList}">
            <tr>
                <td>
                    <a class="btn btn-success" href='/myModules/${course.id}'>${course.title}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
