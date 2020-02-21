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
    <!-- Font Awesome -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <<!-- General demo styles & header -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/explorer/css/demo.css" />
    <!-- Flickity gallery styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/explorer/css/flickity.css" />
    <!-- Component styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jsp/explorer/css/component.css" />
    <script src="${pageContext.request.contextPath}/static/jsp/explorer/js/modernizr.custom.js"></script>
</head>

<body>

<div id="top">

</div>

<div class="container-fluid" style="height: 500px;margin-top: -74px;background-image: url('${pageContext.request.contextPath}/static/jsp/explorer/ebj.jpg')">

</div>

<div class="container-fluid">
    <h2 style="color: white;margin-left: 300px">&nbsp;&nbsp;&nbsp;探险家 <small style="color: #2aabd2">根据您喜欢的类别为您推荐游戏</small></h2>
    <!-- Main view -->
    <hr>
<div class="view">
    <!-- Grid -->
    <section class="grid grid--loading" style="width: 100%">
        <!-- Loader -->
        <img class="grid__loader" src="${pageContext.request.contextPath}/static/jsp/explorer/images/grid.svg" alt="Loader image" />
        <!-- Grid sizer for a fluid Isotope (Masonry) layout -->
        <div class="grid__sizer"></div>
        <!-- Grid items -->
        <c:forEach items="${requestScope.map.games}" var="game">
        <div class='grid__item ${game.kind}'>
            <div class="slider">
                <div class="slider__item"><img src="${pageContext.request.contextPath}/static${game.listGamePath}" alt="Dummy" /></div>
                <div class="slider__item"><img src="${pageContext.request.contextPath}/static${game.listGamePath}" alt="Dummy" /></div>
                <div class="slider__item"><img src="${pageContext.request.contextPath}/static${game.listGamePath}" alt="Dummy" /></div>
            </div>
            <div class="meta">
                <h3 class="meta__title">${game.name}</h3>
                <span class="meta__brand">${game.factory}</span>
                <span class="meta__price">${game.score}</span>
            </div>
            <button class="action action--button action--buy"><a class="fa fa-search" href="${pageContext.request.contextPath}/game/selectGameById?id=${game.id}"></a></button>
        </div>
        </c:forEach>
    </section>

    <div style="margin-top: -100px;margin-left:-15px;margin-right:-15px;background-image: url('${pageContext.request.contextPath}/static/jsp/explorer/footer.jpg');height: 300px">

    </div>

    <!-- /grid-->
</div>
</div>

<!-- Bottom bar with filter and cart info -->
<div class="bar" >
    <div class="filter">
        <button class="action filter__item filter__item--selected" data-filter="*">All</button>
        <c:forEach items="${requestScope.map.favoriteKind}" var="kind">
        <button class="action filter__item" data-filter=".${kind}"><i class="icon icon--${kind}"></i><span class="action__text" style="color: #DD4F43">${kind}</span></button>
        </c:forEach>
        <c:forEach items="${requestScope.map.likeKind}" var="kind">
        <button class="action filter__item" data-filter=".${kind}"><i class="icon icon--${kind}"></i><span class="action__text">${kind}</span></button>
        </c:forEach>
        </div>
</div>

<div id="footer">

</div>

<div class="container-fluid" style="background-color: #122b40;height: 86px;">

</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/explorer/js/isotope.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/explorer/js/flickity.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jsp/explorer/js/main.js"></script>
<script>
    $('#top').load('${pageContext.request.contextPath}/static/jsp/top.jsp');
    $('#footer').load('footer.html');
</script>
</body>
</html>