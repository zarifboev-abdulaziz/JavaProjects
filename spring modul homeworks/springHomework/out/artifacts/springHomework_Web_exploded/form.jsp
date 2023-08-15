<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2022
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>HOME</title>
</head>
<body>

<h1>Ikkita sonni kiriting:</h1><br>

<form action="logic.jsp" method="post">

    <label for="sonA">A number</label><br>
    <input type="text" id="sonA" name="aNum"><br>

    <label for="sonB">B number</label><br>
    <input type="text" id="sonB" name="bNum"><br>

    <button type="submit">Submit</button>


</form>

</body>
</html>
