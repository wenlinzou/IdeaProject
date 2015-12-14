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

<title>HTML转换为PDF</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/htmlTransPdf.css">
	<script type="text/javascript" src="<%=basePath%>resource/js/html2Local.js"></script>
</head>

<body>
	<div>
		<form action="<%=basePath %>html2pdf.html" method="post"  onsubmit="return validate_form(this)">
		<table>
			<tr><td>输入网页地址:</td></tr>
			<tr><td><input type="text" name="htmlUrl" class="inputStyle"/></td></tr>
			<tr><td>输入存放位置:</td></tr>
			<tr><td><input type="text" name="pdfPath" />f:/txt 下)</td></tr>
			<tr><td id="errorinfo"></td></tr>
			<tr><td><input type="submit" value="提交"/></td></tr>
			<!-- <tr><td>
			<select>
				<option >1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				</select></td></tr> -->
		</table>
		</form>
	</div>
	<br>
</body>
</html>
