package zxy.permission.support;

import javax.servlet.http.HttpSession;

/**
 * 没有权限监听器
 *
 * @param <T> PermissionId
 */
public interface NoPermissionListener<T> {
    /**
     * @param session
     * @param permissionId
     * @return 是否自己已经处理完毕，不需交由Spring处理
     */
    boolean noPermission(HttpSession session, T permissionId);
}
