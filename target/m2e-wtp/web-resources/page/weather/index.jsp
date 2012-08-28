<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weather</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
<script type="text/javascript" src="http://dev.baidu.com/wiki/static/map/API/examples/script/convertor.js"></script>
<script type="text/javascript">
	$(function() {
		//initialize();
		$("#get").click(function() {
			var location = $("#location").val();
			if(location.length==0) {
				alert("请填写要查看的地区");
				return;	
			}
			$.post("<%=request.getContextPath()%>/weather/getWeatherData.action",{locationName:location},function(returnedData,status) {
				if(returnedData.id.woe=="1") {
					alert("输入地点查不到信息，请尽量具体");
					return;
				}
				if(returnedData.id.woe=="0") {
					alert("超时，请稍后再试");
					return;
				}
				var content = "";
				content += "<tr><td colspan='2'><img src='"+returnedData.imgUrl+"'></td></tr>";
				content += "<tr><td>WOEID</td><td>"+returnedData.id.woe+"</td></tr>";
				content += "<tr><td>City Name</td><td>"+returnedData.cityName+"</td></tr>";
				content += "<tr><td>City Country</td><td>"+returnedData.cityCountry+"</td></tr>";
				content += "<tr><td>Date</td><td>"+returnedData.id.date+"</td></tr>";
				content += "<tr><td>Tempreature</td><td>"+returnedData.tmpLow+"℃ ~ "+returnedData.tmpHigh+"℃</td></tr>";
				content += "<tr><td>Wind Speed</td><td>"+returnedData.windSpeed+"km/h</td></tr>";
				content += "<tr><td>Humidity</td><td>"+returnedData.humidity+"%</td></tr>";
				content += "<tr><td>Visiblity</td><td>"+returnedData.visiblity+"km</td></tr>";
				content += "<tr><td>Pressure</td><td>"+returnedData.pressure+"</td></tr>";
				//content += "<tr><td>Location</td><td>"+returnedData.latitude+" : "+returnedData.longitude+"</td></tr>";
				var lat = parseFloat(returnedData.latitude);
				var lon = parseFloat(returnedData.longitude);
				$("#info").html(content);
				initialize(location);
			},"json");
		});
	});
	function initialize(name) {
		var mp = new BMap.Map('map');
		mp.centerAndZoom(name);
	}
	
	function loadScript() {
		var script = document.createElement("script");
		script.src = "http://api.map.baidu.com/api?v=1.2&callback=initialize";
		document.appendChild(script);
	}
	
</script>

<style type="text/css">
	#input {
		position: relative;
		left: 2%;
	}

	#map {
		position: relative;
		left: 5%;
		top:25%;
	}
	#table {
		position: relative;
		left: 2%;
		top: 25%;
	}
	
	body {
				background-image: url("<%=request.getContextPath()%>/img/bg1.jpg");
}
</style>
</head>
<body>
		<div id="input"><input type="text" id="location"><input type="button" value="GO" id="get"><br></div>
		
<div style="float: left;" id="table">
		<div style="float: left;"><table id="info" border="1">
		</table></div>
		<div id="map" style="width: 500px;height: 320px;float: right;"></div>
</div>
</body>
</html>