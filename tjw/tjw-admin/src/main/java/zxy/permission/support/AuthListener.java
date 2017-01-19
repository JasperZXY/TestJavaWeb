package zxy.permission.support;

import javax.servlet.http.HttpSession;

public interface AuthListener<UID> {
    void onLogin(HttpSession session, UID uid);
}
