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
    <title>Learning Materials</title>
</head>
<body>
<div style="padding: 20px">
    <a class="btn btn-info" href='/myLessons/${lastModuleId}'>Lessons</a>
    <h4>Learning Material List</h4>

    <c:forEach var="learningMaterial" items="${myLearningMaterialList}">

        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${learningMaterial.title}</h5>
                <p class="card-text">${learningMaterial.information}</p>
            </div>
        </div>

    </c:forEach>

    <c:choose>
        <c:when test="${myLearningMaterialList.size()!=0}">
            <br>
            <a class="btn btn-success" href='/myTasks/${myLearningMaterialList.get(0).lessonId}'>Tasks</a>
        </c:when>
    </c:choose>

</div>
</body>
</html>
