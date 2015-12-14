<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>播放列表</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/videolist.css">
</head>
<body>
	<div>
		<ul>
			<!-- 展现视频列表 -->
			<c:forEach var="file" items="${videolist }">
				<c:if test="${filetype=='video' }">
				<li>
					${file.filename }<br/>
					<video height="500px;" controls="true" src='<%=basePath%>resource/upload/${file.arrfilename }'></video>
				</li>
				</c:if>

				<c:if test="${filetype=='audio' }">
				<li>
					${file.filename }<br/>
					<AUDIO height="500px;" controls="true" src='<%=basePath%>resource/upload/${file.arrfilename }'></AUDIO>
				</li>
				</c:if>
				<c:if test="${filetype=='img' }">
				<li class="clearli">
					<%-- ${file.filename }<br/> --%>
					<img src='<%=basePath%>resource/upload/${file.arrfilename }' alt='${file.filename }'/>
				</li>
				</c:if>
				<c:if test="${filetype=='txt' }">
				<li>
					文档&nbsp;<a href='<%=basePath%>resource/upload/${file.arrfilename }'>${file.filename }</a>
				</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</body>
</html>