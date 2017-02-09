/**
 * 微信企业号专用JS，部分函数在common.js中。
 */

var KEY_URL = 'url';
var KEY_CODE = 'code';
var KEY_STATE = 'state';
var KEY_MYAPPID = 'myappid';
// 服务端请求前缀，在前后端分离中，这里需要修改。ctxPath目前定义在share.jsp
var SERVER_URL_PRE = ctxPath;

function weixinqyhAjax(obj) {
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

    $data[KEY_CODE] = getQueryString(KEY_CODE);
    $data[KEY_MYAPPID] = getQueryString(KEY_MYAPPID);
    $data[KEY_STATE] = getQueryString(KEY_STATE);

    var url = SERVER_URL_PRE + $shortUrl;
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
                    callbackForError(obj, "登录超时");
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

