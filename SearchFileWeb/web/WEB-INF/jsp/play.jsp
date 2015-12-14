<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>播放页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/play.css">
	<script type="text/javascript" src="<%=basePath%>resource/js/play.js"></script>
</head>

<body>

<!-- split file -->
	<div onclick="changeDiv('splitDiv')">
		${playUrl}
	</div>
	<div id="splitDiv" style="display:none;">
		<form action="split.html" method="post" onsubmit="return validate_form(this)">
			<input type="hidden" name="splitFilePath" value="${playUrl }"/>
			<table>
			<tr>
				<td class="trRight">分解文件大小:</td>
				<td><input type="text" name="sizename"/></td>
				<td id="errorsize"></td>
			</tr>
			<tr>
				<td class="trRight">分解文件后缀:</td>
				<td><input type="text" name="suffixname"/></td>
				<td id="errorsuffix"></td>
			</tr>
			<tr>
				<td class="trRight">存放位置:</td>
				<td><input type="text" name="dirname"/></td>
				<td id="errordir"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="分解"/></td>
				<td></td>
			</tr>
			</table>
		</form>
	</div>
	<!-- split file end -->
	
	<div>
		<form action="mergeFile.html" method="post">
			<input type="hidden" name="splitFilePath" value="${playUrl }"/>
			<c:if test="${suffixStr=='.properties' }"><input type="submit" value="还原"></c:if>
			
		</form>	
	</div>
	<!-- merge File end -->
	
	<!-- download -->
	<div>
		<form action="download.html" method="post">
			<input type="hidden" name="filepath" value='${playUrl }'/>
			<input type="submit" value="下载"/>
		</form>
	</div>
	<!-- download end-->
	
	<!-- upload file -->
	<div>
		<form action="uploadFile.html" method="post"enctype="multipart/form-data">
			<input type="text" name="username" />
            <br />
            <input type="file" name="myfile" />
            <br />
            <input type="submit" />
		</form>
	</div>
	
	
	<%-- <div>
		<video src="${playUrl}" width="1000" height="500" controls="controls">您的浏览器不支持此种视频格式。</video> 
	</div> --%>
	
	<%
		String playpath = request.getParameter("playUrl");
		String iplay = (String) session.getAttribute("iPlay");
	%>

	<input type="hidden" value="<%=iplay%>" id="playinfos" />
	<div class="hiddenplay">
		<input type="file" id="file" onchange="onInputFileChange()">
		<video id="audio_id" controls autoplay loop>Your browser can't
		support HTML5 Audio</video>
	</div>

	<div>
		<%-- <input type="button"  onclick="playMyVideo()" value="播放"/>
		<input type="file" id="playinfo" src="<%=iplay%>"/>
		<video id="paly_id" controls autoplay loop>Your browser can't support HTML5 Audio</video>   --%>
	</div>

	<script>
		var count = 1;
		window.onload = function() {
			count++;
			var nodediv = document.getElementById("audio_id");
			nodediv.style.display = "none";
		}
		function onInputFileChange() {
			if (count > 1) {
				var nodediv = document.getElementById("audio_id");
				nodediv.style.display = "block";
			}
			var file = document.getElementById('file').files[0];
			var url = URL.createObjectURL(file);
			console.log(url);
			document.getElementById("audio_id").src = url;
		}

		function playMyVideo() {
			var file = document.getElementById('playinfos');
			alert(file);
			var url = URL.createObjectURL(file.files[0]);
			console.log(url);
			document.getElementById("paly_id").src = url;
		}
	</script>

	<hr />
	<!-- 图片 to 字符画 -->
	<%
	String showIplay = iplay;
	int endIndex = iplay.lastIndexOf(".");
	if (endIndex != -1) {
		String suffixTemp = iplay.substring(endIndex);
		String iplayname = iplay.substring(0, endIndex);
		StringBuilder sb = new StringBuilder();
		iplay = sb.append(iplayname).append(suffixTemp.toLowerCase()).toString();
	
		if(iplay.endsWith(".jpg") || iplay.endsWith("gif") || iplay.endsWith(".png")){
	%>
		图片 to 字符画:<a href="<%=basePath%>asciimg.html?imgpath=<%=showIplay%>"><%=showIplay%></a>
	<%
		}
	%>
	
	
	<!-- html to doc -->
	<%
		if(iplay.endsWith(".html")){
	%>
		html to doc:<a href="<%=basePath%>html2doc.html?htmlpath=<%=showIplay%>"><%=showIplay%></a>
		<%
		}
	}
	%>
	
	<!-- video to show -->
	<%
		if(iplay.endsWith(".mp4")){
	%>
		<%-- <video height="500px;" src="${playUrl }" controls="true"></video> --%>
		<video height="500px;" src="<%=basePath%>resource/video/t1-ui.mp4" controls="true"></video>
	<%	
		}
	
	%>

</body>
</html>
