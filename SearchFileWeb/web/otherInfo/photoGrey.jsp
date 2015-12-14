<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title><c:if test="${title==null }">显示图片</c:if><c:if test="${title!=null }">${title }</c:if></title>
</head>
<body>
<div>
	<c:if test="${existPhoto==null }">
		<c:if test="${filename==null }">
			<a id="jumppath" href="" onclick="getImgPath(this);"><img id="imgPath" src="<%=basePath %>resource/images/s6_fb.jpg" alt="beauty"/></a><!-- s6_fbCopy.jpg -->
		</c:if>
		<c:if test="${filename!=null }">
			<a id="jumppath" href="" onclick="getImgPath(this);"><img id="imgPath" src="<%=basePath %>resource/images/${filename }.jpg" alt="beauty"/></a><!-- s6_fbCopy.jpg -->
		</c:if>
	</c:if>
	<c:if test="${existPhoto!=null }">
	<a id="jumppath" href="" onclick="getImgPath(this);"><img id="imgPath" src="<%=basePath %>resource/images/${filename }Copy.jpg" alt="beauty"/></a>
	</c:if>
</div>

<div id="showLoopPhoto"><input type="button" onclick="showPhoto();" value="添加图片" id="addBtn"/></div>
<script type="text/javascript">
    function getImgPath(thisAT){
//        var imgObj = document.getElementById("imgPath");
//			o.getElementByTagName('div')[0]
//        var imgObj = thisImg.getElementsByTagName("img")[0];
        var imgObj = thisAT.childNodes[0];
        //document.getElementById("father").childNodes[0]
        
        var imgpath = imgObj.src;
//        var aObj = document.getElementById("jumppath");
        var aObj = thisAT;
        aObj.href = '<%=basePath%>photoGrey.html?imgPath='+imgpath;
    }
    function $(input){
    	return document.getElementById(input);
    }
    function showPhoto(){
    	for(var i=1;i<=6;i++){
    		var divObj = $("showLoopPhoto");
    		var aT = document.createElement("a");
    		var imgT = document.createElement("img");
    		
    		aT.setAttribute("id","jumppath"+i);
    		aT.setAttribute("onclick","getImgPath(this);");
    		aT.setAttribute("href","");
    		
    		imgT.setAttribute("id","imgPath"+i);
    		imgT.src="<%=basePath %>resource/images/"+i+".jpg";
    		divObj.appendChild(aT);
    		aT.appendChild(imgT);
    		
    		var addBtn = $("addBtn");
    		addBtn.type = "hidden";
    	}
    }
</script>
</body>
</html>
