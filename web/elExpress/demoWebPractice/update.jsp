<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/8
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta http-equiv="X-UA-Comparator" content="IE=edge">
<meta name="viewport" width="device-width" initial-scale="1">
<link rel="stylesheet" href="../../vrp/bootstrap-3.4.1-dist/css/bootstrap.min.css"/>
<html>
<head>
    <title>修改用户</title>
</head>
<body>
    <h3 class="text-center">修改联系人</h3>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-4" style="width:30%;margin-left:35%">
                <form method="post" action="/local/reDataServlet">
                    <div class="form-group">
                        <label for="user">姓名:</label>
                        <input id="user" type="text" placeholder="  请输入姓名" name="user" class="form-control" readonly >
                    </div>
                    <div class="form-group">
                        <label>性别:</label>
                        <input id="gen1" type="radio" name="gender" value="male" style="margin-left:5px" >&nbsp;男
                        <input id="gen2" type="radio" name="gender" value="female" style="margin-left:5px" >&nbsp;女
                    </div>
                    <div class="form-group">
                        <label for="age" >年龄:</label>
                        <input id="age" type="text" placeholder="  请输入年龄" name="age" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="birthplace">籍贯:</label>
                        <select id="birthplace" name="from" class="form-control">
                            <option value="0">---请选择城市---</option>
                            <option value="Zulpsx">Zulpsx</option>
                            <option value="Otrmsz">Otrmsz</option>
                            <option value="Hmotzm">Hmotzm</option>
                            <option value="Vcigdt">Vcigdt</option>
                            <option value="Ckbzzn">Ckbzzn</option>
                            <option value="Zpeczv">Zpeczv</option>
                            <option value="Qleijj">Qleijj</option>
                            <option value="Jmkvda">Jmkvda</option>
                            <option value="Rkabnw">Rkabnw</option>
                            <option value="Bulzbo">Bulzbo</option>
                        </select>

                    </div>
                    <div class="form-group">
                        <label for="qq">QQ:</label>
                        <input id="qq" type="text" placeholder="  请输入QQ号码" name="qq" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input id="email" type="email" placeholder="  请输入Email地址" name="email" class="form-control">
                    </div>
                    <div class="form-group">
                            <div class="container-fluid" >
                                <div class="col-xs-2" style="margin:10px 2px 0px 20%;padding:0">
                                    <button type="submit" class="btn-info form-control">提交</button>
                                </div>
                                <div class="col-xs-2" style="margin:10px 2px 0px 2px;padding:0">
                                    <button type="button" class="btn-default form-control">
                                        <a id="re" style="text-decoration: none">重置</a>
                                    </button>
                                </div>
                                <div class="col-xs-2" style="margin:10px 2px 0px 2px;padding:0">
                                    <button id="goBack" type="button" class="btn-default form-control" >
                                        <a style="text-decoration: none" href="./list.jsp">返回</a>
                                    </button>
                                </div>
                            </div>

                    </div>

                </form>
            </div>
        </div>
    </div>


    <script src="../../vrp/bootstrap-3.4.1-dist/js/jquery-3.2.1.min.js"></script>
    <script src="../../vrp/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <script>

        var user = document.getElementById("user");
        var gen1 = document.getElementById("gen1");
        var gen2 = document.getElementById("gen2");
        var age = document.getElementById("age");
        var birth = document.getElementById("birthplace");
        var qq = document.getElementById("qq");
        var email = document.getElementById("email");

        //name
        user.setAttribute("value","${cookie.name.value}");
        window.onload = function(){
            //gender
            console.log("gender-------->");
            if("male"== "${cookie.gender.value}"){
                gen1.setAttribute("checked","checked");
            }else{
                gen2.setAttribute("checked","checked");
            }

            //birth
            console.log("birth-------->");
            birth.options[0].removeAttribute("selected");
            for(var i=0;i<birth.options.length;i++){
                var selectedValue = birth.options[i].innerHTML;
                console.log(selectedValue);
                if(selectedValue=="${cookie.birth.value}"){
                    birth.options[i].setAttribute("selected","selected");
                    break;
                }
            }
        }

        //age
        age.setAttribute("value","${cookie.age.value}");

        //qq
        qq.setAttribute("value",${cookie.qq.value});

        //email
        <%
            String utf8 ;
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if("email".equals(c.getName())){
                    utf8 = URLDecoder.decode(c.getValue(),"utf8");
                    application.setAttribute("email",utf8);
                    System.out.println("utg8: " + utf8);
                    break;
                }
            }

        %>
        email.setAttribute("value","${applicationScope.email}");

        //clear msg
        var cleaerMsg = document.getElementById("re");
        cleaerMsg.onclick = function(){
            gen1.removeAttribute("checked");
            gen2.removeAttribute("checked");
            age.removeAttribute("value");
            for(var i=0;i<birth.options.length;i++){
                birth.options[i].removeAttribute("selected");
            }
            birth.options[0].setAttribute("selected","");
            qq.removeAttribute("value");
            email.removeAttribute("value");
        }
    </script>
</body>
</html>


