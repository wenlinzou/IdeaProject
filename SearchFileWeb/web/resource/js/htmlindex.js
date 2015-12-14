//验证码
function changeImage(img) {
	img.src = img.src + "?" + new Date().getDate();
}
var totalCount = 5;
function verify() {
	
    //var userName = $("#userName").val();
    //$.get("AJAXServer?name="+userName);
    //1获取文本框中的内容//document.getElementById("id");
    //JQuery的查找节点的方式，参数中#加上属性值可以找到一个节点
    //JQuery的方法返回的都是JQuery对象，可以继续在上面执行其他的jquery方法
   // var jqueryObj = $("username");
    //获取节点的值
    //var userName = jqueryObj[0].val();
    
    var username = $("input[name='username']").val(); 
    var password = $("input[name='password']").val(); 
    var wordcheck = $("input[name='wordcheck']").val(); 

    //2将文本框中的数据发送给服务器的servlet
    //在javascript当中一个简单的对象的定义方法
    var obj = {username:username, password: password, wordcheck:wordcheck};

    //使用JQuery的XMLHTTPRequest对象的封装

    $.ajax({
        type: "POST",              //http请求方式
        url: "login.html",    //服务器端url地址
//        data: "username=" + username+"&password="+password,    //发送给服务器短的数据
        data: obj,
        dataType: "xml",           //告诉Jquery返回的数据方式
        success: callback,             //定义交互完成，并且服务器正确返回数据时调用的回调函数
        error:errorfun
    });


}
function errorfun(){
	$("#keywords").html("后台自杀了!!提交失败...");
}
//回调函数
var count = 0;
function callback(data) {
	count++;
	
	
	if(count>totalCount){
		$("#keywords").html("最多输入"+totalCount+"次,已经超出!");
//		$("#login").css("disable","disable");
		$("#login").attr({"disabled":true});

		return;
	}
	
    //3接受服务器端返回的数据
    //需要将data这个dom对象中的数据解析出来
    //首先需要将dom对象转换成Jquery对象
    var jqueryObj = $(data);
    //获取message节点
    var message = jqueryObj.children();
    //获取文本内容
    var text = message.text();
    //4将服务器端返回的数据动态的显示在页面上
    //找到保存结果信息的节点
    var msgObj = $("#keywords");

    if(!goToOther(text)){
    //动态的改变页面中div节点中的内容
    	$(".advanced_search").css("display","block");
    	msgObj.html(text);
	}
  //.search .advanced_search
}
function goToOther(msgObj){
	if(msgObj=="canLogin"){
		window.location.href = "jump.html";
		return true;
	}else{
		return false;
	}
}
//keycode   71 = g G 
var gameKeycount = 0;
var lkey = 0;//76
var okey = 0;//79
var vkey = 0;//86
var ekey = 0;//69
$(document).keyup(function(event){
	 if (event.keyCode == 108 || event.keyCode == 76) {
	        lkey++;
	    }
	    if (event.keyCode == 111 || event.keyCode == 79) {
	        okey++;
	    }
	    if (event.keyCode == 118 || event.keyCode == 86) {
	        vkey++;
	    }
	    if (event.keyCode == 101 || event.keyCode == 69) {
	        ekey++;
	    }
	    if (lkey > 0) {
	        if (okey > 0) {
	            if (vkey > 0) {
	                if (ekey > 0) {
	                    var myurl = location.host;
	                    window.location.href = "http://" + myurl + "/SearchFileWeb/otherInfo/htmlgame/love/index.html";
	                }
	            }
	        }
	    }
	    
	if(event.keyCode ==13){
		$("#submit_search").trigger("click");
		verify();
	}
	if(event.keyCode == 71){
		gameKeycount++;
		if(gameKeycount==5){
			var myurl = location.host;
			window.location.href = "http://"+myurl+"/SearchFileWeb/otherInfo/whiteDiv.html";
		}
	}
	if(event.keyCode ==32){
		spaceKeyCount++;
		if(spaceKeyCount==10){
			var myurl = location.host;
//			myurl+"/input.html";
			//http://localhost:8080/SearchFileWeb/input.html
			window.location.href = "http://"+myurl+"/SearchFileWeb/otherInfo/userRegister.jsp";
		}
	}
});
var spaceKeyCount = 1;