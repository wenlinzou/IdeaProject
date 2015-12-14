function $(val){
	return document.getElementById(val);
}
function validate_imgpath(imgpath, alerttxt, selectName) {
	with (imgpath) {
		if (value == null || value == "") {
			$("errorinfo").innerHTML = alerttxt;
			$("errorinfo").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_form(thisform){
	with (thisform) {
		var imgpathwarn = "请输入图片地址";
		if ((validate_imgpath(imgpath, imgpathwarn, "errorinfo") == false)) {
			imgpath.focus();
			return false;
		}
	}
}