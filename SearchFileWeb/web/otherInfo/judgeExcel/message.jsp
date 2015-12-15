<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>消息提示</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/message.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/message.js"></script>
</head>
<body>
<a id="top"></a>
<div class="backRight">
    <input type="button" name="button1" id="button1" class="btnBack" value="&lt;&lt;" onclick="self.location=document.referrer;">
</div>

    ${message}
${judgeInfo }
${table0 }
<br />
  	${table1 }  
<a href="#top" class="btnReg centerTop">回到顶部</a>
</body>
</html>
