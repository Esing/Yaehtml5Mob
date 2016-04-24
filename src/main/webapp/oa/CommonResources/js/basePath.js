// JS获取根路径
function getContextPath () {
    // 获取当前网址，如：http://localhost:8080/ssm/index.jsp
    var currentPath = window.document.location.href;
    // 获取主机地址之后的目录，如： /ssm/index.jsp
    var pathName = window.document.location.pathname;
    var pos = currentPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8080
    var localhostPath = currentPath.substring(0, pos);
    // 获取带"/"的项目名，如：/ssm
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPath + projectName);
}
// JS获取URL参数集合  url解密：decodeURIComponent(String) url加密：encodeURIComponent(String)
function getRequest() {
	var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
	    var str = url.substr(1);
	    strs = str.split("&");
	    for(var i = 0; i < strs.length; i ++) {
	        theRequest[strs[i].split("=")[0]]=(decodeURIComponent(strs[i].split("=")[1]));
	    }
    }
    return theRequest;
}