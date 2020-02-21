<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 style="margin-top: -8px;margin-left: 5px"><strong>评论数据统计</strong></h3>
<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-info btn-lg">较为认同</button>
    <button type="button" class="btn btn-warning btn-lg">不太有用</button>
</div>
<hr>
<h3 style="margin-top: -8px;margin-left: 5px"><strong>请选择数据</strong></h3>
<select id="sel_kind" class="selectpicker" title="请选择类别">
</select>
<select id="sel_game" class="selectpicker" title="请选择游戏">
</select>
<div style="height: 10px"></div>
<table id="table-comment"></table>
<div id="page-comment"></div>

<%--评论详情--%>
<script>
    $(function () {

        $("#sel_kind").selectpicker();
        $("#sel_game").selectpicker();

        //获取游戏类别下拉列表的属性值
        $.ajax({
            method:"get",
            async:false,
            url:"/game/kind/selectAll",
            dataType:'json',
            success:function(data){
                for(var i=0;i < data.length ;i++){
                    $("#sel_kind").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
                }
            }
        });

        //动态生成游戏下拉框
        $("#sel_kind").on("change",function () {
            //每次获取数据时先清空数据
            $("#sel_game").empty();
            //获取当前选择的类别
            var seled = $(this).val();
            var str = getGameName(seled);
            $("#sel_game").append(str);
            $("#sel_game").selectpicker("refresh");
        });

        //动态生成表格
        $("#sel_game").on("change",function () {
            //获取当前选择的游戏
            var seled = $('#sel_game option:selected').val();
            //设置表格添加url
            var url = '/game/comment/findAllByGame?gameName='+seled
            //设置表格修改url
            var eurl = '/game/comment/edit?gameName='+seled
            $("#table-comment").setGridParam({url:url}) .trigger("reloadGrid");
            $("#table-comment").setGridParam({editurl:eurl}) .trigger("reloadGrid");
            $('#table-comment').jqGrid({
                styleUI:'Bootstrap',// 用于整合bootstrap的样式
                datatype:'json',
                cellEdit: false,//开启单元格编辑
                colNames:['ID','内容','所属用户Id','状态','评论种类','好评数','差评数','中立数'],
                colModel:[
                    {name:'id',hidden:true},
                    {name:'content',align:'center',editable:true},
                    {name:'userId',align:'center'},
                    {name:'state',align:'center',editable:true,edittype:"select", editoptions:{value:"正常:正常;待审核:待审核;违规:违规"}},
                    {name:'kind',align:'center',editable:true,edittype:"select", editoptions:{value:"好评:好评;差评:差评"}},
                    {name:'goodNum',align:'center',editable:true},
                    {name:'badNum',align:'center',editable:true},
                    {name:'generalNum',align:'center',editable:true}
                ],
                pager:'#page-comment', // 指定分页工具栏
                autowidth:true, // 自动计算列宽（依据父容器大小）
                rowNum:10, // 默认每页显示的条数
                page:1, // 默认显示第几页
                viewrecords:true, // 是否显示信息的总记录数
                caption:'展示所有评论', // 设置表格标题
                height:400, // 设置表格高度
                hidegrid:false,// 指定是否显示折叠表格按钮
            }).navGrid('#page-comment',{edit : true,add : true,del : true,search:false} ,{closeAfterEdit:true},{closeAfterAdd:true});
        })

        //获取游戏名称下拉列表的属性值
        function getGameName(gameKind){
            //动态生成select内容
            var str="";
            $.ajax({
                method:"get",
                async:false,
                url:"/game/game/selectByKindName?kindName="+gameKind,
                dataType:'json',
                success:function(data){
                    if(data == ""){
                        str += "<option>"+"该类别下暂无游戏"+"</option>";
                    }
                    if (data != null) {
                        for(var i=0;i < data.length ;i++){
                            str += "<option value='" + data[i].name + "'>" + data[i].name + "</option>";
                        }
                    }
                }
            });
            return str;
        }

    });

</script>
