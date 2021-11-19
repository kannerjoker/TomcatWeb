<%@ page import="javafx.application.Application" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/3
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ElExpress</title>
</head>
<body>
<%--
一、
    1.使用${express}来调用el表达式
    2.使用[]或者.来引用数据,其中数组列表只能用[]
    3.paramValues必须按照parmValues.key[offsetNum]格式,如paramValues.affect[2]
--%>
    <%="数据引用--------->"%><br>
    <%
        String[] arr = {"",null,"a3"};
        request.setAttribute("user",arr);
    %>
    <%
        String[] str1 = (String[])request.getAttribute("user");
        for (int i = 0; i < str1.length; i++) {
            request.setAttribute("rt",i);

    %>
            arr<%=i + ": "%>    ${user[rt]} -------- ${rt}<br>
    <%}%>
    <br>
<%--
二、
    使用empty关键字来判断""或者null
--%>
    <%="empty--------->"%><br>
    ${empty user[0]}<br>
    ${empty user[1]}<br>
    ${empty user[2]}<br>
    <br>
<%--
三、
    使用三目表达式来进行条件判断
--%>
    <%="三目表达式--------->"%><br>
    ${1==1?"right":"false"}<br>
    <br>
    <%
        request.getSession();
    %>
<%--
四、
    JSP隐藏对象（共11个，如:）
--%>
    ${pageContext.request}<br>
        ${pageContext.request.serverPort}<br>
        ${pageContext.request.serverName}<br>
        ${pageContext.request.servletContext}<br>
        ${pageContext.request.session}<br>
        ${pageContext.request.cookies}<br>
        <br>
    ${pageContext.response}<br>
    ${pageContext.out}<br>
    ${pageContext.session}<br>
    ${pageContext.exception}<br>
    ${pageContext.page}<br>
    ${pageContext.servletContext}<br>
    <br>
<!--
五、访问作用于隐藏对象
-->
<%="pageScope--------->"%><br>
<jsp:useBean id="user" scope="page" class="elExpress.UserInfo" type="elExpress.UserInfo"></jsp:useBean>
<jsp:setProperty property="name" name="user" value="false"></jsp:setProperty>
${pageScope.user.name}<br>

<jsp:useBean id="u" scope="page" class="elExpress.UserInfo" type="elExpress.UserInfo"/>
<jsp:setProperty property="name" name="u" value="true"/>
${pageScope.u.name}<br>
<%="requestScope------->"%><br>
<%
    request.setAttribute("re","requestScope");
    request.getSession().setAttribute("se","sessionScope");
    application.setAttribute("app","applicationScope");
%>

${requestScope.re}<br>
${sessionScope.se}<br>
${applicationScope.app}<br>
<br>

<%="param--------->"%><br>
<form action="" method="get" name="form1"  >
    <input type="checkbox"name="affect" id="a1" value="1">苹果
    <input type="checkbox"name="affect" id="a2" value="2">香蕉
    <input type="checkbox"name="affect" id="a3" value="3">橘子
    <input type="checkbox"name="affect" id="a4" value="4">梨
    <input type="submit">
</form>
<%--若有多个值,则只返回第一个--%>
${param.affect}<br>
<%--paramValues必须按照parmValues.key[offsetNum]格式,如paramValues.affect[2]--%>
${paramValues.affect[0]}<br>
${paramValues.affect[1]}<br>
${paramValues.affect[2]}<br>
${paramValues.affect[3]}<br>
<br>

<%="header------------>"%><br>
${header["Cookie"]}<br>
<%--${headerValues["Cookie"]}--%>
<br>

<%="initParam------------->"%><br>
${initParam.wangwu}<br>
<br>

<%="cookie----------->"%><br>
<%
    Cookie coo = new Cookie("msi","edg");
    response.addCookie(coo);
%>
${cookie.msi.value}<br>
<br>
</body>
</html>