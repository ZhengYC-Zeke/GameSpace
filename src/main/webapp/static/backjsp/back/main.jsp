<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/default.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="${pageContext.request.contextPath}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">
    <script charset="utf-8" src="${pageContext.request.contextPath}/static/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/static/kindeditor/lang/zh-CN.js"></script>


    <style>
        .item label{
            display: inline-block;
            width: 280px;
            height: 30px;
            text-align: center;
            color: #fff;
            line-height: 30px;
            background-color: skyblue;
            border-radius: 5px;
            cursor: pointer;
        }
        .item input{
            margin-left: 300px;
            margin-top: -26px;
        }
    </style>
</head>
<body>

<div class="htmleaf-container" style="background-color: #1ABC9C">
    <center><h1 style='color:white'>GameBBS后台管理系统</h1></center>
    <div class="container pb30">
        <div class="clear-backend">
            <div class="avatar">
                <div>
                    <img src="${pageContext.request.contextPath}/static/backjsp/back/img/admin.jpg" alt="">
                </div>
            </div>

            <!-- tab-menu -->
            <input type="radio" class="tab-1" name="tab" checked="checked">
            <span>概况总览</span><i class="fa fa-home"></i>

            <input type="radio" class="tab-2" name="tab">
            <span>轮播图</span><i class="fa fa-photo"></i>

            <input type="radio" class="tab-3" name="tab">
            <span>游戏</span><i class="fa fa-gamepad"></i>

            <input type="radio" class="tab-4" name="tab">
            <span>类别</span><i class="fa fa-list-ul"></i>

            <input type="radio" class="tab-5" name="tab">
            <span>厂商</span><i class="fa fa-android"></i>

            <input type="radio" class="tab-6" name="tab">
            <span>文章</span><i class="fa fa-text-width"></i>

            <input type="radio" class="tab-7" name="tab">
            <span>用户</span><i class="fa fa-user"></i>

            <input type="radio" class="tab-8" name="tab">
            <span>评论</span><i class="fa fa-quote-right"></i>

            <input type="radio" class="tab-9" name="tab">
            <span>标签</span><i class="fa fa-tags"></i>

            <input type="radio" class="tab-10" name="tab">
            <span>评测</span><i class="fa fa-cog"></i>

            <!-- tab-top-bar -->
            <div class="top-bar">
                <ul>
                    <li>
                        <a href="" title="Log Out">
                            <span>安全退出&nbsp;&nbsp;&nbsp</span><i class="fa fa-sign-out"></i>
                        </a>
                    </li>
                    <li>
                        <a href="" title="Edit">
                            <span>消息推送&nbsp;&nbsp;&nbsp</span><i class="fa fa-edit"></i>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- tab-content -->
            <div class="tab-content">
                <section class="tab-item-1">
                    <%--首页--%>
                </section>
                <section class="tab-item-2">
                    <%--轮播图--%>
                </section>
                <section class="tab-item-3">
                    <%--游戏--%>
                </section>
                <section class="tab-item-4">
                    <%--类别--%>
                </section>
                <section class="tab-item-5">
                    <%--厂商--%>
                </section>
                <section class="tab-item-6">
                    <%--文章--%>
                </section>
                <section class="tab-item-7">
                    <%--用户--%>
                </section>
                <section class="tab-item-8">
                    <%--评论--%>
                </section>
                <section class="tab-item-9">
                    <%--链接--%>
                </section>
                <section class="tab-item-10">
                    <%--设置--%>
                </section>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
<script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jqgrid/js/ajaxfileupload.js"></script>
<script>

    $(function () {
        $('.tab-item-1').load('/game/static/backjsp/back/first.jsp');
    })

    $('.tab-2').click(function () {
        $('.tab-item-2').load('/game/static/backjsp/back/picture.jsp');
    })
    $('.tab-3').click(function () {
        $('.tab-item-3').load('/game/static/backjsp/back/game.jsp');
    })
    $('.tab-5').click(function () {
        $('.tab-item-5').load('/game/static/backjsp/back/factory.jsp');
    })
    $('.tab-4').click(function () {
        $('.tab-item-4').load('/game/static/backjsp/back/kind.jsp');
    })
    $('.tab-8').click(function () {
        $('.tab-item-8').load('/game/static/backjsp/back/comment.jsp');
    })
    $('.tab-9').click(function () {
        $('.tab-item-9').load('/game/static/backjsp/back/tag.jsp');
    })
    $('.tab-6').click(function () {
        $('.tab-item-6').load('/game/static/backjsp/back/article.jsp');
        //初始化kindEditor
        KindEditor.ready(function(K) {
            K.init({id:'#editor_id'});
            K.create('#editor_id');
        });
    })

</script>
</body>
</html>