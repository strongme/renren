<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Strong Me Home</title>
<style type="text/css">
	body {
				background-image: url("<%=request.getContextPath()%>/img/bg1.jpg");
}
</style>

</head>
<body>
<div style="float: left;">
	<div style="float: left;">
		<a href="<%=request.getContextPath() %>/page/weather">看天气</a>
	</div>
	<div style="float: right;">
		<a href="<%=request.getContextPath() %>/page/renren/img_view.jsp">看相册</a>
	</div>
</div>


<img alt="" src="${requestScope.userHead}">
Home of ${requestScope.userName }
<p>下面是您的一部分好友</p>
<c:forEach var="friend" items="${requestScope.friendList }">
	<img src="${friend.tinyurl }"/>
</c:forEach>
<br>
<p>下面是相册</p>
<c:forEach var="photos" items="${requestScope.photos}">
	<img src="${photos.url}"/><b>Time: ${photos.update_time} Name:${photos.name}</b><br>
</c:forEach>
<p>最近状态</p>
<c:forEach var="feeds" items="${requestScope.feeds}">
	<font color="red">${feeds.message}</font><b>Time: ${feeds.update_time} Name:${feeds.name}</b><br>
</c:forEach>
<p>最新日志</p>
<c:forEach var="blog" items="${requestScope.blogs.blogs}">
	<h1>${blog.title}</h1>
	<p>${blog.content}</p>
	<b>${blog.time}</b><br>
</c:forEach>


</body>
</html>