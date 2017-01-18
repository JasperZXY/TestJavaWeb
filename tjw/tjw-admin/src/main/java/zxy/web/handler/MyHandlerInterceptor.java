package zxy.web.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zxy.constants.JspConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class MyHandlerInterceptor implements HandlerInterceptor {
    private static final Set<String> IGNORE_VIEWS = new HashSet<>();
    static {
        IGNORE_VIEWS.add("hello");
        IGNORE_VIEWS.add("login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        templateView(mv);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    // 判断返回的页面是否需要重新定位，返回真正的页面
    private void templateView(ModelAndView mv) {
        if (mv != null) {
            String orgViewName = mv.getViewName();
            if (IGNORE_VIEWS.contains(orgViewName)) {
                return;
            }
            mv.setViewName("template");
            mv.addObject(JspConfig.TEMPLATE_PAGE_NAME, orgViewName);
        }
    }

}
