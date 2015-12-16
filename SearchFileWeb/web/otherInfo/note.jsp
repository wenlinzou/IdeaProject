<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <title>记事本</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/note.css"/>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>resource/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>resource/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>resource/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
        <div class="titleInfo">笔记</div>

        <form action="saveNodeInfo.html" method="post">
            <!-- 加载编辑器的容器 -->
            <div  class="ueditorStyleDiv">
            <script id="container" name="content" type="text/plain" class="ueditorStyle">${noteInfo}</script>
            </div>
            <div class="centerDiv">
                <input type="button" name="button1" id="button1" class="btnBack" value="返回" onclick="self.location=document.referrer;">
                <input type="submit" value="保存" class="saveBtn"/>
            </div>

        </form>

    <script type="text/javascript">
        var editor = UE.getEditor('container');
    </script>
</body>
</html>
