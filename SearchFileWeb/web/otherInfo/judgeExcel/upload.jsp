<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Excel内容对比-文件上传</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/uploadxls.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/uploadxls.js"></script>
</head>
<body>
<div class="centerDiv">
    <form action="${pageContext.request.contextPath}/upload.shtml" 	onsubmit="return checkoutUpload()" enctype="multipart/form-data" method="post">
        <input type="hidden" name="username"><br />
        <table >

            <tr>

                <td>
                    <label  for="uploadOne" class="labelFile" id="file1Text">选择文件</label>
                    <input accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" onchange="clickFileOne();" type="file" name="file1" id="uploadOne" style="display:none;">
                </td>
            </tr>
            <tr>

                <td>
                    <label  for="uploadTwo" class="labelFile" id="file2Text">选择文件</label>
                    <input accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" onchange="clickFileTwo();" type="file" name="file2" id="uploadTwo" style="display:none;">
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="比较" class="btnReg"></td>
            </tr>

            <tr><td class="errorinfo"><span id="errorinfo" ></span></td></tr>
        </table>
    </form>

</div>
</body>
</html>