package zxy.component;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断是否ajax请求
 */
public class AjaxDecideDelegate {

    public static boolean isNotAjax(HttpServletRequest request) {
        return !isAjax(request);
    }

    public static boolean isAjax(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api") || isReturnTypeJson(request);
    }

    private static boolean isReturnTypeJson(HttpServletRequest request) {
        return "json".equalsIgnoreCase(request.getParameter("returntype"));
    }
}
