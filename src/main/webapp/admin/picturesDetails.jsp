<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/DatePicker.js"></script>
    <title>图片列表</title>

    <script type="text/javascript">

        function deletePictures(id){
            var res = confirm("是否删除");
            if(res==true){
                window.location.href="${pageContext.request.contextPath }/picture/deletepictures.action?id="+id;
            }
        }

        //条件查询
        $(function(){
            //给查询按钮 添加 点击事件
            $("#search").click(function(){
                var pname = $("input[name='pname']").val();
                var createtime = $("input[name='createtime']").val();

                //使用ajax 进行异步交互
                $.ajax({
                    url:"${pageContext.request.contextPath}/picture/querypictureslist.action?pname="+pname+"&createtime="+createtime,
                    method:"post",
                    success:function(data){
                        if(data==0){
                            //alert("未找到指定内容");
                            $("#tb_list").html("<tr class='tr_head'><td>序号</td><td>图片封面</td><td>图片名称</td><td>上传时间</td><td>热度</td><td>描述</td><td>操作</td></tr>");

                        }else{
                            //showMsg(data);
                            var list = data;
                            $("#tb_list").html("<tr class='tr_head'><td>序号</td><td>图片封面</td><td>图片名称</td><td>上传时间</td><td>热度</td><td>描述</td><td>操作</td></tr>");
                            var i = 1;
                            for (var g in list) {
                                //声明 tr  td  对象
                                var tr = $("<tr></tr>");
                                var td1 = $("<td>" + (i++) + "</td>");
                                var td2 = $("<td>"+list[g].picture+"</td>");
                                var td3 = $("<td>"+list[g].pname+"</td>");
                                var td4 = $("<td>"+list[g].createtime+"</td>");
                                var td5 = $("<td>"+list[g].star+"</td>");
                                var td6 = $("<td>"+list[g].remark+"</td>");

                                //将td 添加到tr中
                                tr.append(td1);
                                tr.append(td2);
                                tr.append(td3);
                                tr.append(td4);
                                tr.append(td5);
                                tr.append(td6);
                                $("#tb_list").append(tr);
                            }
                        }
                    },
                    error:function(XMLHttpRequest,textStatus,errorThrown){
                        alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
                    }
                })
            })
        })

    </script>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                图片列表
            </div>
            <div class="panel-body">
                <form action="${pageContext.request.contextPath }/picture/querypictureslist.action" method="post">
                    <div class="row">
                        <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                            <div class="form-group form-inline">
                                <span>图片名称</span>
                                <input type="text" name="pname" class="form-control">
                            </div>
                        </div>
                        <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                            <div class="form-group form-inline">
                                <span>上传时间</span><!--  readonly="readonly"   onclick="setday(this)" -->
                                <input type="text" name="createtime" class="form-control">
                            </div>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <%--<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>--%>
                            <button type="submit" class="btn btn-primary">查询</button>
                        </div>

                    </div>
                </form>
                <div style="height: 400px;overflow: scroll;">
                    <table id="tb_list" class="table table-striped table-hover table-bordered">
                        <tr>
                            <td>序号</td><td>图片封面</td><td>图片名称</td><td>上传时间</td><td>热度</td><td>描述</td><td>操作</td>
                        </tr>
                        <c:forEach items="${pictureList}" var="pictures" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>
                                    <img src="${pictures.picture}" width="50px" height="50px">
                                </td>
                                <td>${pictures.pname}</td>
                                <td>${pictures.createtime}</td>
                                <td>${pictures.star}</td>
                                <td>${pictures.remark}</td>
                                <td style="width: 130px">
                                    <button onclick="deletePictures(${pictures.id})" class="btn btn-danger btn-sm">删除</button>&nbsp;&nbsp;
                                    <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal${pictures.id}">修改</button>&nbsp;&nbsp;
                                    <!-- 弹出模态框 -->

                                    <div class="modal fade" tabindex="-1" role="dialog" id="myModal${pictures.id}">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">
                                                        <span>&times;</span>
                                                    </button>
                                                    <h4 class="modal-title">修改图片信息</h4>
                                                </div>
                                                <form action="${pageContext.request.contextPath }/picture/updatepictures.action" method="post" enctype="multipart/form-data" class="form-horizontal" style="padding:10px">
                                                    <div class="motal-body">
                                                        <div class="form-group">
                                                            <input type="hidden" name="id" value="${pictures.id}">
                                                            <label class="col-sm-2 control-label">图片名</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" name="pname" class="form-control" value="${pictures.pname}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">上传日期</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" readonly="readonly" name="createtime" class="form-control" value="${pictures.createtime}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">图片星级</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" name="star" class="form-control" value="${pictures.star}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">图片</label>
                                                            <div class="col-sm-10">
                                                                <input type="file" name="file" />
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label">图片描述</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" name="remark" class="form-control" value="${pictures.remark}">
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="motal-footer text-center">
                                                        <button type="submit" class="btn btn-primary">修改</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>