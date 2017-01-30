package zxy.permission.support;

import javax.servlet.http.HttpSession;

public class DefaultNoPermissionListener<T> implements NoPermissionListener<T> {
    @Override
    public boolean noPermission(HttpSession session, T permissionId) {
        throw new NoPermissionException();
    }
}
