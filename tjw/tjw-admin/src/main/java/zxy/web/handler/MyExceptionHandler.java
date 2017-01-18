package zxy.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import zxy.common.JsonResult;
import zxy.common.ServiceException;
import zxy.utils.JsonUtils;
import zxy.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常统一拦截、异常日志
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    private boolean toPage500(HttpServletRequest request, HttpServletResponse response) {
        // 后续可以改为根据具体的controller方法上的注解进行判断
        if (request.getRequestURI().startsWith("/api")) {
            return false;
        }

        try {
            request.getRequestDispatcher("/500").forward(request, response);
        } catch (Exception e) {
            // ignore
        }
        return true;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Object eduServiceException(ServiceException ex, HttpServletRequest request, HttpServletResponse response) {
        printLog(ex, request);
        if (toPage500(request, response)) {
            return null;
        }

        return JsonResult.buildFail(ex.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        printLog(ex, request);
        if (toPage500(request, response)) {
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
                Utils.getRemoteIP(request), JsonUtils.toString(loginUser), urlBuilder.toString(), ex);
    }

}
