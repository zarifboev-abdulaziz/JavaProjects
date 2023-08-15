<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 6:27 PM
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
    <title>Mentor Info</title>
</head>
<body>
<div style="padding: 20px">
    <h5>${selectedMentor.firstName} ${selectedMentor.lastName}</h5>
    <p>Email: ${selectedMentor.email}</p>
    <h5>Courses</h5>

    <table class="table">
        <tr>
            <th>name</th>
            <th>View</th>
        </tr>

        <c:forEach var="course" items="${mentorCourses}">
            <tr>
                <td>${course.title}</td>
                <td>
                    <a class="btn btn-info" href="/courseInfo/${course.id}">More...</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="/activeCourses">Course List</a>
</div>

</body>
</html>
