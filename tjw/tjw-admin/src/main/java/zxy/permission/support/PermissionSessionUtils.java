package zxy.permission.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Set;

public abstract class  PermissionSessionUtils {
    private static final Logger logger = LoggerFactory.getLogger(PermissionSessionUtils.class);
    public static final String USER_RESOURCE = PermissionSessionUtils.class.getName() + ".USER_RESOURCE";

    /**
     * 设置当前登录用户的权限，在登录后进行调用
     * @param session
     * @param resourceIds
     * @param <T> 权限ID的类型
     */
    public static <T> void setSessionUserPermission(HttpSession session, Set<T> resourceIds) {
        if (session == null) {
            return;
        }
        session.setAttribute(USER_RESOURCE, resourceIds);
    }

    /**
     * 判断当前用户是否有该权限
     * @param session
     * @param permissionCode
     * @param permissionPass
     * @param <T> 权限ID的类型
     * @return
     */
    public static <T> boolean pass(HttpSession session, T permissionCode, PermissionPass<T> permissionPass) {
        if (session == null) {
            return false;
        }
        if (permissionPass == null) {
            permissionPass = PermissionContext.getPermissionPass();
        }
        return permissionPass.pass(session, (Set<T>) session.getAttribute(USER_RESOURCE), permissionCode);
    }

    /**
     * 判断当前用户是否有该权限
     * @param session
     * @param permissionCode
     * @param <T> 权限ID的类型
     * @return
     */
    public static <T> boolean pass(HttpSession session, T permissionCode) {
        return pass(session, permissionCode, PermissionContext.getPermissionPass());
    }

}
