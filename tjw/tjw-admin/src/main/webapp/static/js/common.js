/*
 *  实现自己的JS
 */

/** 是否打印日志 */
var isDebugLog = true;
// 在share.jsp中定义了一个“ctxPath”变量

// 基础工具类
/**
 * 生成完整的url
 */
function createCompleteUrl(shortUrl) {
    return ctxPath + shortUrl;
}

function isHasText(str) {
    return isNotNull(str) && str != '';
}

function isNull(obj) {
    return obj == undefined || obj == null;
}

function isNotNull(obj) {
    return !isNull(obj);
}

$(document).ready(function(){
    myInit();
});
// window.onload = function () {
//     myInit();
// }

function myInit() {
    printLog("init");
    menuInit();

    try {
        // jQuery配合input-mask使用
        $("[data-mask]").inputmask();
    }
    catch (err) {
        printLog("inputmask error:" + err.description );
    }

    // 扩展select标签，增加init-value，用于初始化数据
    var allSelects = $("select");
    printLog("allSelects length:" + allSelects.length);
    for (var i=0; i<allSelects.length; i++) {
        var selectItem = $(allSelects[i]);
        if (isNotNull(selectItem.attr('init-value'))) {
            var selectOptions = selectItem.children('option');
            for (var j=0; j<selectOptions.length; j++) {
                if ($(selectOptions[j]).val() == selectItem.attr('init-value')) {
                    $(selectOptions[j]).attr('selected', 'selected');
                }
            }
        }
    }
}

/**
 * 在当前也打开窗口
 * @param shortUrl
 */
function selfOpen(shortUrl) {
    window.open(createCompleteUrl(shortUrl), "_self");
}

function printLog(msg) {
    if (isDebugLog) {
        console.log(msg);
    }
}

/**
 * 构造分页查询url
 * @param shortUrl
 * @param topage
 * @returns {string}
 */
function toPageUrl(shortUrl, topage) {
    return createCompleteUrl(shortUrl) + "?topage=" + topage + "&pageSize=" + $('#pageSize').val();
}

/**
 * 根据当前页面，判断页面那个item是选中状态
 */
function menuInit() {
    var url = location.href;
    //printLog("menuInit url:" + url);
    var au = cutShortUrl(url);
    //printLog("cutShortUrl url:" + au);

    dealMenu(au, $('#sidebar-menus').children('li'));
}

/**
 * 这里处理的页面情况有两种，一种是li下面直接跟着“a”；一种是li下面跟着“ul”，然后“ul”下面才是想要的“li a”
 * @param expectUrl
 * @param childrenLi
 * @returns {boolean} 是否有匹配到url
 */
function dealMenu(expectUrl, childrenLi) {
    for (var i = 0; i < childrenLi.length; i++) {
        var childrenItem = $(childrenLi[i]);
        //printLog("item: " + childrenItem.html());
        var aChild = childrenItem.children('a');
        if (aChild.length > 0) {
            for (var j = 0; j < aChild.length; j++) {
                printLog($(aChild).attr('href'));
                if (expectUrl == $(aChild).attr('href')) {
                    childrenItem.addClass('active');
                    return true;
                }
            }
        }

        var ulChile = childrenItem.children('ul');
        //printLog("ulChile:" + ulChile.length);
        if (ulChile.length > 0) {
            for (var j = 0; j < ulChile.length; j++) {
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
function cutShortUrl(url) {
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

/**
 * 获取请求url中的参数值
 * @param name
 * @returns {String}
 */
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

/**
 * form表单Ajax请求。
 * @param {Object} obj 包含字段：formId、type、shortUrl，success、error
 * @constructor
 */
function ajaxForm(obj) {
    var $formId = obj.formId;

    if (!isHasText($formId)) {
        callbackForError(obj, "formId为空");
    }
    obj.data = $('#' + $formId).serialize();
    ajax(obj);
}

/**
 * 异步Http请求
 * @param {Object} obj 包含字段：data(Object)、type(GET、POST)、shortUrl，success(function，为空则提示默认)、error(function，为空则提示默认)
 * @constructor
 */
function ajax(obj) {
    var $data = obj.data;
    var $type = obj.type;
    var $shortUrl = obj.shortUrl;

    if (!isHasText($type)) {
        $type = 'POST';
    }
    if (!isHasText($shortUrl)) {
        callbackForError(obj, '请求不能为空');
        return;
    }
    if (isNull($data)) {
        $data = {};
    }
    var url = createCompleteUrl($shortUrl);
    if (url.indexOf("?") >= 0) {
        url = url + "&";
    }
    else {
        url = url + "?";
    }
    url = url + "returntype=json";

    $.ajax({
        url: url,
        data: $data,
        type: $type,
        success: function (retData) {
            if (retData == null) {
                callbackForError(obj, "返回数据为空");
            }
            else {
                if (retData.status == 10000) {
                    callbackForSuccess(obj, retData.result);
                }
                else if (retData.status == 10002) {
                    selfOpen('/auth/login?msg=' + encodeURI('登录超时')
                        + '&redirect_url=' + encodeURI(window.location));
                }
                else {
                    callbackForError(obj, retData.msg);
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (!navigator.onLine) {
                callbackForError(obj, "断网了，请检查网络");
            }
            else {
                callbackForError(obj, "请求链接有误或服务异常");
            }
        }
    });
}

/**
 * 成功回调，与ajaxForm、ajax搭配使用
 * @param obj
 * @param data
 */
function callbackForSuccess(obj, data) {
    if (obj.success instanceof Function) {
        obj.success(data);
    }
    else {
        showSuccessTips();
    }
}

/**
 * 失败回调，与ajaxForm、ajax搭配使用
 * @param obj
 * @param msg
 */
function callbackForError(obj, msg) {
    if (obj.error instanceof Function) {
        obj.error(msg);
    }
    else {
        showErrorTips(msg);
    }
}

function findInArray(array, data) {
    if (isNull(array)) {
        return false;
    }
    if (array instanceof Array) {
        for (var i=0; i<array.length; i++) {
            if (array[i] == data) {
                return true;
            }
        }
    }
    return false;
}

// --------------------------
// ======弹窗提示 start======
// --------------------------
/**
 * 展示提示
 * @param text 提示文字
 * @param type 类型，默认info，可取success、info、warning、error
 * @param duration 持续时间，默认1.5秒，毫秒
 */
function showTips(text, type, duration) {
    if (!isHasText(type)) {
        type = 'info';
    }
    if (!isHasText(duration)) {
        duration = 1500;
    }
    $.toast({
        text: text, // Text that is to be shown in the toast
        heading: '', // Optional heading to be shown on the toast
        icon: type, // Type of toast icon
        showHideTransition: 'fade', // fade, slide or plain
        allowToastClose: false, // Boolean value true or false
        hideAfter: duration, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
        position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left',  // Text alignment i.e. left, right or center
        loader: false,  // Whether to show loader or not. True by default
        loaderBg: '#000000',  // Background color of the toast loader
    });
}

function showSuccessTips() {
    showTips("成功", "success");
}
function showErrorTips(msg) {
    showTips(msg, "error");
}

// --------------------------
// ======弹窗提示 end  ======
// --------------------------