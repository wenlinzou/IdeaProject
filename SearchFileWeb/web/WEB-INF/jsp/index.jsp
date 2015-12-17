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
<title>搜索文件</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/index.css">
<script type="text/javascript" src="<%=basePath%>resource/js/index.js"></script>
</head>
<body>

<c:if test="${user==null }">
		<div class="noLogUser"><a href="<%=basePath%>indexLogin.jsp">请跳转至登陆页</a></div>
</c:if>
<c:if test="${user!=null }">

<div class="left">

	<c:if test="${osName=='Windows'}">
		<div class="divTurnoff" onclick="changeDiv('turnoffpc')">关机</div>
		<div style=";">${turnOffSuccess }
		</div>
		<div id="turnoffpc" style="display:none;">
			<form action="turnOff.html" method="post" onsubmit="return trunOffPC()">
				<input type="hidden" id="turnoffID" name="turnoffTime"/>
				<table>
					<tr><td>
				<input type="radio" name="turnoff" value="0.02">立即关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="2">2分钟后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="5">5分钟后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="10">10分钟后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="20">20分钟后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="30">半小时后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="60">1小时后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="120">2小时后关机</td>
					</tr>
					<tr><td>
				<input type="radio" name="turnoff" value="180">3小时后关机</td>
					</tr>

					<tr><td>
				<input type="radio" name="turnoff" value="720">12小时后关机</td>
					</tr>


				<TR >
				<c:if test="${turnOffSuccess!=null }">
					<td class="cancelOff"><input type="radio" name="turnoff" value="-1" >取消关机</td>
				</c:if>
				</TR>
					<tr><td id="errormsg"></td></tr>
					<tr><td>
				<input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>
	

</div>	
<!-- end left -->
	
<div class="middle">
	<div>
		<p>
		${fullStr }
		</p>
		<c:if test="${fullStr==null }">
		
		<div class="searchCenterDiv">
	<h2 class="mycenter"></h2>
	<span id="useTime" class="numStyle"></span>
	<input type="hidden" id="osname" value='${osName}'/>
		<form action="search.html" method="post"  onsubmit="return validate_form(this)">
			<table>
			<tr><td></td><td class="mycenter">搜索文件</td><td></td></tr>
			<c:if test="${osName=='Windows'}">
			<tr>
				<td class="cueInfo">
			指定盘符&nbsp;
				</td>
				<td>
			<input type="text" class="inputSearch" name="diskname" value="${iFile.diskname }"/>
				</td>
				<td id="errordisk"></td>
			</tr>
			</c:if>
			
			<tr>
				<td class="cueInfo">
			文件夹名&nbsp;
				</td>
				<td>
			<input type="text" class="inputSearch" name="foldername" value="${iFile.foldername }"/>
				</td>
				<td id="errorfolder"></td>
			</tr>
			<tr>
				<td class="cueInfo">
			文件名&nbsp;
				</td>
				<td>
			<input type="text"  class="inputSearch" name="filename" value="${iFile.filename }"/>
				</td><td></td>
			</tr>
			<tr>
				<td class="cueInfo">
			文件后缀&nbsp;
				</td>
				<td>
			<input type="text" class="inputSearch" name="suffix" value="${iFile.suffix }"/>
				</td><td></td>
			</tr>
			<tr>
				<td></td>
				<td>
			<input type="submit" value="搜索" class="btnSearch"/>
				</td><td></td>
			</tr>
			</table>
		</form>
		</div>

		</c:if>
		
	</div>
</div>	
<!-- end middle -->

<!-- right -->
	<div class="divRightStyle">
		<ol>
			<li><a href="<%=basePath %>otherInfo/CarInfo.html" target="_blank">拿照详细经验</a></li>
			<li><a href="<%=basePath %>otherInfo/htmlTransPdf.jsp" target="_blank">HTML转成PDF</a></li>
			<li><a href="<%=basePath %>otherInfo/sendEmail.jsp" target="_blank">发送邮件</a></li>
			<li><a href="<%=basePath %>otherInfo/asciiImg.jsp" target="_blank">打印字符画</a></li>
			<%-- <li><a href="<%=basePath %>otherInfo/indexShowImg.html" target="_blank">查看图片</a></li> --%>
			<li><a href="<%=basePath %>videolist.html?filetype=img" target="_blank">浏览图片</a></li>
			<li><a href="<%=basePath %>videolist.html?filetype=mp4" target="_blank">查看视频</a></li>
			<li><a href="<%=basePath %>videolist.html?filetype=mp3" target="_blank">播放音频</a></li>
			<li><a href="<%=basePath %>videolist.html?filetype=txt" target="_blank">查看文本</a></li>
		</ol>
	</div>
	
</c:if>	
</body>
</html>