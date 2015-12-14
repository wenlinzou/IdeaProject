function showDiv() {
	document.getElementById("showdiv").style.display = "";
}
function hiddenDiv() {
	document.getElementById("showdiv").style.display = "none";
}

function convertHtml(){
	$("convertH").className = "convertH";
}

// 切换显示和隐藏
function changeDiv(showdiv) {
	var nodediv = document.getElementById(showdiv);
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}

// 返回首页
function backIndex() {
//	var myurl = location.href;
//	var endIndex = myurl.lastIndexOf('/');
//	var newUrl = myurl.substring(0, endIndex);
//	window.location.href = newUrl+"/input.html";
	window.location.href = "jump.html";
}

// 全选
function selectAll(node) {
	var filelistNodes = document.getElementsByName("filePathList");
	for (var i = 0; i < filelistNodes.length; i++) {
		filelistNodes[i].checked = node.checked;
	}
}

//btn全选
function checkAll(node){
	var oMailNodes = document.getElementsByName("filePathList");
	for(var i=0; i<oMailNodes.length; i++){
		oMailNodes[i].checked = node.checked;
	}
}
//反选
function checkAllByBtn(val){
	var oMailNodes = document.getElementsByName("filePathList");
	for(var i=0; i<oMailNodes.length; i++){
		if(val>1){
			oMailNodes[i].checked = !oMailNodes[i].checked;
		}else{
			oMailNodes[i].checked = val;
		}
	}
}


//更改行样式
var trColorName;
function trColor() {
	var oTabNode = document.getElementById("mySearchInfo");

	var collTrNodes = oTabNode.rows;

	for (var i = 1; i < collTrNodes.length; i++) {
		if (i % 2 == 1)
			collTrNodes[i].className = "mytrOne";
		else
			collTrNodes[i].className = "mytrTwo";
		collTrNodes[i].onmouseover = function() {
			trColorName = this.className;
			this.className = "over";
		}
		collTrNodes[i].onmouseout = function() {
			this.className = trColorName;
		}
	}
}
onload = function() {
	trColor();
}

/* 修改字体 */
function changeFont(selectName) {
	var oNewsText = document.getElementsByName("changeFont");

	for (var i = 0; i < oNewsText.length; i++) {
		oNewsText[i].className = selectName;
	}
	/*
	 * var trNodes = document.getElementsByName("filePathList"); for(var i = 0;
	 * i < trNodes.length; i++){
	 * 
	 * trNodes[i].className = selectName; }
	 */
}
function validate_file(field, alerttxt, selectName) {
	with (field) {
		if (value == null || value == "") {
			$("errorfile").innerHTML = alerttxt;
			$("errorfile").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function $(val) {
	return document.getElementById(val);
}
function validate_disk(diskname, alerttxt, selectName) {
	with (diskname) {
		if (value == null || value == "") {
			$("errordisk").innerHTML = alerttxt;
			$("errordisk").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_form(thisform) {
	with (thisform) {
		var diskwarn = "请输入盘符名称";
		var filewarn = "请输入文件名称";
		if ((validate_disk(diskname, diskwarn, "errordiskfile") == false)
				&& (validate_file(filename, filewarn, "errordiskfile") == false)) {
			diskname.focus();
			return false;
		}
		if (validate_disk(diskname, diskwarn) == false) {
			diskname.focus();
			return false;
		}
		if (validate_file(filename, filewarn) == false) {
			filename.focus();
			return false;
		}
	}
}

function getSelectChecked() {
	var collFiles = document.getElementsByName("filePathList");
	var array = new Array();
	var j = 0;
	for (var i = 0; i < collFiles.length; i++) {
		if (collFiles[i].checked) {
			if (null != collFiles[i].value) {
				array[j] = collFiles[i].value;
				j++;
			}
		}
	}
	
	
	if(j<1){
		var errorNode = $("nochecked");
		errorNode.className = "errorSameName";
		errorNode.innerHTML = "请选中修改的文件";
		return false;
	}else{
		var nodeValue = $("samename").value;
		if(nodeValue==null || nodeValue==""){
			var errorNode = $("nochecked");
			errorNode.className = "errorSameName";
			errorNode.innerHTML = "请输入重复部分名称";
			return false;
		}else{
			$("rename").value = array;
		}
	}
}

