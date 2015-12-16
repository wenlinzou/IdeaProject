function checkoutUpload(){
    $("errorinfo").innerHTML = "";
    var fileOneObj = document.getElementsByName("myfile")[0];
    var errorTDObj = $("errorinfoTD");
    var fileOneBool = false;
    var fileOneText = fileOneObj.value;
    fileOneText == ""?fileOneBool==false:fileOneBool=true;

    if(!fileOneBool){
        errorTDObj.className = "errorinfo";
        $("errorinfo").innerHTML = "上传文件不能为空!";
        return false;
    }

    if(fileOneBool){
        //alert("比较文件吧!");
        return  true;
    }
}
function checkCode(){
    $("errorinfo").innerHTML = "";
    var errorTDObj = $("errorinfoTD");
    var codeObj = $("qrtext");
    var codeObjText = codeObj.value;
    if("" == codeObjText){
        errorTDObj.className = "errorinfo";
        $("errorinfo").innerHTML = "输入不能为空!";
        return false;
    }else{
        return true;
    }
}
function $(text){
    return document.getElementById(text);
}

function getFileName(startChar, filepath){
    var textlength = filepath.length;
    var index = filepath.lastIndexOf(startChar);
    var filename = filepath.substring(index+1, textlength);
    return filename;
}


function clickFileOne(){
    var fileOneText = $("uploadOne").value;

    var filename = getFileName("\\", fileOneText);
    $("file1Text").innerHTML = filename;

    if("" == filename){
        $("file1Text").style.color="#D4262E";
        //#C9B9A5
        $("file1Text").style.backgroundColor="#C9B9A5";
    }else{
        $("file1Text").style.backgroundColor="#CAD786";
        $("file1Text").style.color="#FFFFFF";
    }
}



