<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome RenRen</title>
<script type="text/javascript" src="scripts/renren.js"></script>
</head>
<body>
<script type="text/javascript">


var uiOpts = {
		 url : "http://graph.renren.com/oauth/authorize",
		  display : "iframe",
		  params : {"response_type":"token","client_id":"${requestScope.appId}","scope":"read_user_photo read_user_feed read_user_blog read_user_album"},
		  onSuccess: function(r){
		    top.location = "http://apps.renren.com/strongme/home";
		  },
		  onFailure: function(r){} 
	  };
	  Renren.ui(uiOpts);

</script>
RenRen Welcome 
</body>
</html>