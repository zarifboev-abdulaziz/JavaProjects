<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2022
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.RequestDispatcher" %>

<html>
<head>
    <title>OutPut</title>
</head>
<body>

<%!
public int sum(int a, int b){
    return a+b;
}
%>

<%
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("form.jsp");
    requestDispatcher.include(request, response);
%>

<h1 style="color: blue">Javob:

<%
    int aNum = Integer.parseInt(request.getParameter("aNum"));
    int bNum = Integer.parseInt(request.getParameter("bNum"));
    int sum = sum(aNum, bNum);
%>
    <%=
    sum
    %>
</h1>

</body>
</html>
