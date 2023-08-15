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
    <title>Courses</title>
</head>
<body>
<div style="padding: 20px">

    <h4>${selectedCourse.title}</h4>
    <p>${selectedCourse.description}</p>
    <p>
        Number of modules: ${selectedCourse.modules.size()} <br>
        Number of lessons: ${countLesson} <br>
        Mentors:
        <c:forEach var="mentor" items="${selectedCourse.mentors}">
            <a class="btn btn-success"
               href='/mentorInfo/${mentor.id}'>${mentor.firstName} ${mentor.lastName}</a>
        </c:forEach>
    </p>
    <h4>Modules</h4>

    <div class="card-group">
        <c:forEach var="modul" items="${selectedCourse.modules}">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">modul- ${modul.orderNumber}</h5>
                    <p class="card-text">${modul.title}</p>
                    <p class="card-text">Lessons: </p>
                    <c:forEach var="lesson" items="${modul.lessons}">
                        <p>${lesson.orderNumber} - ${lesson.title}</p>
                    </c:forEach>
                </div>
            </div>

        </c:forEach>
    </div>

    <h4 style="color: green">Price: ${selectedCourse.price}</h4>

    <c:choose>
        <c:when test="${isCoursePurchased == true}">
            <button type="button" class="btn btn-secondary" disabled>Purchased</button>
        </c:when>
        <c:when test="${isCoursePurchased == false}">
            <a class="btn btn-success" href='/studentPurchase/${selectedCourse.id}'>Purchase
                Course</a>
        </c:when>
    </c:choose>


</div>
</body>
</html>
