
//if linux hidden disk input tag
/*window.onload=checkOsName;
function checkOsName(){
	var osName = getOsName();
	if('Win7'==osName){
		alert(osName);
	}else{
		var diskObj = document.getElementsByName('diskname')[0];
		diskObj.type='hidden';
		
		//document.getElementById('username').focus();
	}
}*/

function $(val) {
	return document.getElementById(val);
}
function trunOffPC() {
	var collRadios = document.getElementsByName("turnoff");
	var j = 0;
	for ( var i = 0; i < collRadios.length; i++) {
		if (collRadios[i].checked) {
			if (confirm("确认此操作 ?")) {
				j = j + 1;
				$("turnoffID").value = collRadios[i].value;
			} else {
				return false;
			}
		}
	}
	if (j < 1) {
		$("errormsg").innerHTML = "未选中";
		$("errormsg").className = "errormsgStyle";
		return false;
	}
}

function cancelOff() {

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
function validate_folder(foldername, alerttxt, selectName) {
	with (foldername) {
		if (value == null || value == "") {
			$("errorfolder").innerHTML = alerttxt;
			$("errorfolder").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_form(thisform) {
	with (thisform) {
		var osName = $('osname').value;
		//Windows
		if('Windows'==osName){
			var diskwarn = "请输入盘符名称";
			if (validate_disk(diskname, diskwarn, "errordisk") == false) {
				diskname.focus();
				return false;
			}
		}else{
			//linux
			var folderwarn = "请输入文件夹名称";
			if (validate_folder(foldername, folderwarn, "errorfolder") == false) {
				foldername.focus();
				return false;
			}
		}
	}
	// setInterval("startTime()",1000);
}

//osname

// 写一个计时的js
var useTime = 0;
function startTime() {
	var useTime = document.getElementById("useTime").innerHTML;
	useTime++;
	document.getElementById("useTime").innerHTML = useTime;
}
// get os name
function getOsName() {
	var sUserAgent = navigator.userAgent;
	var isWin = (navigator.platform == "Win32")	|| (navigator.platform == "Windows");
	var isMac = (navigator.platform == "Mac68K")|| (navigator.platform == "MacPPC")	|| (navigator.platform == "Macintosh")|| (navigator.platform == "MacIntel");
	if (isMac){
		return "Mac";
	}
	var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
	if (isUnix){
		return "Unix";
	}
	var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
	if (isLinux){
		return "Linux";
	}
	if (isWin) {
        var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
        if (isWin2K) return "Win2000";
        var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
        if (isWinXP) return "WinXP";
        var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
        if (isWin2003) return "Win2003";
        var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
        if (isWinVista) return "WinVista";
        var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
        if (isWin7) {
        	return "Win7";
        }
        
    }
    return "other";
}