<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/15/2022
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/savefile" method="post" enctype="multipart/form-data">
    Select File: <input type="file" name="file"/>
    <input type="submit" value="Upload File"/>
</form>



</body>
</html>
