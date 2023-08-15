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
    <title>Tasks</title>
</head>
<body>
<div style="padding: 20px">
    <a class="btn btn-info" href='/myLessons/${lastModuleId}'>Lessons</a>
    <h4>Task List</h4>

    <c:forEach var="task" items="${myTaskList}">

        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${task.title}</h5>
                <p class="card-text">${task.body}</p>
            </div>
        </div>

    </c:forEach>


    <br><a class="btn btn-success" href='/myLearningMaterials/${lessonId}'>Learning Materials</a>
    <br><a class="btn btn-danger" href='/likeButton/${lessonId}'>Likes - ${numberOfLikes}</a>
    <div style="padding: 20px">
        <h5>Comments</h5>
        <form action="/addComment/${lessonId}" method="post">
            <input type="text" class="form-control" placeholder="Write your comment here"
                   name="comment" required><br>
            <button type="submit" class="btn btn-primary">Add Comment</button>
        </form>
    </div>

    <c:forEach var="comment" items="${commentList}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${comment.userName}</h5>
                <p class="card-text">${comment.body}</p>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
