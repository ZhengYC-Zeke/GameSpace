<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--    设置模态框点击后浮层在最上面--%>
<style type="text/css">
    .modal-backdrop{z-index:0;}
</style>

<nav class="navbar navbar-inverse navbar-fixed-top" style="height: 100px">

    <div class="container">
        <div class="navbar-header">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        <img alt="Brand" src="${pageContext.request.contextPath}/static/img/游戏.png">
                    </a>
                </div>
            </div>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <div class="col-md-4">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/firstPage/main"><h4>首页</h4></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><h4>论坛<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/gamelist/buttonClick?buttonName=CHOICENESS">每日精选</a></li>
                            <li><a href="${pageContext.request.contextPath}/gamelist/explorer">探险家</a></li>
                            <li><a href="#">热门文章</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">愿望单</a></li>
                        </ul>
                    </li>
                    <li><a href="#"><h4>关于</h4></a></li>
                    <!-- Button trigger modal -->
                    <li><a class="btn" data-toggle="modal" data-target="#myModal"><h4>客服</h4></a></li>
                    <!-- Modal -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">GameSpace客服</h4>
                                </div>
                                <div class="modal-body">
                                    <label for="answer">常见问题（鼠标悬浮至问题上方查看）：</label><br>
                                    <br><a title="刷新网页重试或检查您的网络连接">网页无法打开</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a title="点击个人中心 -> 忘记密码">忘记密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a title="点击探险家选择标签即可获取我们为您量身推荐的游戏">找不到喜欢的游戏</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a title="点击个人中心查看用户信息">账户相关</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <p id="answer" class="hidden"></p>
                                    <hr>
                                    <img src="${pageContext.request.contextPath}/static/img/robot.jpg" class="img-circle" style="width: 50px;height: 50px;margin-bottom: 13px">
                                    <label for="question">&nbsp;&nbsp;无法解决？请输入您的问题：</label>
                                    <textarea class="form-control" id="question" style="height: 74px" maxlength="100" placeholder="您最多可输入100字..."></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </ul>
                </ul>
            </div>

            <form class="navbar-form navbar-left">
                <a href=""><h4 style="color: #2aabd2">没有账号？立即注册</h4></a>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名">
                    <input type="password" class="form-control" placeholder="密码">
                    <input type="submit" class="btn btn-success" value="登录">
                </div>

            </form>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><h4>选择语言<span class="caret"></span></h4></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">简体中文</a></li>
                        <li><a href="#">繁體中文</a></li>
                        <li><a href="#">English</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container" style="width:100%;height:150px;background-color: #214B6D">
    <div style="height: 150px"></div>
    <div class="btn-group col-md-offset-2 col-md-5">
        <div class="btn-group btn-group-lg">
            <button id="shop" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                您的论坛 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="shop">
                <li><a href="#">论坛首页</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">最近浏览</a></li>
                <li><a href="#">个人中心</a></li>
            </ul>
        </div>
        <div class="btn-group btn-group-lg">
            <button type="button" id="game" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                游戏 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="game">
                <li><a href="#">在线游玩</a></li>
                <li><a href="${pageContext.request.contextPath}/gamelist/buttonClick?buttonName=UPDATE">最近更新</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">按类别浏览</li>
                <li><a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=策略战棋">策略战旗</a></li>
                <li><a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=角色扮演">角色扮演</a></li>
                <li><a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=多人在线">多人在线</a></li>
                <li><a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=体育赛车">体育赛车</a></li>
                <li><a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=动作射击">动作射击</a></li>
                <li><a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=独立游戏">独立游戏</a></li>
            </ul>
        </div>
        <div class="btn-group btn-group-lg">
            <button id="software" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                移动中心 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="software">
                <li><a href="#">手机游戏</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">按类别浏览</li>
                <li><a href="#">策略</a></li>
                <li><a href="#">赛车</a></li>
                <li><a href="#">传奇</a></li>
                <li><a href="#">休闲</a></li>
                <li><a href="#">动作</a></li>
            </ul>
        </div>
        <div class="btn-group btn-group-lg">
            <button id="company" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                游戏厂商 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="company">
                <li><a href="#">腾讯</a></li>
                <li><a href="#">网易</a></li>
                <li><a href="#">搜狐</a></li>
                <li><a href="#">百度</a></li>
                <li><a href="#">微软</a></li>
            </ul>
        </div>
        <div class="btn btn-lg btn-danger"><a href="#" style="color: #ffffff">新闻</a></div>
    </div>
    <div class="col-md-3">
        <form class="form-inline">
            <div class="form-group">
                <input type="text" class="form-control input-lg" placeholder="荒野大镖客">
            </div>
            <button type="submit" class="btn btn-lg btn-success"><span class="glyphicon glyphicon-search"></span></button>
        </form>
    </div>
</div>
