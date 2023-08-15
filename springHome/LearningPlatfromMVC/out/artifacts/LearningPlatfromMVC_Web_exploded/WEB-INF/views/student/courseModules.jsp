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
    <title>Modules</title>
</head>
<body>
<div style="padding: 20px">

    <a class="btn btn-info" href='/myCourses'>My Courses</a>
    <h4>Module List</h4>

    <p>
        Mentors:
        <c:forEach var="mentor" items="${mentors}">
            <a class="btn btn-success"
               href='/mentorInfo/${mentor.id}'>${mentor.firstName} ${mentor.lastName}</a>
        </c:forEach>
    </p>

    <div class="card-group">
        <c:forEach var="modul" items="${courseModules}">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${modul.title}</h5>
                    <a class="btn btn-success" href='/myLessons/${modul.id}'>Modul-${modul.orderNumber}</a>
                </div>
            </div>

        </c:forEach>
    </div>

</div>
</body>
</html>
