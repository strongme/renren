$(function() {
		
		var myCanvas = document.getElementById("myCanvas");
		var context = myCanvas.getContext("2d");
		var context3 = myCanvas.getContext("2d");
		context3.lineWidth = 100;
		context.beginPath();
		context.strokeStyle = "rgb(0, 0, 0)";
		context.arc(200, 150, 100, 0, 0.6 * Math.PI, true);
		context.stroke();
		
		context3.lineWidth = 50;
		context3.lineCap = "butt";
		context3.lineJoin = "miter";
		context3.strokeStyle = "rgba(34,12,12,0.6)"
		context3.beginPath();
		context3.moveTo(10,40);
		context3.bezierCurveTo(90,50,20,600,350,60);
		context3.stroke();
		
		
		var myCanvas2 = document.getElementById("myCanvas2");
		var context2 = myCanvas2.getContext("2d");
		context2.fillRect(5, 5, 145, 145);
		context2.fillStyle = "rgb(0, 162, 232)";
		context2.fillRect(40, 55, 145, 145);
		context2.fillStyle = "rgba(255, 0, 0, 0.2)";
		context2.fillRect(75, 105, 145, 145);
		
		// face
		context2.beginPath();
		context2.lineWidth = 10;
		context2.strokeStyle = "rgb(0, 0, 0)";
		context2.arc(200, 233, 150, 0, 2 * Math.PI, true);
		context2.stroke();
		// color face
		context2.beginPath();
		context2.fillStyle = "rgba(80, 100, 80, 0.4)";
		context2.arc(200, 233, 150, 0, 2 * Math.PI, true);
		context2.fill();
		// right eye
		context2.lineWidth = 20;
		context2.beginPath();
		context2.moveTo(230, 130);
		context2.bezierCurveTo(230, 130, 230, 130, 240, 200);
		context2.stroke();
		// left eye
		context2.beginPath();
		context2.moveTo(170, 130);
		context2.bezierCurveTo(170, 130, 170, 130, 160, 200);
		context2.stroke();
		// upper lip
		context2.beginPath();
		context2.moveTo(100, 230);
		context2.bezierCurveTo(100, 230, 200, 380, 300, 230);
		context2.stroke();
		// lower lip
		context2.beginPath();
		context2.moveTo(100, 235);
		context2.bezierCurveTo(105, 270, 200, 480, 300, 232);
		context2.stroke();
		
		var gradient = context.createLinearGradient(5,32,166,23);
		gradient.addColorStop(0.1,"#1166dd");
		gradient.addColorStop(0.5,"#99ddff");
		gradient.addColorStop(0.7,"#66aa11");
		context.fillStyle = gradient;
		context.fillRect(5,5,145,145);
		
		var myCanvas3 = document.getElementById("myCanvas3");
		var context4 = myCanvas3.getContext("2d");
		context4.lineCap = "round";
		for(var i=15;i>0;i--) {
			context4.strokeStyle = "blue";
			context4.lineWidth= i;
			context4.beginPath();
			context4.moveTo(55,59+(15-i)*24);
			context4.lineTo(355,20+(15-i)*24);
			context4.stroke();
		}
		
		var context5 = myCanvas3.getContext("2d");
		context5.font = "80px '微软雅黑'";
		context5.fillStyle = "rgba(33,22,143,0.8)";
		context5.shadowOffsetX = 10;
		context5.shadowOffsetY = 10 ;
		context5.shadowBlur= 10;
		context5.shadowColor = 'rgba(0,0,0,0.9)';
		context5.fillText("中国",43,350);
		
		can.init();
		cube.init(document.getElementById("myCanvas5"));
		
		var canvas6 = document.getElementById("myCanvas6");
		var context6 = canvas6.getContext('2d');
		
		context6.fillCircle = function(x,y,radius,fillColor) {
			this.fillStyle = fillColor;
			this.beginPath();
			this.moveTo(x,y);
			this.arc(x,y,radius,0,Math.PI*2,false);
			this.fill();
		};
		
		context6.clearTo = function(fillColor) {
			context6.fillStyle = fillColor;
			context6.fillRect(0,0,canvas6.width,canvas6.height);
		};
		
		context6.clearTo("#ddd");
		canvas6.onmousemove = function (e) {
		if (!canvas6.isDrawing) return;
		var x = e.pageX - this.offsetLeft;
		var y = e.pageY - this.offsetTop;
		var div = document.getElementById("coords");
		div.innerHTML = "x: " + x + " y: " + y;
		var radius = 1;
		var fillColor = "#ff0000";
		context6.fillCircle(x, y, radius, fillColor);
		};
		canvas6.onmousedown = function (e) {
		canvas6.isDrawing = true;
		};
		canvas6.onmouseup = function (e) {
		canvas6.isDrawing = false;
		};
		
		
		
		
		
});


//另一个逐渐缩放的动画
var cube = function() {
	
	var canvas;
	var context;
	var x = 0;
	var y = 0;
	
	function currectX() {
		return x = x +1;
	}
	
	function currectY() {
		return y = y + 1;
	}
	
	return {
		
		draw: function() {
			context.fillStyle = getRandomColor();
			context.scale(1.2,1.2);
			context.fillRect(currectX(),currectY(),2,2);
		},
		init: function(myCanvas) {
			canvas = myCanvas;
			context = canvas.getContext("2d");
			context.translate(0,0);
			setInterval(cube.draw, 200);
		}
	};
}();



//构造一个自动转圈动画类
var can = function() {
	 var canvas;
	 var context;
	 
	 return {
		 
		 draw: function() {
			 context.rotate(0.2*Math.PI);
			 var s = getRandomColor();
			 context.fillStyle = s;
			 context.fillRect(10,0,120,20);
		},
		init: function() {
			canvas = document.getElementById("myCanvas4");
			context = canvas.getContext("2d");
			context.translate(200,250);
			setInterval(can.draw, 20);
		}
	 };
}();

function getRandomColor() {
	var r = Math.floor(Math.random()*255)+70;
	var g = Math.floor(Math.random()*255)+70;
	var b = Math.floor(Math.random()*255)+70;
	 var s = 'rgba('+r+','+g+','+b+','+'1)';
	 return s;
}

