/*
 *  实现自己的JS
 */

/** 是否打印日志 */
var isDebugLog = true;
// 在share.jsp中定义了一个“ctxPath”变量

/*$(document).ready(function(){
    myInit();
});*/
window.onload = function(){
    myInit();
}

function myInit() {
    printLog("init");
    menuInit();
}

function printLog(msg) {
    if (isDebugLog) {
        console.log(msg);
    }
}

function toPageUrl(url, topage, pageSize) {
    return ctxPath + url + "?topage=" + topage + "&pageSize=" + pageSize;
}

/**
 * 根据当前页面，判断页面那个item是选中状态
 */
function menuInit() {
    var url = location.href;
    printLog("menuInit url:" + url);
    var au = actualUrl(url);
    printLog("actualUrl url:" + au);

    dealMenu(au, $('#sidebar-menus').children('li'));
}

/**
 * 这里处理的页面情况有两种，一种是li下面直接跟着“a”；一种是li下面跟着“ul”，然后“ul”下面才是想要的“li a”
 * @param expectUrl
 * @param childrenLi
 * @returns {boolean} 是否有匹配到url
 */
function dealMenu(expectUrl, childrenLi) {
    for (var i=0; i<childrenLi.length; i++) {
        var childrenItem = $(childrenLi[i]);
        printLog("item: " + childrenItem.html());
        var aChild = childrenItem.children('a');
        if (aChild.length > 0) {
            for (var j=0; j<aChild.length; j++) {
                printLog($(aChild).attr('href'));
                if (expectUrl == $(aChild).attr('href')) {
                    childrenItem.addClass('active');
                    return true;
                }
            }
        }

        var ulChile = childrenItem.children('ul');
        printLog("ulChile:" + ulChile.length);
        if (ulChile.length > 0) {
            for (var j=0; j<ulChile.length; j++) {
                // 递归调用
                if (dealMenu(expectUrl, $(ulChile[j]).children('li'))) {
                    childrenItem.addClass('active');
                }
            }
        }
    }
    return false;
}

/**
 * 截取有用的url，如“http://127.0.0.1:9696/admin/demo1#abc?a=1”，结果为“/admin/demo1”
 * @param url {string}
 * @returns {string}
 */
function actualUrl(url) {
    var urlIndex = -1;
    var retUrl = url;

    var httpUrlPre = "http://";
    if (retUrl.indexOf(httpUrlPre) == 0) {
         retUrl = retUrl.substr(httpUrlPre.length);
    }

    var rootUrl = ctxPath + '/';
    urlIndex = retUrl.indexOf(rootUrl);
    if (urlIndex >= 0) {
        retUrl = retUrl.substr(urlIndex);
    }

    urlIndex = retUrl.indexOf("?");
    if (urlIndex >= 0) {
        retUrl = retUrl.substr(0, urlIndex);
    }

    urlIndex = retUrl.indexOf("#");
    if (urlIndex >= 0) {
        retUrl = retUrl.substr(0, urlIndex);
    }
    return retUrl;
}