function checkoutUpload(){
    $("errorinfo").innerHTML = "";
    var fileOneObj = document.getElementsByName("file1")[0];
    var fileTwoObj = document.getElementsByName("file2")[0];

    var fileOneBool = false;
    var fileTwoBool = false;
    var fileOneText = fileOneObj.value;
    var fileTwoText = fileTwoObj.value;
    fileOneText == ""?fileOneBool==false:fileOneBool=true;
    fileTwoText == ""?fileTwoBool==false:fileTwoBool=true;

    if(!fileOneBool && !fileTwoBool){
        $("errorinfo").innerHTML = "比较文件不能为空";
        return false;
    }
    if((fileOneBool&&!fileTwoBool) || (!fileTwoBool&&fileTwoBool)){
        $("errorinfo").innerHTML = "请再比较一个文件!";
        return false;
    }
    var fileOneSuffix = getFileName(".", fileOneText);
    var fileTwoSuffix = getFileName(".", fileTwoText);
    var isFileOneSuffixRight = false;
    var isFileTwoSuffixRight = false;
    if("xls"==fileOneSuffix || "xlsx"==fileOneSuffix){
        isFileOneSuffixRight = true;
    }
    if("xls"==fileTwoSuffix || "xlsx"==fileTwoSuffix){
        isFileTwoSuffixRight = true;
    }
    if((isFileOneSuffixRight&&!isFileTwoSuffixRight) || (!isFileOneSuffixRight&&isFileTwoSuffixRight)){
        $("errorinfo").innerHTML = "文件格式不正确!";
        return false;
    }
    if(!isFileOneSuffixRight && !isFileTwoSuffixRight){
        $("errorinfo").innerHTML = "文件格式不正确!";
        return false;
    }
    if(fileOneText == fileTwoText){
        $("errorinfo").innerHTML = "不能为同一个文件!";
        return false;
    }
    if(fileOneBool && fileTwoBool){
        //alert("比较文件吧!");
        return  true;
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
    var fileSuffix = getFileName(".", fileOneText);
    $("file1Text").innerHTML = filename;

    if("xls"!=fileSuffix && "xlsx"!=fileSuffix){
        $("file1Text").style.color="#D4262E";
        //#C9B9A5
        $("file1Text").style.backgroundColor="#C9B9A5";
    }else{
        $("file1Text").style.backgroundColor="#CAD786";
        $("file1Text").style.color="#FFFFFF";
    }
}
function clickFileTwo(){
    var fileTwoText = $("uploadTwo").value;

    var filename = getFileName("\\", fileTwoText);
    var fileSuffix = getFileName(".", fileTwoText);
    $("file2Text").innerHTML = filename;

    if("xls"!=fileSuffix && "xlsx"!=fileSuffix){
        $("file2Text").style.color="#D4262E";
        $("file2Text").style.backgroundColor="#C9B9A5";
    }else{
        $("file2Text").style.backgroundColor="#CAD786";
        $("file2Text").style.color="#FFFFFF";
    }
}


