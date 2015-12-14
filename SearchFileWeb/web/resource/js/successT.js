onload = function () {
    messageColor();
}
function $(val) {
    return document.getElementById(val);
}
function messageColor() {
    var isOK = $("ok").value;
    //大于0 成功
    if (isOK > 0) {
        $("infoP").className = "messageS";
    } else {
        $("infoP").className = "messageE";
    }
}