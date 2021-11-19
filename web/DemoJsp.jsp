<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.beans.Encoder" %>
<%@ page import="sun.awt.AWTCharset" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/10/25
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>visitWeb</title>
</head>
<body>
    <%
        response.setHeader("content-type","text/html;charset=utf-8");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vt = sdf.format(date);
        Cookie cookie = new Cookie("visitTime", URLEncoder.encode(vt,"utf-8"));
        cookie.setMaxAge(5);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        String para = request.getParameter("visitTime");
        int count = 0;
        for (Cookie c : cookies) {
            if("visitTime".equals(c.getName())){
    %>
                <h2>欢迎回来，您上次访问时间为:&nbsp;
                    <%= URLDecoder.decode(c.getValue(),"utf-8") %>
                </h2>
    <%
                break;
            }
            if(count==cookies.length-1 && !"visitTime".equals(c.getName())){
    %>
                <h2>您好，欢迎您首次访问...</h2>
    <%
            }
            count++;
        }
    %>
</body>
</html>
