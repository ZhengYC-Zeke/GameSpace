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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/gamelist/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/gamelist/css/component.css" />
    <script src="${pageContext.request.contextPath}/static/jsp/gamelist/js/modernizr.custom.js"></script>
</head>

<body>

<div id="top">

</div>

<div class="container-fluid" style="background-color: #214B6D">
    <div class="col-md-offset-2 col-md-8">
        <div style="height: 12px"></div>
        <div>
            <h2 style="color: white">&nbsp;&nbsp;&nbsp;${requestScope.map.title} <small style="color: #2aabd2">${requestScope.map.content}</small></h2>
        </div>

        <hr>
        <section class="grid-wrap">
            <ul class="grid swipe-rotate" id="grid">
                <c:forEach items="${requestScope.map.games}" var="game">
                <li><a href="${pageContext.request.contextPath}/game/selectGameById?id=${game.id}"><img src="${pageContext.request.contextPath}/static${game.cutGamePath1}" alt="dummy" style="width: 100%"><h3>${game.name}</h3></a></li>
                </c:forEach>
            </ul>
        </section>
        <hr>
    </div>
</div>

<div id="footer">

</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/gamelist/js/masonry.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/gamelist/js/imagesloaded.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/gamelist/js/classie.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/gamelist/js/colorfinder-1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/gamelist/js/gridScrollFx.js"></script>
<script>
    $('#top').load('${pageContext.request.contextPath}/static/jsp/top.jsp');
    $('#footer').load('${pageContext.request.contextPath}/static/jsp/footer.html');
    new GridScrollFx( document.getElementById( 'grid' ), {
        viewportFactor : 0.4
    } );
</script>
</body>
</html>
