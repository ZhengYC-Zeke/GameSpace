<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="btn-group">
    <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="kind">
       请选择类别 <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" id="menu">
        <li class="dropdown-header">所有类别</li>
    </ul>
</div>
<button class="btn btn-danger" id="add">添加游戏</button>
<hr>
<table id="table"></table>
<div id="page"></div>

<div id="form" style="display:none">
    <form id="form-subbmit">
        <div class="form-group">
            <label for="name">游戏名称</label>
            <input type="hidden" name="id" id="id">
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入游戏名称">
        </div>
        <div class="form-group">
            <label for="form-kind">游戏类别</label>
            <select class="form-control" id="form-kind" name="kind">

            </select>
        </div>
        <div class="form-group">
            <label for="factory">游戏厂商</label>
            <select class="form-control" id="factory" name="factory">

            </select>
        </div>
        <div class="form-group">
            <label for="introduce">游戏简介</label>
            <textarea class="form-control" rows="3" name="introduce" id="introduce" placeholder="请输入游戏简介（100字以内）"></textarea>
        </div>
        <div class="form-group">
            <label for="score">游戏评分</label>
            <input type="text" class="form-control" id="score" name="score" placeholder="请输入游戏评分">
        </div>
        <div class="form-group">
            <label>视频、图片上传</label>
            <div class="item">
                <label for="vedio">请选择上传视频</label>
                <input type="file" id="vedio" name="files">
            </div>
            <div class="item">
                <label for="showGamePath">请选择游戏详情页面图片,格式460*215</label>
                <input type="file" id="showGamePath" name="files">
            </div>
            <div class="item">
                <label for="recommendedGamePath">请选择特别推荐页面图片,格式480*300</label>
                <input type="file" id="recommendedGamePath" name="files">
            </div>
            <div class="item">
                <label for="listGamePath">请选择首页按钮展示页面图片,格式255*143</label>
                <input type="file" id="listGamePath" name="files">
            </div>
            <div class="item">
                <label for="cutGamePath1">请选择游戏截图1,格式1024*768</label>
                <input type="file" id="cutGamePath1" name="files">
            </div>
            <div class="item">
                <label for="cutGamePath2">请选择游戏截图2,格式1024*768</label>
                <input type="file" id="cutGamePath2" name="files">
            </div>
        </div>
        <%--用于判断修改还是添加--%>
        <input type="hidden" id="subbmitValue">
        <button type="button" class="btn btn-success btn-lg" style="margin-top: -110px;margin-left: 830px" onclick="subbmit()">提交</button>
        <button type="reset" class="btn btn-danger btn-lg" style="margin-top: -149px;margin-left: 740px" id="clear">清空</button>
    </form>
</div>

<%--类别详情--%>
<script>
    $(function () {
        //获取所有类别
        $.ajax({
            methid:'get',
            url:'/game/kind/selectAll',
            dataType:'json',
            success:function (data) {
                for (var x = 0 ; x < data.length ; x++){
                    $('#menu').append($('<li><a onclick="show(\'' + data[x].name + '\')">'+data[x].name+'</a></li>'))
                    $('#form-kind').append($('<option value="'+data[x].name+'">'+data[x].name+'</option>'))
                }
            }
        })
        //获取所有厂商
        $.ajax({
            methid:'get',
            url:'/game/factory/selectAll',
            dataType:'json',
            success:function (data) {
                for (var x = 0 ; x < data.length ; x++){
                    $('#factory').append($('<option value="'+data[x].name+'">'+data[x].name+'</option>'))
                }
            }
        })
    });

    //根据类别展示
    function show(name) {
        var url = '/game/game/findAll?kindName='+name;//设置表格url

        $("#table").jqGrid('setGridParam', {url: url}).trigger("reloadGrid");

        $('#table').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            datatype:'json',
            cellEdit: false,//开启单元格编辑
            editurl:'/game/game/edit',
            colNames:['ID','名称','类别','厂商','评分','状态','好评数','差评数','关注数','操作'],
            colModel:[
                {name:'id',hidden:true},
                {name:'name',align:'center'},
                {name:'kind',align:'center'},
                {name:'factory',align:'center'},
                {name:'score',align:'center'},
                {name:'state',align:'center'},
                {name:'goodCommentsNum',align:'center'},
                {name:'badCommentsNum',align:'center'},
                {name:'attentionNum',align:'center'},
                {
                    name:"xx",align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return "<a class=\"btn btn-default btn-warning\" onclick=\"update('"+rowObject.id+"')\">修改</a>";
                    }
                }
            ],
            pager:'#page', // 指定分页工具栏
            autowidth:true, // 自动计算列宽（依据父容器大小）
            rowNum:5, // 默认每页显示的条数
            page:1, // 默认显示第几页
            viewrecords:true, // 是否显示信息的总记录数
            height:250, // 设置表格高度
            hidegrid:false// 指定是否显示折叠表格按钮
        }).navGrid('#page',{edit : false,add : false,del : true,search:false});
    }

    //修改
    function update(id) {
        $('#subbmitValue').val('update');
        $.ajax({
            method:'get',
            url:"/game/game/selectById?id="+id,
            dateType:'json',
            success:function (data) {
                //隐藏jqgird表格
                $('#gbox_table').css('display','none');
                //展示表单
                $('#form').css('display','block');
                //数据回显
                $('#id').val(data.id);
                $('#name').val(data.name);
                $('#form-kind').find("option[value='"+data.kind+"']").attr("selected",true);
                $('#Factory').find("option[value='"+data.factory+"']").attr("selected",true);
                $('#score').val(data.score);
            }
        })
    }

    //添加游戏
   $('#add').click(function () {
       //清空表单
       $('#clear').click();
       //将提交按钮设置为添加按钮
       $('#subbmitValue').val('insert');
       //隐藏jqgrid表格
       $('#gbox_table').css('display','none');
       //显示表单
       $('#form').css('display','block');
    });

    //展示时关闭添加表单
    $('#kind').click(function () {
        //隐藏表单
        $('#form').css('display','none');
        //显示jqgrid表格
        $('#gbox_table').css('display','block');
    });

    //表单提交
    function subbmit() {
        var value = $('#subbmitValue').val();
        $.ajax({
            method:'post',
            url:'/game/game/'+value,
            processData:false,
            contentType : false,
            data: new FormData($('#form-subbmit')[0]),
            //提交完成后
            success:function () {
                //关闭表单
                $('#form').css('display','none');
                //展示jqgrid表格
                $('#gbox_table').css('display','block');
                //刷新表格
                $("#table").trigger("reloadGrid");
            }
        })
    }

</script>
