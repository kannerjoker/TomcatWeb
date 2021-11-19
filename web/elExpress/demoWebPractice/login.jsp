<%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/8
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="X-UA-Comparator" content="IE=edge">
<meta name="viewport" width="device-width" initial-scale="1">
<link rel="stylesheet" href="../../vrp/bootstrap-3.4.1-dist/css/bootstrap.min.css"/>
<head>
    <style type="text/css">
        .col-xs-3{
            margin-left: 38%;
        }
    </style>
</head>
<body>
    <div align="center">
        <h3>管理员登录</h3>
    </div>
    <br>
    <%--关联方法--%>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-3">
                <form method="post" action="">
                    <div class="form-group">
                        <label for="u">用户名:</label>
                        <input id="u" type="text" placeholder="请输入用户名" name="user" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="p">密码:</label>
                        <input id="p" type="text" placeholder="请输入密码" name="passwd" class="form-control">
                    </div>
                    <div class="form-inline">
                        <label for="code">验证码:</label>
                        <input id="code" type="text" placeholder="请输入验证码" name="code" class="form-control" style="width:135px">
                        <%--关联方法--%>
                        <img src="" alt="看不清点击刷新">
                    </div>
                    <div>
                        <br>
                        <br>
                        <br>
                        <div style="width:60px;margin-left:30%">
                            <button type="submit" class="btn-info form-control">登录</button>
                        </div>
                        <%--登录失败弹框,需校验logIn是否为true--%>
                        <c:if test="${!sessionScope.logIn&&sessionScope.logIn!=null}">
                            <div class="alert alert-warning alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span>x</span>
                                </button>
                                <strong>登录失败!</strong>
                            </div>
                        </c:if>
                        <div class="alert alert-warning alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>Warning!</strong> Better check yourself, you're not looking too good.
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>



    <script src="../../vrp/bootstrap-3.4.1-dist/js/jquery-3.2.1.min.js"></script>
    <script src="../../vrp/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
</body>
</html>
