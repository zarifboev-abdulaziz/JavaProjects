<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/14/2022
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <title>My Balance</title>
</head>
<body>

<div style="padding: 20px">
    <a class="btn btn-info" href='/studentHome'>Home</a>

    <h5>My Balance: ${user.balance}</h5><br>

    <a class="btn btn-success" href='/setBalance'>fill Balance</a>
    <c:choose>
        <c:when test="${setBalance == true}">
            <div style="padding: 20px">
                <form action="/setBalance" method="post">
                    <input type="number" class="form-control" placeholder="Enter Money"
                           name="money" required><br>

                    <button type="submit" class="btn btn-primary">Fill</button>
                </form>
            </div>
        </c:when>
    </c:choose>


</div>

</body>
</html>
