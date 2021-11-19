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
    <meta charset="utf-8">
    <meta Http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>RegisterPage</title>
    <link rel="stylesheet" href="./bootstrap-3.4.1-dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container-fluid">
        <div class="col-xs-4">
            <form action="/local/verificationRegister" method="post">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="user">用户名:</label>
                            <input type="text" name="user" placeholder="" id="user" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="passwd">密码: </label>
                            <input type="text" name="passwd" placeholder="" id="passwd" class="form-control">
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-xs-5">
                        <label for="verification">验证码:</label>
                        <input type="text" name="verificationCode"  id="verification" class="form-control">
                    </div>
                    <img id="im" src="/local/verificationPicture" alt="picture">
                </div>
                <br>
                <div class="row">
                    <div class="col-xs-3 ">
                        <div class="form-group ">
                            <button type="submit" class="form-control btn-success">提交</button>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>

<script src="./bootstrap-3.4.1-dist/js/jquery-3.2.1.min.js"></script>
<script src="./bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
<script>
    var pic = document.getElementById("im");

    pic.onclick = function(){
        var num = new Date().getTime();
        pic.setAttribute("src","/local/verificationPicture?" + num);
    }


</script>
</body>
</html>
