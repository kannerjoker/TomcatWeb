<%@ page import="elExpress.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/8
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        List<UserInfo> list = new ArrayList();
        UserInfo user;
        user = new UserInfo();
        user.setName("zhangsan");
        user.setNum(1);
        list.add(user);

        user = new UserInfo();
        user.setName("lisi");
        user.setNum(2);
        list.add(user);
        request.setAttribute("users",list);
    %>

    <table border="1">
        <tr>
            <th>UserName</th>
            <th>Num</th>
        </tr>
        <c:forEach var="u" items="${requestScope.users}">
            <tr>
                <td>${u.getName()}</td>
                <td>${u.getNum()}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
