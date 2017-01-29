package zxy.web;

import zxy.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class SessionManager {
    private static final String LOGIN_USER = "LOGIN_USER";

    public static User getCurrentUser(HttpSession session) {
        if (session == null) {
            return null;
        }
        return (User) session.getAttribute(LOGIN_USER);
    }

    public static Integer getCurrentUserId(HttpSession session) {
        User user = getCurrentUser(session);
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static void setCurrentUser(HttpSession session, User loginUser) {
        if (session == null) {
            return;
        }
        session.setAttribute(LOGIN_USER, loginUser);
    }

    public static boolean isLogin(HttpServletRequest request) {
        return getCurrentUser(request.getSession(false)) != null;
    }
}
