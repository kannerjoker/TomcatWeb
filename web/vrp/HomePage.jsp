<%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/1
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>
    <h1>HomePage</h1>
    您好!
    <%!Cookie[] cookies;%>
    <%!Cookie cookie;%>
    <%
        cookies = request.getCookies();
        int count = 0;
        for (Cookie c : cookies) {
            count++;
            if("name".equals(c.getName())){
                cookie = c;
                break;
            }
            if(!"name".equals(c.getName())&&count==cookies.length){
                cookie = new Cookie("null","sorry");
            }
        }
    %>
    <%=
        cookie.getValue()
    %>!
</body>
</html>
