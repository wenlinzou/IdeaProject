// 切换显示和隐藏
function changeDiv(showdiv) {
	var nodediv = document.getElementById(showdiv);
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}
function $(val) {
	return document.getElementById(val);
}
function validate_size(sizename, alerttxt, selectName) {
	with (sizename) {
		if (value == null || value == "") {
			$("errorsize").innerHTML = alerttxt;
			$("errorsize").className = selectName;
			return false;
		} else if(isNaN(value)){
			$("errorsize").innerHTML = "单位(M) 输入的不是数字";
			$("errorsize").className = selectName;
			return false;
		}else{
			return true;
		}
	}
}
function validate_suffixname(suffixname, alerttxt, selectName) {
	with (suffixname) {
		if (value == null || value == "") {
			$("errorsuffix").innerHTML = alerttxt;
			$("errorsuffix").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_dir(dirname, alerttxt, selectName) {
	with (dirname) {
		if (value == null || value == "") {
			$("errordir").innerHTML = alerttxt;
			$("errordir").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_form(thisform) {
	with (thisform) {
		var sizewarn = "单位(M) 请输入尺寸";
		var suffixwarn = "请输入后缀名";
		var dirwarn = "请输入文件存放路径";
		if ((validate_size(sizename, sizewarn, "errorsize") == false)
				&& (validate_suffixname(suffixname, suffixwarn, "errorsuffix") == false)
				&& (validate_dir(dirname, dirwarn, "errordir") == false)) {
			sizename.focus();
			return false;
		}
		if (validate_size(sizename, sizewarn) == false) {
			sizename.focus();
			return false;
		}
		if (validate_suffixname(suffixname, suffixwarn) == false) {
			suffixname.focus();
			return false;
		}
		if (validate_dir(dirname, dirwarn) == false) {
			dirname.focus();
			return false;
		}
	}
}