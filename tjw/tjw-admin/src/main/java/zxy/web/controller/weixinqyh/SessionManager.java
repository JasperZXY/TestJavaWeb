package zxy.web.controller.weixinqyh;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class SessionManager {
    private static final String USERID = "userid";
    private static final String MYAPPID = "myappid";

    private static String getLoginUserKey(HttpServletRequest request) {
        return new StringBuilder().append(USERID).append("_").append(request.getParameter(MYAPPID)).toString();
    }

    public static String getLoginUserid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (String) session.getAttribute(getLoginUserKey(request));
    }

    public static void setLoginUserid(HttpServletRequest request, String userid) {
        request.getSession().setAttribute(getLoginUserKey(request), userid);
    }

    public static boolean isLogin(HttpServletRequest request) {
        return StringUtils.isNotBlank(getLoginUserid(request));
    }
}
