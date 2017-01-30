package zxy.permission.support;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器，权限没通过的，抛出NoPermissionException，需结合ExceptionHandler一起使用
 *
 * @see zxy.permission.support.NoPermissionException
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            PermissionAnnotation permissionAnnotation = method.getMethodAnnotation(PermissionAnnotation.class);
            if (permissionAnnotation != null) {
                if (!PermissionSessionUtils.pass(request.getSession(false), permissionAnnotation.code())) {
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
