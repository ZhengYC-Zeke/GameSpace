<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>欢迎来到 中文游戏网</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/jsp/kind/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/jsp/kindlist/css/kindlist.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jsp/kind/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jsp/kind/js/current.js"></script>
</head>

<div id="top">

</div>

<div class="container-fluid" style="height: 800px;margin-top: -74px;background-image: url('${pageContext.request.contextPath}/static/jsp/kind/kbj.jpg')">

</div>

<div class="container-fluid" style="margin-top: -350px;margin-bottom: 35px">
    <h2 style="color: white;margin-left: 250px">&nbsp;&nbsp;&nbsp;游戏分类&nbsp;&nbsp;<small style="color: pink">根据类别，评分，厂商选择游戏</small></h2>
    <br>
    <div class="cation-content">
    <div class="cation-middle">
        <dl class="cation-list">
            <dt>分类</dt>
            <dd>
                <a href="" rel="all" name="kind" class="all on">全部</a>
                <a href="#" rel="角色扮演" name="kind" class="default">角色扮演</a>
                <a href="#" rel="策略战棋" name="kind" class="default">策略战棋</a>
                <a href="#" rel="格斗游戏" name="kind" class="default">格斗游戏</a>
                <a href="#" rel="故事剧情" name="kind" class="default">故事剧情</a>
                <a href="#" rel="冒险益智" name="kind" class="default">冒险益智</a>
                <a href="#" rel="恋爱养成" name="kind" class="default">恋爱养成</a>
                <a href="#" rel="塔防卡牌" name="kind" class="default">塔防卡牌</a>
                <a href="#" rel="动作射击" name="kind" class="default">动作射击</a>
                <a href="#" rel="模拟经营" name="kind" class="default">模拟经营</a>
                <a href="#" rel="多人在线" name="kind" class="default">多人在线</a>
                <a href="#" rel="音乐舞蹈" name="kind" class="default">音乐舞蹈</a>
                <a href="#" rel="体育赛车" name="kind" class="default">体育赛车</a>
                <a href="#" rel="独立游戏" name="kind" class="default">独立游戏</a>
            </dd>
        </dl>
        <dl class="cation-list">
            <dt>评分</dt>
            <dd>
                <a href="#" rel="all" name="score" class="all on">全部</a>
                <a href="#" rel="1" name="score" class="default">0.0-1.0</a>
                <a href="#" rel="2" name="score" class="default">1.0-2.0</a>
                <a href="#" rel="3" name="score" class="default">2.0-3.0</a>
                <a href="#" rel="4" name="score" class="default">3.0-4.0</a>
                <a href="#" rel="5" name="score" class="default">4.0-5.0</a>
            </dd>
        </dl>
        <dl class="cation-list">
            <dt>厂商</dt>
            <dd>
                <a href="#" rel="all" name="factory" class="all on">全部</a>
                <a href="#" rel="ROIT（拳头）" name="factory" class="default">ROIT（拳头）</a>
                <a href="#" rel="Ubisoft Entertainment" name="factory" class="default">Ubisoft Entertainment</a>
                <a href="#" rel="No Brakes Games" name="factory" class="default">No Brakes Games</a>
                <a href="#" rel="Rockstar Games" name="factory" class="default">Rockstar Games</a>
                <a href="#" rel="Origin" name="factory" class="default">Origin</a>
                <a href="#" rel="暴雪娱乐" name="factory" class="default">暴雪娱乐</a>
            </dd>
        </dl>
    </div>
</div>
</div>

<div class="container-fluid" style="background-color: #122b40">
    <div class="htmleaf-container">
        <div class="container">
            <div class="btn-wrapper">
                <strong style="color: white">显示为：</strong>
                <div class="btn-group">
                    <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
		            </span>列表布局</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                        class="glyphicon glyphicon-th"></span>网格布局</a>
                </div>
            </div>

            <div id="products" class="row list-group">

                <c:forEach items="${requestScope.map.games}" var="game">
                <div class="item  col-xs-4 col-lg-4">
                    <div class="thumbnail">
                        <img class="group list-group-image" src="${pageContext.request.contextPath}/static${game.recommendedGamePath}"/>
                        <div class="caption">
                            <h4 class="group inner list-group-item-heading" style="margin-top: 10px">
                                <strong style="color:black">${game.name}</strong></h4>
                            <p class="group inner list-group-item-text" style="color:orangered;margin-top: 10px">
                                   简介：${fn:substring(game.introduce, 0, 50)}……</p>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <p class="lead" style="margin-bottom:0;margin-top:10px;font-size: 14px;color:purple">
                                            评分：${game.score}</p>
                                </div>
                                <div class="col-xs-12 col-md-2 col-md-offset-4">
                                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/game/selectGameById?id="${game.id}>查看详情</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>

            </div>
        </div>
        <div class="text-center" style="margin-top: -15px;margin-bottom: 15px">
            <c:if test="${requestScope.map.page==1}">
                <a class="btn btn-danger">已经是第一页啦</a>
            </c:if>
            <c:if test="${requestScope.map.page>1}">
                <a class="btn btn-primary">上一页</a>
            </c:if>
            <strong style="color: #5C5EDC">&nbsp;&nbsp;第${requestScope.map.page}页&nbsp;/&nbsp;共${requestScope.map.sum}页&nbsp;&nbsp;</strong>
            <c:if test="${requestScope.map.page==requestScope.map.sum}">
                <a class="btn btn-danger">已经是最后一页啦</a>
            </c:if>
            <c:if test="${requestScope.map.page < requestScope.map.sum}">
                <a class="btn btn-primary">下一页</a>
            </c:if>
        </div>
    </div>
</div>

<div id="footer">

</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('#top').load('${pageContext.request.contextPath}/static/jsp/top.jsp');
        $('#footer').load('${pageContext.request.contextPath}/static/jsp/footer.html');
        new SelectTag({
            child : ".default", //所有默认
            over : 'on', //当前选中
            all : ".all" // 默认全部
        });
        $(document).ready(function() {
            $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
            $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
        });
    })
</script>
</body>
</html>