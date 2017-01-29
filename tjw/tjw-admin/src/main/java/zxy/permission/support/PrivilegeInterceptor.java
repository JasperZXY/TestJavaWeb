package zxy.permission.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器，权限没通过的，抛出NoPermissionException
 *
 * @see zxy.permission.support.NoPermissionException
 */
@ControllerAdvice("privilegeInterceptor")
public class PrivilegeInterceptor implements HandlerInterceptor {
    @Autowired
    private PrivilegeContext privilegeContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            PrivilegeAnnotation permissionAnnotation = method.getMethodAnnotation(PrivilegeAnnotation.class);
            if (permissionAnnotation != null) {
                if (!privilegeContext.pass(request.getSession(false), permissionAnnotation.code())) {
                    throw new NoPermissionException();
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
