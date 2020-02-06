<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 style="margin-top: -8px;margin-left: 5px"><strong>请选择数据</strong></h3>
<select id="tag_sel_kind" class="selectpicker" title="请选择类别">
</select>
<select id="tag_sel_game" class="selectpicker" title="请选择游戏">
</select>
<div style="height: 10px"></div>
<table id="table-tag"></table>
<div id="page-tag"></div>

<h3><strong>数据操作</strong></h3>
<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-info" id="clear">清除当前</button>
    <button type="button" class="btn btn-warning" id="clearAll">清除所有</button>
</div>
<%--评论详情--%>
<script>

    $(function () {

        $("#tag_sel_kind").selectpicker();
        $("#tag_sel_game").selectpicker();

        //获取游戏类别下拉列表的属性值
        $.ajax({
            method:"get",
            async:false,
            url:"/game/kind/selectAll",
            dataType:'json',
            success:function(data){
                for(var i=0;i < data.length ;i++){
                    $("#tag_sel_kind").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
                }
            }
        });

        //动态生成游戏下拉框
        $("#tag_sel_kind").on("change",function () {
            //每次获取数据时先清空数据
            $("#tag_sel_game").empty();
            //获取当前选择的类别
            var seled = $(this).val();
            var str = getGameName(seled);
            $("#tag_sel_game").append(str);
            $("#tag_sel_game").selectpicker("refresh");
        });

        //动态生成表格
        $("#tag_sel_game").on("change",function () {
            //获取当前选择的游戏
            var seled = $(this).val();
            //设置表格url
            var url = '/game/tag/findAllByGame?gameName='+seled
            $("#table-tag").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");
            $('#table-tag').jqGrid({
                styleUI:'Bootstrap',// 用于整合bootstrap的样式
                datatype:'json',
                cellEdit: false,//开启单元格编辑
                colNames:['ID','内容','所属用户Id'],
                editurl:'/game/tag/edit?gameName='+seled,
                colModel:[
                    {name:'id',hidden:true},
                    {name:'content',align:'center',editable:true},
                    {name:'userId',align:'center'},
                ],
                pager:'#page-tag', // 指定分页工具栏
                autowidth:true, // 自动计算列宽（依据父容器大小）
                rowNum:20, // 默认每页显示的条数
                page:1, // 默认显示第几页
                viewrecords:true, // 是否显示信息的总记录数
                caption:'展示所有标签', // 设置表格标题
                height:430, // 设置表格高度
                hidegrid:false,// 指定是否显示折叠表格按钮
            }).navGrid('#page-tag',{edit : true,add : true,del : true,search:false} ,{closeAfterEdit:true},{closeAfterAdd:true});
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

    $('#clear').click(function () {
        var gameName = $('#tag_sel_game option:selected').val();
        var r = window.confirm("您确认要清空 <<"+gameName+">> 的所有标签吗");
        if (r == true) {
            $.ajax({
                url:'/game/tag/clearByGameName?gameName='+gameName
            })
        }
    })

    $('#clearAll').click(function () {
        var r = window.confirm("您确认要清空标签库吗");
        if (r == true) {
            $.ajax({
                url:'/game/tag/clearAll'
            })
        }
    })

</script>
