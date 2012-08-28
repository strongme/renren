<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Place</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.ztree.all-3.3.js"></script>
<script type="text/javascript">
		var setting = {
				callback: {
					onClick: afterClick
				},
			async: {
				enable: true,
				url:"<%=request.getContextPath()%>/renren/initLocation.action",
				autoParam:["id"],
				type: "post"
			}
		};
	
	function afterClick(event, treeId, treeNode) {
		var content = treeNode.name;
		var index = content.lastIndexOf(" ");
		content = content.substring(index+1);
		var content = "<em>"+content+"</em>";
		$("#content").html(content);
	}
	
	$(function() {
		$.fn.zTree.init($("#treeplace"),setting);
	});
	
</script>
<style type="text/css">
	#content{
		position: absolute;
		right:100px;
		top: 35%;
		font-size: 75px;
		font-family: fantasy;
	}
</style>
</head>
<body>
<div>
<div style="float: left;width: 70%;">
<ul id="treeplace" class="ztree"></ul>
</div>

<div id="content">
</div>

</div>
</body>
</html>