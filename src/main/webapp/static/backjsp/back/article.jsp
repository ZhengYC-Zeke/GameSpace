<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal">
    添加文章
</button>
<hr>
<table id="table-article"></table>
<div id="page-article"></div>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="myModalLabel">添加文章</h3>
            </div>
            <div class="modal-body">
                <form id="formadd" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" name="name" placeholder="请输入文章名..."
                               class="form-username form-control" required>
                    </div>
                    请选择文章封面...<input type="file" name="file" id="file"><br>
                    <div class="form-group">
                        <input type="text" name="author" placeholder="请输入作者名..."
                               class="form-username form-control" required>
                    </div>
                    <div class="form-group">
                        <input type="text" name="presentation" placeholder="请输入文章介绍..."
                               class="form-username form-control" required>
                    </div>
                    <div class="form-group">
                        <textarea id="editor_id" name="content" style="height: 310px;width: 100%">&lt;strong&gt;HTML内容&lt;/strong&gt;</textarea>
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add()">添加</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class='modal fade' id='exampleModal' role='dialog' aria-labelledby='exampleModalLabel'>
    <div class='modal-dialog' role='document'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                <h4 class='modal-title' id='exampleModalLabel'>修改文章</h4>
            </div>
            <div class="modal-body">
                <form id="formupdate" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" name="id" placeholder="文章Id..." readonly="readonly"
                               class="form-username form-control" id="id" required>
                    </div>
                    请选择文章封面...<input type="file" name="file"><br>
                    <div class="form-group">
                        <textarea id="editor_id1" name="content" style="height: 310px;width: 100%">&lt;strong&gt;HTML内容&lt;/strong&gt;</textarea>
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="close1">关闭</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="update()">修改</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%--文章详情--%>
<script>
    $(function () {
        $('#table-article').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'/game/article/findAll',
            datatype:'json',
            cellEdit: false,//开启单元格编辑
            colNames:['ID','文章名称','作者ID','作者名称','封面','文章介绍','文章内容','状态','好评数','差评数','中立评论数','操作'],
            editurl:'/game/article/edit',
            colModel:[
                {name:'id',hidden:true},
                {name:'name',align:'center',editable:true},
                {name:'userId',align:'center'},
                {name:'author',align:'center',editable:true},
                {name:'picPath',align:'center',formatter:function showPicture(cellvalue){
                        return "<img src='${pageContext.request.contextPath}/static/img" +cellvalue + "'width='100%'/>";
                    },edittype:'file',
                    editoptions:{enctype:"multipart/form-data"}},
                {name:'presentation',align:'center',editable:true},
                {name:'contentPath',align:'center',formatter:function showContent(cellvalue) {
                        return "<button type='button' class='btn btn-success' onclick=\"content('"+cellvalue+"')\">查看</button>"
                    }},
                {name:'state',editable:true,align:'center',edittype:"select", editoptions:{value:"正常:正常;违规:违规;未审核:未审核"}},
                {name:'goodNum',align:'center',editable:true},
                {name:'badNum',align:'center',editable:true},
                {name:'generalNum',align:'center',editable:true},
                {
                    name:"xx",
                    formatter:function(cellvalue, options, rowObject) {
                        return "<button type='button' class='btn btn-warning' data-toggle='modal' data-target='#exampleModal' onclick=\"updateshow('"+rowObject.id+"')\">内容修改</button>";
                    }
                }
            ],
            pager:'#page-article', // 指定分页工具栏
            autowidth:true, // 自动计算列宽（依据父容器大小）
            rowNum:6, // 默认每页显示的条数
            page:1, // 默认显示第几页
            viewrecords:true, // 是否显示信息的总记录数
            caption:'展示所有文章', // 设置表格标题
            height:500, // 设置表格高度
            hidegrid:false// 指定是否显示折叠表格按钮
        }).navGrid('#page-article',{edit : true,add : false,del : true,search:false},{closeAfterEdit:true});

        //加载kindEditor
        KindEditor.create("textarea[name='content']",{
            uploadJson : '/game/kindEditor/upload',
            afterBlur:function () {
                this.sync();
            }
        });
    });

    //在新页面展示内容详情
    function content(data) {
        window.open("${pageContext.request.contextPath}"+data)
    }

    //点击修改回显数据
    function updateshow(id) {
        var data = $('#table-article').jqGrid("getRowData",id);
        $('#id').val(data.id);
        KindEditor.html('#editor_id1',data.content);
    }

    //修改数据
    function update() {
        $.ajax({
            method:'post',
            url:'/game/article/update',
            processData:false,
            contentType : false,
            data: new FormData($('#formupdate')[0]),
            success:function () {
                $("#table-article").trigger("reloadGrid");
            }
        })
    }

    //添加文章
    function add() {
        $.ajax({
            method:'post',
            url:'/game/article/insert',
            processData:false,
            contentType : false,
            data: new FormData($('#formadd')[0]),
            success:function () {
                $("#table-article").trigger("reloadGrid");
            }
        })
    }

</script>
