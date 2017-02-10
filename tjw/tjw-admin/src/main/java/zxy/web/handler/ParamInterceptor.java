package zxy.web.handler;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.JspConfig;
import zxy.commons.ResultCode;
import zxy.commons.ServiceException;
import zxy.web.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO 待优化 由于MyExceptionHandler拦截后是Forward，参数会带回来
        if (request.getRequestURI().startsWith(JspConfig.ERROR_URL)) {
            return true;
        }
        String pageSize = request.getParameter("pageSize");
        if (NumberUtils.toInt(pageSize, 0) > 150) {
            throw new ServiceException(ResultCode.PAGE_SIZE_TOO_BIG);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
