<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
<title>商品详情页</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login2.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
  
    	<div style="margin: 0 auto;width: 90%;">
	   <ol class="breadcrumb">
		  <li><a href="#">龙琳记忆</a></li>
		  <li class="active"><a href="${pageContext.request.contextPath }/memory/getpicturesdetailbyid.action?id=${pictures.id}">${pictures.pname}</a></li>
	   </ol>
   </div>
   
	<div class="container">
		<div class="row">
		  <div class="col-xs-6 col-md-6">
		    <a href="#" class="thumbnail">
		      <img src="${pictures.picture}"  width="400" height="400"  alt="${pictures.pname}" />
		    </a>
		  </div>
		  <div class="col-xs-6 col-md-6">
		   	<div class="panel panel-default" style="height: 560px">
			  <div class="panel-heading">照片详情</div>
			  <div class="panel-body">
			    <h3>图片名称:<small style="color: red">${pictures.pname}</small></h3>
			    <div style="margin-left: 10px;">
				    <p>上传时间:&nbsp;&nbsp;&nbsp;${pictures.createtime}</p>
					<p>热度:&nbsp;&nbsp;&nbsp;
						<c:forEach begin="1" end="${pictures.star}">
				        		<img src="${pageContext.request.contextPath}/image/star_red.gif" alt="star"/>
				        	</c:forEach>
					</p>
				    <p>详细介绍:</p>
				    <p>&nbsp;&nbsp;${pictures.remark }</p>
				    <a href="${pageContext.request.contextPath}/secret/addsecretpicture.action?id=${pictures.id}&number=1" class="btn btn-warning">加入隐私&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-shopping-cart"></span></a>&nbsp;&nbsp;&nbsp;
			    </div>
			  </div>
			</div>
		  </div>
		</div>
	</div>
   <!-- 底部 -->
   <%@ include file="footer.jsp"%>


</body>
</html>