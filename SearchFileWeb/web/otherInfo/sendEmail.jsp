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

<title>发送邮件</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/sendEmail.css">
<script type="text/javascript" src="<%=basePath %>resource/js/sendEmail.js"></script>

	<script type="text/javascript" charset="utf-8" src="<%=basePath %>resource/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>resource/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>resource/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>

<body>
	<div class="sendmailDiv">
	<form action="sendemail.html" method="post"  onsubmit="return validate_form(this)">
		<table id="fileid">
			<tr>
				<td class="tdwordRight">发件邮箱</td><td><input type="text" name="username" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">邮箱密码</td><td><input type="password" name="password" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">收邮人</td><td><input type="text" name="sendToEmailName" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">标题</td><td><input type="text" name="subject" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">内容</td><td>
				<!-- <input type="text" name="content"/> -->
				<textarea rows="10px" cols="30px" name="content" class="inputStyle"></textarea>
				<script id="editor" type="text/plain" style="width:717px;height:154px;"></script>
				</td>
			</tr>
			
			<script type="text/javascript">

		    //实例化编辑器
		    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		    var ue = UE.getEditor('editor');
			</script>
			
			
	    	<tr>
	    		<td class="tdwordRight">附件</td>
	    		<td>
	    			<input type="file" name="filename" value="浏览"/>
	    		</td>
	    	</tr>
	    	
	    	<!-- 附件 -->
			<tr>
				<td></td>
				<td><a href="javascript:void(0)" onclick="addFile()">添加附件</a></td>
			</tr>
			
			<tr>
				<td> </td><td><!-- <input type="submit" value="发送"/> --></td>
			</tr>
			
			<tr><td></td><td id="errorinfo"></td></tr>
		</table>
		<input type="submit" value="发送"/>
	</form>
	
	</div>
	<br>
</body>
</html>
