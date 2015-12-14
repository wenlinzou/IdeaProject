var count = 0;
function $(val){
	return document.getElementById(val);
}
function addFile() {
	count++;
	var oTabNode = document.getElementById("fileid");
	
	var oTrNode = oTabNode.insertRow();

	var oTdNode_file = oTrNode.insertCell();
	var otdNode_del = oTrNode.insertCell();

	/*oTdNode_file.innerHTML = "<input type='file' name=filename"+count+"/>";
	otdNode_del.innerHTML = "<img src='1.jpg' alt='删除附件' onclick='deleteFile(this)'/>";*/
	
	oTdNode_file.innerHTML = "附件";
	oTdNode_file.className = "tdwordRight";
	otdNode_del.innerHTML = "<input type='file' name=filename"+count+"/><img src='1.jpg' alt='删除附件' onclick='deleteFile(this)'/>";
	// otdNode_del.innerHTML = '<a href="javascript:void(0)"
	// onclick="deleteFile(this)">删除附件</a>';
	alert(count);
}
function deleteFile(node) {
	var oTrNode = node.parentNode.parentNode;
	oTrNode.parentNode.removeChild(oTrNode);
}
/*获取上传文件的路径*/


/*输入验证*/
function validate_username(username, alerttxt, selectName) {
	with (username) {
		if (value == null || value == "") {
			$("errorinfo").innerHTML = alerttxt;
			$("errorinfo").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_password(password, alerttxt, selectName) {
	with (password) {
		if (value == null || value == "") {
			$("errorinfo").innerHTML = alerttxt;
			$("errorinfo").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_sendToEmailName(sendToEmailName, alerttxt, selectName) {
	with (sendToEmailName) {
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
		var usernamewarn = "请输入发件邮箱名";
		var passwordwarn = "请输入密码";
		var sendToEmailNamewarn = "请输入收件邮箱名";
		var v_username =  validate_username(username,usernamewarn,"errorinfo");
		var v_password =  validate_password(password,passwordwarn,"errorinfo");
		var v_sendToEmailName = validate_sendToEmailName(sendToEmailName,sendToEmailNamewarn,"errorinfo");
		
		if ((v_username==false)
				&& (v_password==false)
				&&(v_sendToEmailName==false)) {
			validate_username(username,usernamewarn,"errorinfo");
//			v_username;
			username.focus();
			return false;
		}
		
		if(validate_username(username,usernamewarn,"errorinfo")==false){
			username.focus();
			return false;
		}
		if(validate_password(password,passwordwarn,"errorinfo")==false){
			password.focus();
			return false;
		}
		if(validate_sendToEmailName(sendToEmailName,sendToEmailNamewarn,"errorinfo")==false){
			sendToEmailName.focus();
			return false;
		}
	}
}