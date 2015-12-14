function $(val){
	return document.getElementById(val);
}
function validate_url(htmlUrl, alerttxt, selectName) {
	with (htmlUrl) {
		if (value == null || value == "") {
			$("errorinfo").innerHTML = alerttxt;
			$("errorinfo").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_pdf(pdfPath, alerttxt, selectName) {
	with (pdfPath) {
		if (value == null || value == "") {
			$("errorinfo").innerHTML = alerttxt;
			$("errorinfo").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_form(thisform) {
	with (thisform) {
		var urlwarn = "请输入网页地址";
		var pdfwarn = "请输入存放位置";
		if(validate_url(htmlUrl,urlwarn,"errorinfo")==false){
			htmlUrl.focus();
			return false;
		}
		if(validate_pdf(pdfPath,pdfwarn,"errorinfo")==false){
			pdfPath.focus();
			return false;
		}
	}
}