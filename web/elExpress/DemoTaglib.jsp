<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/5
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Taglib</title>
</head>
<body>
    <jsp:useBean id="ur" class="elExpress.UserInfo" type="elExpress.UserInfo"></jsp:useBean>
    <jsp:setProperty name="ur" property="name" value="zhaoliu"></jsp:setProperty>
    <jsp:setProperty name="ur" property="num"  value="6"></jsp:setProperty>

    ${pageScope.ur.name}<br>
    ${pageScope.ur.num}<br>
    <br>
    <c:if test="${pageScope.ur.name} == 'zhaoliu'">
        ${pageScope.ur.name}&nbsp;&nbsp;${pageScope.ur.num}
    </c:if>

    <%! int[] arr = {1,2,3,4,5,6};%>
    <%
        request.setAttribute("arr",arr);
    %>
    <c:forEach var="ar" items="${requestScope.arr}" begin="0" end="6" step="1"  varStatus="index">
        <c:out value="${requestScope.arr[ar-1]}"></c:out>
    </c:forEach>

    <%! int n; %>
    <%
        n = new Random().nextInt(6)+1;
        request.setAttribute("n",n);
    %>
    <br>
    -------><c:out value="${requestScope.n}"></c:out><--------
    <br>
    <c:choose>
        <c:when test="${requestScope.n == 1}">choose1<br></c:when>
        <c:when test="${requestScope.n == 2}">choose2<br></c:when>
        <c:when test="${requestScope.n == 3}">choose3<br></c:when>
        <c:when test="${requestScope.n == 4}">choose4<br></c:when>
        <c:when test="${requestScope.n == 5}">choose5<br></c:when>
        <c:otherwise>choose<br></c:otherwise>
    </c:choose>
    <c:if test="${requestScope.n == 2}">
        <h1>if</h1>
    </c:if>

</body>
</html>
