<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image View</title>
<link rel="stylesheet"
				href="<%=request.getContextPath()%>/scripts/global.css">
<script type="text/javascript"
				src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript"
				src="<%=request.getContextPath()%>/scripts/slides.jquery.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("#p").click(function() {
			$.post("<%=request.getContextPath()%>/renren/getPhotoes.action",{},
					function(returnedData,status) {
						var content = "";
						for(var i=0;i<returnedData.length;i++) {
							content += "<div class='slide'><img src='"+returnedData[i].url+"' width='570' height='270'><div class='caption' style='bottom: 0'><p>"+returnedData[i].name+"</p></div></div>";
						}
						$(".slides_container").html(content);
					},"json");
			dis();
		});
		
		dis();
	});
	
		function dis() {
			$("#slides").slides({
				preload : true,
				preloadImage : '../../img/loading.gif',
				play : 5000,
				pause : 2500,
				hoverPause : true,
				generateNextPrev: true,
				animationStart : function(current) {
					$('.caption').animate({
						bottom : -35
					}, 100);
					if (window.console && console.log) {
						// example return of current slide number
						console.log('animationStart on slide: ', current);
					}
					;
				},
				animationComplete : function(current) {
					$('.caption').animate({
						bottom : 0
					}, 200);
					if (window.console && console.log) {
						// example return of current slide number
						console.log('animationComplete on slide: ', current);
					}
					;
				},
				slidesLoaded : function() {
					$('.caption').animate({
						bottom : 0
					}, 200);
				}
			});
		}
</script>
</head>
<body>
<input type="button" id="p" value="Get">
				<div id="container">
								<div id="example">
												<img src="../../img/new-ribbon.png" width="112" height="112"
																alt="New Ribbon" id="ribbon">
												<div id="slides">
																<div class="slides_container">
								 												<div class="slide">
																								<img src="../../img/img3.jpg" width="570"
																												height="270" alt="Slide 1">
																								<div class="caption" style="bottom: 0">
																												<p>Happy Bokeh Thursday!</p>
																								</div>
																				</div>
																				<div class="slide">
																								<img src="../../img/img4.jpg" width="570"
																												height="270" alt="Slide 2">
																								<div class="caption">
																												<p>Taxi</p>
																								</div>
																				</div>
																				<div class="slide">
																								<img src="../../img/img5.jpg" width="570"
																												height="270" alt="Slide 3">
																								<div class="caption">
																												<p>Happy Bokeh raining Day</p>
																								</div>
																				</div>
																				<div class="slide">
																								<img src="../../img/img6.jpg" width="570"
																												height="270" alt="Slide 4">
																								<div class="caption">
																												<p>We Eat Light</p>
																								</div>
																				</div>
																				<div class="slide">
																								<img src="../../img/img1.jpg" width="570"
																												height="270" alt="Slide 5">
																								<div class="caption">
																												<p>&ldquo;I must go down to the sea
																																again, to the lonely sea and the
																																sky...&rdquo;</p>
																								</div>
																				</div>
																				<div class="slide">
																								<img src="../../img/img2.jpg" width="570"
																												height="270" alt="Slide 6">
																								<div class="caption">
																												<p>twelve.inch</p>
																								</div>
																				</div>
																				<div class="slide">
																								<img src="../../img/img3.jpg" width="570"
																												height="270" alt="Slide 7">
																								<div class="caption">
																												<p>Save my love for loneliness</p>
																								</div>
																				</div>
																</div>
																<a href="#" class="prev">
																	<img src="../../img/arrow-prev.png" width="24" height="43" alt="Arrow Prev"> 
																</a> 
																<a href="#" class="next">
																	<img src="../../img/arrow-next.png" width="24" height="43" alt="Arrow Next">
																 </a>
												</div>
												<img src="../../img/example-frame.png" width="739"
																height="341" alt="Example Frame" id="frame">
								</div>
				</div>


</body>
</html>