package zxy.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import zxy.common.utils.JsonUtils;
import zxy.commons.JsonResult;
import zxy.commons.ResultCode;
import zxy.commons.ServiceException;
import zxy.common.utils.HttpUtils;
import zxy.component.AjaxDecideDelegate;
import zxy.commons.JspConfig;
import zxy.permission.support.NoPermissionException;
import zxy.utils.Utils;
import zxy.weixin.WeixinException;
import zxy.weixin.qyh.utils.WeixinReturnCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一拦截、异常日志
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    // FIXME 判断是返回json数据还是view视图

    private void forward(HttpServletRequest request, HttpServletResponse response, String msg) {
        request.setAttribute(JspConfig.KEY_MSG, msg);
        Utils.requestForward(request, response, JspConfig.ERROR_URL);
    }

    private void forwardLogin(HttpServletRequest request, HttpServletResponse response) {
        Utils.requestForward(request, response, JspConfig.LOGIN_URL);
    }

    @ExceptionHandler(WeixinException.class)
    @ResponseBody
    public Object noPermissionException(WeixinException ex, HttpServletRequest request, HttpServletResponse response) {
        if (WeixinReturnCode.ILLEGAL_OAUTH_CODE.equals(ex.getCode())) {
            return JsonResult.buildNoLogin();
        }
        return JsonResult.buildFail(ex.getMessage());
    }

    @ExceptionHandler(NoPermissionException.class)
    @ResponseBody
    public Object noPermissionException(NoPermissionException ex, HttpServletRequest request, HttpServletResponse response) {
        if (AjaxDecideDelegate.isNotAjax(request)) {
            forward(request, response, ResultCode.NO_PERMISSION.getCndesc());
            return null;
        }

        return JsonResult.buildFail(ResultCode.NO_PERMISSION);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Object serviceException(ServiceException ex, HttpServletRequest request, HttpServletResponse response) {
        printLog(ex, request);

        // 对未登陆的做特殊处理
        if (ex.getCode() == ResultCode.NO_LOGIN) {
            if (AjaxDecideDelegate.isNotAjax(request)) {
                // 把当前url带回去，在登录完后才可以跳转到原位置
                Utils.requestRedirect(response,
                        JspConfig.LOGIN_URL + "?" + JspConfig.REDIRECT_URL_KEY + "=" + request.getRequestURI());
                return null;
            }
            else {
                return JsonResult.buildNoLogin();
            }
        }

        if (AjaxDecideDelegate.isNotAjax(request)) {
            forward(request, response, ex.getMessage());
            return null;
        }

        return JsonResult.buildFail(ex.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        printLog(ex, request);
        if (AjaxDecideDelegate.isNotAjax(request)) {
            forward(request, response, ResultCode.FAIL.getCndesc());
            return null;
        }

        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException matme = (MethodArgumentTypeMismatchException) ex;
            return JsonResult.buildFail(String.format("参数(%s=%s)类型有误", matme.getName(), matme.getValue()));
        }
        if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            return JsonResult.buildFail(String.format("参数(%s=%s)类型有误", be.getFieldError().getField(), be.getFieldError().getRejectedValue()));
        }

        return JsonResult.SYSTEM_ERROR_RESULT;
    }

    private void printLog(Exception ex, HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(request.getRequestURI());
        String queryString = request.getQueryString();
//        LoginUser loginUser = UserSessionUtils.getCurrentUser(request.getSession());
        Object loginUser = null;
        if (queryString != null && !"".equals(queryString)) {
            urlBuilder.append("?").append(queryString);
        }
        logger.error("Exception IP:{}\n \tuser:{}\n \turl:{}",
                HttpUtils.getRemoteIP(request), JsonUtils.toString(loginUser), urlBuilder.toString(), ex);
    }

}
