<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	//验证码
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/indexLogin.css">
	
	<script type="text/javascript" src="<%=basePath%>resource/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/indexLogin.js"></script>
  </head>
  
  <body>
    <center>
    <div class="hero">
    <!-- 新增一个div将用户登陆的这块信息挤到下面 -->
    <div class="head"></div>

    <!-- <form html="login.html" method="post"> -->
    	<div class="tabDiv">
	    	<table class="trFont" border="0px">
	    		<tr class="firstTr"></tr>
	    		<tr class="firstTr"><td colspan="2"></td></tr>
	    		<tr><td style="text-align: center;font-size:22px;" class="loginWords">用户登录</td></tr>
	    		<tr class="secondTr"><td colspan="2"></td></tr>
		    	<tr>
				<td class="inputTd" colspan="2"><input type="text" class="trInput" id="username" name="username" value="${user.username }"/></td>
				</tr>
				<tr>
					<td  class="inputTd">
						<input type="text"  class="trInput" id="password" name="password" value="${user.password }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" >
					<input type="text" class="wordCheck" name="wordcheck" id="wordcheck"/>
					<img alt="change" src="<%=basePath %>wordcheck.html"onclick="changeImage(this)" style="cursor: hand;display: block;float: right;height:40px;">
					</td>
				</tr>
				
				<tr>
					<td class="errorStyle" id="msg" colspan="2"></td>
				</tr>
				<tr >
					<td class="loginBtn" colspan="2">
						<input type="button" class="login-btn" onclick="verify()" value="登陆" id="login"/>
					</td>
				</tr>
			</table>
		</div>
	<!-- </form> -->
	
	</div>
	</center>
  </body>
</html>
