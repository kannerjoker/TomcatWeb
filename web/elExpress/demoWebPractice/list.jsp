<%@ page import="elExpress.test.PrePareTestSource" %>
<%@ page import="elExpress.demoWebPractice.GetData" %>
<%@ page import="elExpress.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: kan
  Date: 2021/11/8
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="X-UA-Comparator" content="IE=edge">
<meta name="viewport" width="device-width" initial-scale="1">
<link rel="stylesheet" href="../../vrp/bootstrap-3.4.1-dist/css/bootstrap.min.css">
<html>
<head>
    <title>用户信息</title>
    <style type="text/css">
        th{
            /*align:center;*/
            text-align:center;
            /*margin: auto;*/
        }
        td{
            display: table-cell;
            vertical-align: middle;
            text-align: center;

        }
    </style>
</head>
<body>
    <div><h3 class="text-center" style="font-size: 32px;">用户信息列表</h3></div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <form method="post" action="/local/searchServlet" style="width:80%;margin-left:10%">
                    <span style="float:left;">
                        <div class="form-group form-inline">
                            <label for="sUser">姓名</label>
                            <input id="sUser" type="text" class="form-control" name="searchUser" placeholder="${applicationScope.user}">
                        </div>
                     </span>
                    <span style="float:left;margin-left:20px">
                        <div class="form-group form-inline">
                            <label for="sBirth">籍贯</label>
                            <input id="sBirth" type="text" class="form-control" name="searchBirth" placeholder="${applicationScope.birth}">
                        </div>
                    </span>
                    <span style="float:left;margin-left:20px">
                        <div class="form-group form-inline">
                            <label for="sMail">邮箱</label>
                            <input id="sMail" type="text" class="form-control" name="searchEmail" placeholder="${applicationScope.email}">
                        </div>
                     </span>
                    <span style="float:left;margin-left:10px">
                        <input id="sBtn" type="submit" value="查询" class="btn btn-default">
                    </span>
                    <span style="float:right">
                        <a class="btn btn-primary" href="./addUser.jsp">添加联系人</a>
                        <a class="btn btn-primary" onclick="delSelChecked(this)"> 删除选中</a>
                    </span>
                </form>


                <table class="table table-bordered" style="width:80%;margin-left:10%">
                    <tr class="success">
                        <th>全选&nbsp;<input type="checkbox" name="selAll" onclick="selectAll(this)"></th>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>籍贯</th>
                        <th>QQ</th>
                        <th>邮箱</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach var="u" items="${sessionScope.userList}">
                        <tr>
                            <td><input class="check-box" type="checkbox" name="ch" value="${u[0]}" onclick="selectOne(this)"></td>
                            <td class="rowNum" datavalue="${u[0]}"></td>
                            <td>${u[1]}</td>
                            <td>${u[2]}</td>
                            <td>${u[3]}</td>
                            <td>${u[4]}</td>
                            <td>${u[5]}</td>
                            <td>${u[6]}</td>
                            <td>
                                <div class="form-group" style="margin-left:30%">
                                    <button class="btn-default form-control " style="width:60px;float:left">
                                        <a class="up" style="text-decoration:none;color:black" onclick="getUpdatePage(this)">修改</a>
                                    </button>
                                    <button class="btn-default form-control" style="width:60px;float:left;margin-left:2%">
                                        <a class="de" href="" style="text-decoration: none;color:black" onclick="del(this)">删除</a>
                                    </button>

                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <span id="foot" style="width:80%;margin-left:10%">
                    <a class="btn btn-default labelBtn" href="" onclick="setLabelL(this)"><span class="text-primary">>></span></a>
                    <c:forEach var="label" items="${sessionScope.labelList}">
                        <a class="btn btn-default labelBtn" onclick="setLabel(this)" focus="0"><span class="text-primary">${label}</span></a>
                    </c:forEach>
                    <a class="btn btn-default labelBtn" href="" onclick="setLabelR(this)"><span class="text-primary"><<</span></a>
                    <span style="font-size:20px;font-weight: normal">共${sessionScope.userListSize}条记录,共${sessionScope.labelList.size()}页</span>
                </span>
            </div>
        </div>
    </div>


    <script src="../../vrp/bootstrap-3.4.1-dist/js/jquery-3.2.1.min.js"></script>
    <script src="../../vrp/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <script>
        // 页面刷新时,设置选项
        var labelBtns =  document.getElementsByClassName("labelBtn");
        window.onload = function(){
            // 选中效果
            // 从session获取选中标签
            var label = "${sessionScope.label}";
            if((label==null||label=="")&&labelBtns.length>2){
                labelBtns[1].setAttribute("focus","1");
                labelBtns[1].setAttribute("style","background-color:#286090;");
            }else if(labelBtns.length==2){
                labelBtns[0].setAttribute("style","background-color:white;");
                labelBtns[1].setAttribute("style","background-color:white;");
            } else{
                labelBtns[label].setAttribute("focus","1");
                labelBtns[label].setAttribute("style","background-color:#286090;");
            }
            //绑定servlet
            var selectedBtn;
            for(var i=0;i<labelBtns.length;i++){
                selectedBtn = labelBtns[i];
                if(selectedBtn.getAttribute("focus")=="1"){
                    selectedBtn.setAttribute("href","/local/listLabelServlet?label=" + selectedBtn.innerText);
                    console.log("selectedBtn.innerText: " + selectedBtn.innerText);
                }
            }

            //设置编号/行号
            var rowNum = document.getElementsByClassName("rowNum");
            var len = parseInt(${sessionScope.userListSize});
            var formSize = parseInt(${sessionScope.formSize});
            var label = "${sessionScope.label}";

            console.log("label:----> " + typeof(label) );
            for(var i=0;i<len;i++){
                for(var j=0;j<rowNum.length;j++){
                    if(label==""||label==null){
                        rowNum[j].innerHTML = 0*formSize + (j+1);
                    }else{
                        rowNum[j].innerHTML = (window.parseInt(label)-1)*(window.parseInt(formSize)) + (j+1);
                    }
                }

            }
        }

        //修改页面
        getUpdatePage = function(obj){
            var str = obj.parentElement.parentElement.parentElement.parentElement.children[1].innerHTML;
            obj.href = "/local/updateServlet?line=" + str;
        }
        //删除页面
        del = function(obj){
            var text = obj.parentElement.parentElement.parentElement.parentElement.children[2].innerHTML;
            obj.setAttribute("href","/local/deleteServlet?del=" + text);
        }
        //批量删除
        //全选
        selectAll = function(obj){
            var eleChecked = document.getElementsByClassName("check-box");
            if(obj.checked == true){
                for(var i=0;i<eleChecked.length;i++){
                    eleChecked[i].checked = true;
                }
            }else{
                for(var i=0;i<eleChecked.length;i++){
                    eleChecked[i].checked = false;
                }
            }
        }
        selectOne =function(obj){
            var checkAll = document.getElementsByName("selAll")[0];
            var checkList = document.getElementsByClassName("check-box");
            var count = 0;
            for(var i=0;i<checkList.length;i++){
                if(checkList[i].checked == true){
                    count++;
                }
            }
            if(count==checkList.length){
                checkAll.checked = true;
            }else{
                checkAll.checked = false;
            }
        }

        //设置lable左右键切换
        setLabelL = function(obj){
            var label = "${sessionScope.label}";
            // 直接通过按钮传参,当前选中+1,最后一个则返回第一个
            // 初始加载,还没给label赋值,此时label为null
            if((label==""||label==null)&&${sessionScope.labelList.size()>=2}){
                obj.setAttribute("href","/local/listLabelServlet?label=2");
            }else if(label=="${sessionScope.labelList.size()}"){             //最后一个
                obj.setAttribute("href","/local/listLabelServlet?label=1");
            }else{                                                          //通常情况
                obj.setAttribute("href","/local/listLabelServlet?label=" + (window.parseInt(label)+1));
            }
        }
        setLabelR = function(obj){
            var label = "${sessionScope.label}";
            // 直接通过按钮传参,当前选中-1,第一个则返回最后一个
            // 初始加载,还没给label赋值,此时label为null
            if(label==""&&${sessionScope.labelList.size()>=2}){
                obj.setAttribute("href","/local/listLabelServlet?label=1");
            }else if(label==1){             //第一个
                obj.setAttribute("href","/local/listLabelServlet?label=" + ${sessionScope.labelList.size()});
            }else{                                                          //通常情况
                obj.setAttribute("href","/local/listLabelServlet?label=" + (label-1));
            }
        }


        //设置数字label后,改变背景色和向servlet传递当前标签号
        setLabel = function(obj){
            labelBtns =  document.getElementsByClassName("labelBtn");
            //标记label选中效果
            for(var i=1;i<labelBtns.length-1;i++){
                labelBtns[i].setAttribute("focus","0");
            }
            obj.setAttribute("focus","1");
            judgeFocus();   //背景色判定
            //绑定servlet
            obj.setAttribute("href","/local/listLabelServlet?label=" + obj.innerText);
        }

        //通过focus属性来设置背景色
        var judgeFocus = function(){
            for(var i=0;i<labelBtns.length;i++){
                var isFoucus = labelBtns[i].getAttribute("focus");
                if(isFoucus=="1"){
                    labelBtns[i].setAttribute("style","background-color:#286090;");
                }else{
                    labelBtns[i].setAttribute("style","background-color:white;");
                }
            }
        }
        console.log("label: ${sessionScope.label}");
        delSelChecked = function(obj){
            var checkedList = document.getElementsByName("ch");
            var strList = new Array();
            var str;
            for(var i=0;i<checkedList.length;i++){
                if(checkedList[i].checked){
                    strList.push(checkedList[i].getAttribute("value"));
                }
            }

            str = strList.join(",");
            obj.setAttribute("href","/local/deleteSelectedServlet?str=" + str);
        }


    </script>
</body>
</html>
