/*
 *  实现自己的JS
 */

/** 是否打印日志 */
var isDebugLog = true;
// 在share.jsp中定义了一个“ctxPath”变量

/*$(document).ready(function(){
 myInit();
 });*/
window.onload = function () {
    myInit();
}

function myInit() {
    printLog("init");
    menuInit();

    // jQuery配合input-mask使用
    $("[data-mask]").inputmask();

    // 扩展select标签
    var selectsNeedToInit = $("[select-init]");
    for (var i=0; i<selectsNeedToInit.length; i++) {
        var selectItem = $(selectsNeedToInit[i]);
        var selectOptions = selectItem.children('option');
        for (var j=0; j<selectOptions.length; j++) {
            if ($(selectOptions[j]).val() == selectItem.attr('init-value')) {
                $(selectOptions[j]).attr('selected', 'selected');
            }
        }
    }
}

/**
 * 在当前也打开窗口
 * @param shortUrl
 */
function selfOpen(shortUrl) {
    window.open(ctxPath + shortUrl, "_self");
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
    return ctxPath + shortUrl + "?topage=" + topage + "&pageSize=" + $('#pageSize').val();
}

/**
 * 根据当前页面，判断页面那个item是选中状态
 */
function menuInit() {
    var url = location.href;
    //printLog("menuInit url:" + url);
    var au = actualUrl(url);
    //printLog("actualUrl url:" + au);

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
        printLog("item: " + childrenItem.html());
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
        printLog("ulChile:" + ulChile.length);
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

function isHasText(str) {
    return str != undefined && str != null && str != '';
}

/**
 * form表单Ajax请求。
 * TODO 修改为继承的方式，MyAjaxForm应该继承MyAjaxHttp
 * @param {MyAjaxForm} obj
 * @constructor
 */
function MyAjaxForm(obj) {
    var $formId = obj.formId;
    var $type = obj.type;
    var $shortUrl = obj.shortUrl;
    var success = function (data) {
        if (obj.success instanceof Function) {
            obj.success(data);
        }
    };
    var error = function (msg) {
        if (obj.error instanceof Function) {
            obj.error(msg);
        }
    };
    var run = function () {
        if (!isHasText($formId)) {
            error("formId为空");
        }
        new MyAjaxHttp({
            data : $('#' + $formId).serialize(),
            shortUrl : $shortUrl,
            type : $type,
            success : function (data) {
                success(data);
            },
            error : function (msg) {
                error(msg);
            }
        }).run();
    };

    this.formId = $formId;
    this.type = $type;
    this.shortUrl = $shortUrl;
    this.success = success;
    this.error = error;
    this.run = run;
}

/**
 * 异步Http请求
 * @param {MyAjaxHttp} obj
 * @constructor
 */
function MyAjaxHttp(obj) {
    var $data = obj.data;
    var $type = obj.type;
    var $shortUrl = obj.shortUrl;

    var success = function (data) {
        if (obj.success instanceof Function) {
            obj.success(data);
        }
    };
    var error = function (msg) {
        if (obj.error instanceof Function) {
            obj.error(msg);
        }
    };
    var run = function () {
        if (!isHasText($type)) {
            $type = 'POST';
        }
        if (!isHasText($shortUrl)) {
            obj.error('请求不能为空');
        }
        if ($data == undefined || $data == null) {
            $data = {};
        }

        $.ajax({
            url: ctxPath + $shortUrl,
            data: $data,
            type: $type,
            success: function (retData) {
                if (retData == null) {
                    error("返回数据为空");
                }
                else {
                    if (retData.status == 10000) {
                        success(retData.result);
                    }
                    else {
                        error(retData.msg);
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (!navigator.onLine) {
                    error("断网了，请检查网络");
                }
                else {
                    error("请求链接有误或服务异常");
                }
            }
        });
    };

    this.data = $data;
    this.type = $type;
    this.shortUrl = $shortUrl;
    this.success = success;
    this.error = error;
    this.run = run;
}
