<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2 style="margin-top: -8px;margin-left: 10px">数据操作</h2>
<div class="btn-group" role="group" aria-label="...">
    <button type="button" class="btn btn-warning" id="in">导入Excel数据</button>
    <button type="button" class="btn btn-danger" id="out">导出Excel数据</button>
</div>
<hr>
<table id="table-picture"></table>
<div id="page-picture"></div>

<form method="post" enctype="multipart/form-data" id="form">
    <input type="file" id="btn_file" style="display:none" name="file">
</form>

<%--轮播图详情--%>
<script>
    $(function () {
        $('#table-picture').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'/game/picture/findAll',
            datatype:'json',
            cellEdit: false,//开启单元格编辑
            colNames:['ID','封面','创建日期','状态','所属游戏','轮播图名称','介绍'],
            editurl:'/game/picture/edit',
            colModel:[
                {name:'id',hidden:true},
                {name:'picPath',align:'center',editable:true,formatter:function showPicture(cellvalue){
                        return "<img src='${pageContext.request.contextPath}/static" +cellvalue + "'width='100%'/>";
                    },edittype:'file',
                    editoptions:{enctype:"multipart/form-data"}},
                {name:'createDate',align:'center'},
                {name:'state',editable:true,align:'center',edittype:"select", editoptions:{value:"正常:正常;冻结:冻结"}},
                {name:'gameId',align:'center',editable:true,edittype:"select",editoptions:{value:gettypes()}},
                {name:'name',align:'center',editable:true},
                {name:'content',align:'center',editable:true}
            ],
            pager:'#page-picture', // 指定分页工具栏
            autowidth:true, // 自动计算列宽（依据父容器大小）
            rowNum:7, // 默认每页显示的条数
            page:1, // 默认显示第几页
            viewrecords:true, // 是否显示信息的总记录数
            caption:'展示所有轮播图', // 设置表格标题
            height:476, // 设置表格高度
            hidegrid:false,// 指定是否显示折叠表格按钮
        }).navGrid('#page-picture',{edit : true,add : true,del : true,search:false} ,{closeAfterEdit:true,
            afterSubmit:function upload(response) {
                //后台传来的map中的data属性,存放了对象的id
                var id = response.responseJSON.data;
                //后台传来的map中的data属性,存放了运行成功标识 200或其他的
                var code = response.responseJSON.code;
                if (code == '200') {
                    $.ajaxFileUpload({
                        url: "/game/picture/upload",//用于文件上传的服务器端请求地址
                        fileElementId: 'picPath',    //文件上传空间的id属性  <input type="file" id="file" name="file" />
                        type:'post',
                        data: {id: id},
                        success : function() {
                            $("#tt").trigger("reloadGrid");
                        }
                    });
                    return "true";
                }else {
                    return "false";
                }
            }},{closeAfterAdd:true,
            afterSubmit:function upload(response) {
                //后台传来的map中的data属性,存放了对象的id
                var id = response.responseJSON.data;
                //后台传来的map中的data属性,存放了运行成功标识 200或其他的
                var code = response.responseJSON.code;
                if (code == '200') {
                    $.ajaxFileUpload({
                        url: "/game/picture/upload",//用于文件上传的服务器端请求地址
                        fileElementId: 'picPath',    //文件上传空间的id属性  <input type="file" id="file" name="file" />
                        type:'post',
                        data: {id: id},
                        success : function() {
                            $("#tt").trigger("reloadGrid");
                        }
                    });
                    return "true";
                }else {
                    return "false";
                }
            }
        });
    });

    //获取下拉列表的属性值
    function gettypes(){
        //动态生成select内容
        var str="";
        $.ajax({
            method:"get",
            async:false,
            url:"/game/game/selectAll",
            dataType:'json',
            success:function(data){
                if (data != null) {
                    for(var i=0;i < data.length ;i++){
                        if(i!=data.length-1){
                            str+=data[i].id+":"+data[i].name+";";
                        }else{
                            str+=data[i].id+":"+data[i].name;
                        }
                    }
                }
            }
        });
        return str;
    }

    //导出excel表格
    $('#out').click(function () {
        location.href="/game/excel/out?value=picture";
    })

    //导入表格
    $('#in').click(function () {
        $("#btn_file").click();
        openuploads();
    })

    function openuploads() {
        $("#btn_file").change(function () {
            $.ajax({
                method:'post',
                url:'/game/excel/in?value=picture',
                processData:false,
                contentType : false,
                data: new FormData($('#form')[0]),
                success:function () {
                    $("#tt").trigger("reloadGrid");
                }
            })
        })
    }
</script>
