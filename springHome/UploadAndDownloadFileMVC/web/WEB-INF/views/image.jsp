<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/15/2022
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--&lt;%&ndash;<%@ taglib uri="C:\Users\User\Desktop\springHome\UploadAndDownloadFileMVC\src\main\resources\cad1-25-825x510.jpeg" prefix="portlet" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;<portlet:defineObjects />&ndash;%&gt;--%>
<%--src="<%= response.encodeURL(request.getContextPath())+"/"+users.getImage() %>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Image</title>
</head>
<body>

    <img src="data:image/png;base64, ${base64}" alt="Here should be image">

</body>
</html>
