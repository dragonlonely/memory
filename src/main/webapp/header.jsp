 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/memory/getpicturestypelist.action",
			type:"GET",
			success:function(data){
				for(var i in data){//i是索引
					var a = $("<a href='${pageContext.request.contextPath}/goods/getpicturesbytypeid.action?typeId="+data[i].id+"'>"+data[i].name+"</a>");
					$("#goodsType").append(a);
					
				}
			},
			dataType:"json",
			error:function(){
				//alert("失败");
			}
		})
	})
</script>
		
 <div id="top">
    	<div id="topdiv">
            <span>
                <a href="${pageContext.request.contextPath}/index.jsp" id="a_top" target="_blank" style="color:orange;">龙琳记忆</a>
                <li>|</li>
                <a href="" id="a_top">龙琳记忆</a>
                <li>|</li>
                <a href="" id="a_top">问题反馈</a>
            </span>
            <span style="float:right">
           		<c:if test="${empty user}">
	                <a href="/login.jsp" id="a_top">登录</a>
	                <li>|</li>
	                <a href="/register.jsp" id="a_top">注册</a>
           		</c:if>
       			<c:if test="${not empty user}">
       				<a href="${pageContext.request.contextPath}/self_show.jsp" id="a_top">${user.username}</a>
       				<li>|</li>
       				<a href="${pageContext.request.contextPath}/user/logout.action" onclick="return confirm('确定要注销吗?')" id="a_top">注销</a>
       				<li>|</li>
       				<a href="${pageContext.request.contextPath}/address/getaddress.action" id="a_top">地址管理</a>
					<li>|</li>
					<a href="${pageContext.request.contextPath}/memory/getallpictures.action" id="a_top" style="color: orange">最美记忆-DragonLin</a>
					<li>|</li>
					<a href="${pageContext.request.contextPath}/secret/getallsecretpictures.action" id="a_top" style="color: orange">私密相册-DragonLin</a>
       			</c:if>
                <li>|</li>
                <a href="" id="a_top">消息通知</a>
            </span>
        </div>
 </div>
<div id="second">
	    <a href="" id="seimg" style=" margin-top:23px;"><img id="logo" src="${pageContext.request.contextPath}/image/love01.jpg" width="55" height="54"/></a>
        <a href="" id="seimg" style=" margin-top:17px;"><img id="gif" src="${pageContext.request.contextPath}/image/gif_ll.gif" width="120" height="70" /></a>
	<p id="goodsType">
			<!-- 根据ajax 回调函数 填写数据 到此id中 -->
        </p>
       <form class="form-inline pull-right" style="margin-top: 40px;margin-right: 10px;" action="${pageContext.request.contextPath }/memory/searchpictures.action" method="post">
		
		  <div class="form-group">
		    <%--<input type="hidden" name="method" value="searchgoods.action">--%>
		    <input type="text" class="form-control" style="width: 300px"  placeholder="搜索一下好东西..." name="picturesname">
		    
		  </div>
		  <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索</button>
	  </form>
</div>
