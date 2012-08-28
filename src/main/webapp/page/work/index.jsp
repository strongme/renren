<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="w" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/work/workAction.action" method="post" enctype="multipart/form-data">
	
	<input type="file" name="data"><br>
	<input type="file" name="data"><br>

	<select multiple="multiple" name="se">
		<option value="Content1">Content1</option>
		<option value="Content2">Content2</option>
		<option value="Content3">Content3</option>
		<option value="Content4">Content4</option>
	</select>
	<input type="submit">
</form>

</body>
</html>