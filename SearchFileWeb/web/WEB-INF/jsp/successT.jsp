<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${title }</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/successT.css">
<script type="text/javascript" src="<%=basePath%>resource/js/successT.js"></script>
</head>

<body>
	<input type="hidden" value="${ok}" id="ok"/>
	<p id="infoP">${message }</p>
	<br>
	<center>
		<span id="time" class="numStyle">6</span>秒种后自动跳转,如果不跳转,请点击下面链接
		<script language="JavaScript1.2" type="text/javascript">
			//  Place this in the 'head' section of your page.  

			function delayURL(url) {
				var delay = document.getElementById("time").innerHTML;
				if (delay > 0) {
					delay--;
					document.getElementById("time").innerHTML = delay;
				} else {
					window.top.location.href = url;
				}
				setTimeout("delayURL('" + url + "')", 1000);

			}
		//-->
		</script>

		<a href="<%=basePath%>">返回首页</a>
		<script type="text/javascript">
			delayURL("jump.html");
		</script>

	</center>
	<div style="display:none;">
		<script type="text/javascript">
			function myClick(){
				setTimeout("realMethod()",2000);
				//setInterval("function()", 3000);//每隔多久就执行
			}
			function realMethod(){
				alert("ok this is me");
			}
		</script>
		<a href="javascript:void(0);" onclick="myClick()" >href跳转</a>
	</div>
</body>
</html>
