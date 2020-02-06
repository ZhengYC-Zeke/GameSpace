<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 style="margin-top: -8px;margin-left: 5px"><strong>一周相关数据统计</strong></h3>
<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-info btn-lg">最受欢迎</button>
    <button type="button" class="btn btn-warning btn-lg">增长最快</button>
    <button type="button" class="btn btn-danger btn-lg">不太理想</button>
</div>
<h3 style="margin-left: 5px"><strong>用户喜爱类别分布</strong></h3>
<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-info btn-lg">根据年龄分布</button>
    <button type="button" class="btn btn-warning btn-lg">根据性别分布</button>
    <button type="button" class="btn btn-danger btn-lg">根据角色分布</button>
</div>
<hr>
<table id="factoryTable"></table>
<div id="factoryPage"></div>

<%--厂商详情--%>
<script>
    $(function () {
        $('#factoryTable').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'/game/factory/findAll',
            datatype:'json',
            cellEdit: false,//开启单元格编辑
            colNames:['ID','名称','评分','游戏数量','好评数量','差评数量'],
            editurl:'/game/factory/edit',
            colModel:[
                {name:'id',hidden:true},
                {name:'name',align:'center',editable:true},
                {name:'score',align:'center',editable:true},
                {name:'gameNum',align:'center',editable:true},
                {name:'goodCommentsNum',align:'center',editable:true},
                {name:'badCommentsNum',align:'center',editable:true}
            ],
            pager:'#factoryPage', // 指定分页工具栏
            autowidth:true, // 自动计算列宽（依据父容器大小）
            rowNum:10, // 默认每页显示的条数
            page:1, // 默认显示第几页
            viewrecords:true, // 是否显示信息的总记录数
            caption:'展示所有厂商', // 设置表格标题
            height:368, // 设置表格高度
            hidegrid:false,// 指定是否显示折叠表格按钮
        }).navGrid('#factoryPage',{edit : true,add : true,del : true,search:false} ,{closeAfterEdit:true,},{closeAfterAdd:true});
    });
</script>
