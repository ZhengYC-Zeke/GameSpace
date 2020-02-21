<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/comment/css/comment.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/review/css/component.css" />
    <script src="${pageContext.request.contextPath}/static/jsp/review/js/modernizr.custom.js"></script>
</head>
<body>
<input type="hidden" id="gameName" value="${requestScope.game.name}">
<div id="top">

</div>

<div class="container-fluid" style="background-color: #122b40;height: 60px;margin-top: -50px">

</div>

<div class="container-fluid" style="background-color: #122b40;">
    <div class="col-md-offset-2 col-md-10">
        <h5>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-tags" style="color: #a6e1ec">&nbsp;类别：</span>
            <a href="${pageContext.request.contextPath}/gamelist/kindShow?kind=${requestScope.game.kind}" style="color: yellow">${requestScope.game.kind}</a>
        </h5>
        <h5>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-home" style="color: #a6e1ec"> 厂商：</span>
            <a href="#" style="color: yellow">
                ${requestScope.game.factory}</a>
        </h5>
        <h3 style="color: #ffffff">
            &nbsp;&nbsp;${requestScope.game.name}
            <button class="btn btn-info btn-sm col-md-offset-7"><a href="${pageContext.request.contextPath}/firstPage/main" style="color: #ffffff">返回首页</a></button>
        </h3>
    </div>
</div>

<div class="container-fluid" style="background-color:#2b669a;height: 690px"><br>
    <div class="col-md-offset-2">
        <div class="row col-md-10">
                <div class="thumbnail"  style="background-color: black">
                    <video controlsautoplay controls="controls" src="${pageContext.request.contextPath}/static${requestScope.game.vedioPath}" style="height: 350px;width: 60%"></video>
                    <div class="col-md-offset-7 col-md-5" style="margin-top: -350px">
                        <div class="col-md-offset-1">
                            <img src="${pageContext.request.contextPath}/static${requestScope.game.showGamePath}" style="width: 100%"><hr>
                        </div>
                        <div class="list-group col-md-offset-1">
                            <h4 style="color: white">支持语言</h4>
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <td style="color: #a6e1ec">语言</td>
                                        <td style="color: #a6e1ec">界面</td>
                                        <td style="color: #a6e1ec">完全音频</td>
                                        <td style="color: #a6e1ec">字幕</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="color: #a6e1ec">简体中文</td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                </tr>
                                <tr>
                                    <td style="color: #a6e1ec">English</td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                    <td style="color: green"></td>
                                </tr>
                                <tr>
                                    <td style="color: #a6e1ec">繁體中文</td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                    <td style="color: green"></td>
                                    <td style="color: green"><span class="glyphicon glyphicon-ok"></span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                     <div class="caption">
                         <h3 style="color: white"><strong>${requestScope.game.name}</strong></h3>
                         <span class="glyphicon glyphicon-list-alt" style="color: #269abc"> 简介：</span>
                         <h5 style="color: #a6e1ec">${requestScope.game.introduce}</h5>
                         <span class="glyphicon glyphicon-thumbs-up"style="color: red"> 评分：</span>
                         <h4 style="color: white"><strong>${requestScope.game.score}/5.0</strong></h4>
                         <div class="progress">
                             <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${requestScope.game.score/5.0*100}%">
                                 <span class="sr-only">95% Complete (success)</span>
                             </div>
                         </div>
                         <p><a href="#" class="btn btn-warning" role="button">加入愿望单</a></p>
                    </div>
                </div>
            </div>
        </div>
</div>

