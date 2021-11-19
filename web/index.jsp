<%@ page import="static jdk.nashorn.internal.objects.Global.print" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/9/2
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <meta charset="utf-8">
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h1>
        index jsp 文件
    </h1>
  $END$
    <%
      String str;
      application.setAttribute("s","123456");
    %>
    ${applicationScope.s}
  </body>
</html>
