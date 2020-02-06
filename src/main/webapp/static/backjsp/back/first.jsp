<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4 style="margin-top: -6px"><strong>审核模块</strong></h4>
<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-info" id="comment">评论审核</button>
    <button type="button" class="btn btn-warning" id="article">文章审核</button>
</div>
<div style="height: 10px"></div>
<table id="table-first"></table>
<div id="page-first"></div>

<div class='modal fade' id='exampleModal' role='dialog' aria-labelledby='exampleModalLabel'>
    <div class='modal-dialog' role='document'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                <h4 class='modal-title' id='exampleModalLabel'>违规评论处理</h4>
            </div>
            <div class="modal-body">
                <form id="formUnPass" method="post">
                    <div class="form-group">
                        <label for="id">评论Id</label>
                        <input type="text" name="id" readonly="readonly"
                               class="form-username form-control" id="id" required>
                    </div>
                    <div class="form-group">
                        <label for="userId">用户Id</label>
                        <input type="text" name="userIdid" readonly="readonly"
                               class="form-username form-control" id="userId" required>
                    </div>
                    <div class="form-group">
                        <label for="result">请输入违规原因</label>
                         <textarea name="result" id="result" class="form-username form-control" required></textarea>
                    </div>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="unPass()">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    //加载评论jqgrid
    $('#comment').click(function() {
        $('#table-first').jqGrid({
            styleUI: 'Bootstrap',// 用于整合bootstrap的样式
            url: '/game/comment/selectStateIsCheck',
            datatype: 'json',
            cellEdit: false,//开启单元格编辑
            colNames: ['ID', '评论内容','用户ID', '操作'],
            colModel: [
                {name: 'id', hidden: true},
                {name: 'content', align: 'center'},
                {name: 'userId', align: 'center'},
                {
                    name: "xx",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<button type='button' class='btn btn-success btn-xs' onclick=\"cPass('" + rowObject.id + "')\">通过</button><button type='button' class='btn btn-warning btn-xs' data-toggle='modal' data-target='#exampleModal' onclick=\"cUnPass('"+rowObject.id+"')\">违规</button>";
                    }
                }
            ],
            pager: '#page-first', // 指定分页工具栏
            autowidth: true, // 自动计算列宽（依据父容器大小）
            rowNum: 10, // 默认每页显示的条数
            page: 1, // 默认显示第几页
            viewrecords: true, // 是否显示信息的总记录数
            caption: '评论审核', // 设置表格标题
            height: 508, // 设置表格高度
            hidegrid: false,// 指定是否显示折叠表格按钮
        }).navGrid('#page-first',{edit : false,add : false,del : false,search:false});
    })

    //在新页面展示内容详情
    function content(data) {
        window.open("${pageContext.request.contextPath}"+data)
    }

    //设置被审核评论的数据回显
    function cUnPass(data) {
        //获取当前行数据
        var rowData = $("#table-first").jqGrid('getRowData',data);
        $('#id').val(rowData.id);
        $('#userId').val(rowData.userId);
    }

    //提交审核未通过数据
    function unPass() {
        $.ajax({
            method:'post',
            url:'/game/comment/cUnPass',
            data: $('#formUnPass').serialize(),
            success:function () {
                $("#table-first").trigger("reloadGrid");
            }
        })
    }

    //提交审核他通过数据
    function cPass(id) {
        $.ajax({
            url:'/game/comment/cPass',
            method:'post',
            data:{
                id:id
            },
            success:function () {
                $("#table-first").trigger("reloadGrid");
            }
        })
    }

</script>
