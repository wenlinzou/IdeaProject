
function trColor() {
    var oTabNode = $("tabid0");

    var collTrNodes = oTabNode.rows;

    for (var i = 1; i < collTrNodes.length; i++) {
        if (i % 2 == 1)
            collTrNodes[i].className = "mytrOne";
        else
            collTrNodes[i].className = "mytrTwo";
    }

    var oTabNode1 = $("tabid1");
    var collTrNodes1 = oTabNode1.rows;
    for (var i = 1; i < collTrNodes1.length; i++) {
        if (i % 2 == 1)
            collTrNodes1[i].className = "mytrOne";
        else
            collTrNodes1[i].className = "mytrTwo";
    }
}
function $(obj){
    return document.getElementById(obj);
}
onload = function() {
    trColor();
}