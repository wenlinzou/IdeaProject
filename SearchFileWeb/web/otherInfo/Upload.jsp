<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/upload.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/Upload.js"></script>

  </head>
  
  <body>
  <div class="header"></div>

    <div class="updiv">
		<form action="<%=basePath%>uploadFile.html" onsubmit="return checkoutUpload();" method="post"enctype="multipart/form-data">
			<%--<input type="hidden" name="username" />
            <br />--%>
            <%--<input class="upfile" type="file" name="myfile" />
            <br />
            <input class="subtn" type="submit" value="上传" />--%>
			<table>

				<tr>

					<td>
						<label  for="uploadOne" class="labelFile" id="file1Text">选择文件</label>
						<input onchange="clickFileOne();" type="file" name="myfile" id="uploadOne" style="display:none;">
					</td>
				</tr>

				<tr>
					<td><input type="submit" value="上传" class="btnReg"></td>
				</tr>

				<tr><td id="errorinfoTD"><span id="errorinfo" ></span></td></tr>
			</table>
		</form>


		<form action="<%=basePath %>qrcode.html" method="post" onsubmit="return checkCode();">
			<input type="text" name="qrtext" id="qrtext"  class="codeInput"/>
			<input type="submit" value="二维码" class="codeSub"/>
		</form>
	</div>
  </body>
</html>