<div class="container-fluid" style="background-color: #293652">

    <p><h3 style="margin-left: 200px;color: white"><span class="glyphicon glyphicon-th-list">&nbsp;用户评论</span></h3></p>
    <footer style="margin-left: 200px;color: white"><h4>最有价值的评测（过去三十天内）</h4></footer>
    <hr>

    <div class="jumbotron"style="margin-left: 200px;margin-right: 200px;background-color: #122b40">
        <div class="row">
            <div class="col-md-3">
                <a href="#" class="thumbnail" style="width: 200px">
                    <img src="${pageContext.request.contextPath}/static/jsp/thanks/images/tyc.jpg" alt="...">
                </a>
            </div>
            <img src="${pageContext.request.contextPath}/static/img/bad.png" alt="...">
            <hr>
            <h2 style="color: white"><strong>桃胤橙</strong></h2>
            <h5 style="color: white">云南 大理</h5>
            <h5 style="color: white">发表于2019-12-12</h5>
            <hr>
        </div>
        <p class="lead" style="color: white;margin-left: 240px">
            “什么时候大行动？”
            “别问，问就……卧槽！今天！？？”
        </p>
        <hr>
        <div class="col-md-offset-1">
            <h5 style="color: white">这篇评测是否有价值？</h5>
            <button type="button" class="btn btn-default btn-xs btn-success"><span class="glyphicon glyphicon-thumbs-up">&nbsp;有趣</span></button>
            <button type="button" class="btn btn-default btn-xs btn-danger"><span class="glyphicon glyphicon-thumbs-down">&nbsp;无聊</span></button>
            <button type="button" class="btn btn-default btn-xs btn-warning"><span class="glyphicon glyphicon-adjust">&nbsp;还行</span></button>
        </div>
    </div>

    <div class="jumbotron"style="margin-left: 200px;margin-right: 200px;background-color: #122b40">
        <div class="row">
            <div class="col-md-3">
                <a href="#" class="thumbnail" style="width: 200px">
                    <img src="${pageContext.request.contextPath}/static/jsp/thanks/images/lxy.jpg" alt="...">
                </a>
            </div>
            <img src="${pageContext.request.contextPath}/static/img/good.png" alt="...">
            <hr>
            <h2 style="color: white"><strong>李星宇</strong></h2>
            <h5 style="color: white">湖南 株洲</h5>
            <h5 style="color: white">发表于2019-12-15</h5>
            <hr>
        </div>
        <p class="lead" style="color: white;margin-left: 240px">
            两个号，玩了一共1000+小时，，说实话，真的受不了这种感觉，5个人之中有两个人开挂，或许开始的时候你根本看不出来，之后，你能慢慢搞懂拉枪，爆头线，当你发现你能单人破点的时候，你就能真正知道谁才是外挂，这些买挂的没有妈，不要和他们学，自己打自己的，多练枪才是硬道理。
            买挂防身？那都是放屁。
            最后关于开箱，真香，每次都觉得能出货，可偏偏不，就不给你出，嘻嘻嘻，顺便说一下，裂网大行动，氪金13轮，现在快1350星了
        </p>
        <hr>
        <div class="col-md-offset-1">
            <h5 style="color: white">这篇评测是否有价值？</h5>
            <button type="button" class="btn btn-default btn-xs btn-success"><span class="glyphicon glyphicon-thumbs-up">&nbsp;有趣</span></button>
            <button type="button" class="btn btn-default btn-xs btn-danger"><span class="glyphicon glyphicon-thumbs-down">&nbsp;无聊</span></button>
            <button type="button" class="btn btn-default btn-xs btn-warning"><span class="glyphicon glyphicon-adjust">&nbsp;还行</span></button>
        </div>
    </div>

    <hr>
</div>

<!--标签板-->
<div class="container-fluid" style="height: 10px;background-color:#0C1925">
    <h3 style="margin-left: 300px;color: white"><span class="glyphicon glyphicon-tasks">&nbsp;游戏标签板</span></h3>
    <br><h5 style="margin-left: 300px;color: white">此功能仍在测试阶段，如遇bug请及时反馈</h5>
</div>
<div class="container-fluid" style="background-image: url('${pageContext.request.contextPath}/static/jsp/comment/nbj.jpg');width: 100%">
    <div id="container" style="margin:100px 200px 30px 200px;">
    </div>
    <input id="input" type="text" placeholder=你有什么要表达的？...按回车发布" />
</div>
<div class="container-fluid" style="height: 10px;background-color:#0C1925">
</div>

<div class="container-fluid" style="background-color: #293652;height: 450px">
    <h3 style="margin-left: 300px;color: white"><span class="glyphicon glyphicon-th-list">&nbsp;媒体评测</span></h3>
    <br>
    <div class="container" style="background-color: #122b40;height: 332px">
        <div class="main">
            <div id="cbp-qtrotator" class="cbp-qtrotator">
                <div class="cbp-qtcontent">
                    <img src="${pageContext.request.contextPath}/static/jsp/review/images/1.jpg" alt="img01" />
                    <blockquote>
                        <p>People eat meat and think they will become .</p>
                        <footer>Pino Caruso</footer>
                    </blockquote>
                    <button class="btn btn-primary">查看详情</button>
                </div>
                <div class="cbp-qtcontent">
                    <img src="${pageContext.request.contextPath}/static/jsp/review/images/2.jpg" alt="img02" />
                    <blockquote>
                        <p>Nothing will benefit human health and increase.</p>
                        <footer>Albert Einstein</footer>
                    </blockquote>
                    <button class="btn btn-primary">查看详情</button>
                </div>
                <div class="cbp-qtcontent">
                    <img src="${pageContext.request.contextPath}/static/jsp/review/images/3.jpg" alt="img03" />
                    <blockquote>
                        <p>If you don't want to be beaten, imprisoned.</p>
                        <footer>Moby</footer>
                    </blockquote>
                    <button class="btn btn-primary">查看详情</button>
                </div>
                <div class="cbp-qtcontent">
                    <img src="${pageContext.request.contextPath}/static/jsp/review/images/4.jpg" alt="img04" />
                    <blockquote>
                        <p>My body will not be a tomb for other creatures.</p>
                        <footer>Leonardo Da Vinci</footer>
                    </blockquote>
                    <button class="btn btn-primary">查看详情</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid" style="height: 240px;background-color: black">
    <h3 class="text-center"><span style="color: #2aabd2">————————————————————————查看更多内容?  ————————————————————————</span></h3>
    <br>
    <h4 class="text-center" style="color: snow">登录查看更多推荐</h4>
    <div style="text-align:center">
        <br>
        <a href="#"><button class="btn btn-success">登录</button></a>
    </div>
    <br>
    <h4 class="text-center" style="color: snow">或者<a href="#" style="color: orangered">注册</a>并免费加入中文游戏网</h4>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/comment/js/comment.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/review/js/jquery.cbpQTRotator.min.js"></script>
<script>
    $('#top').load('${pageContext.request.contextPath}/static/jsp/top.jsp');
    $( function() {
        $( '#cbp-qtrotator' ).cbpQTRotator();
    } );
</script>
</body>
</html>