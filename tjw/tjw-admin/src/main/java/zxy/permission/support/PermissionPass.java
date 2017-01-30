package zxy.permission.support;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 权限验证接口
 * @param <T>
 */
public interface PermissionPass<T> {
    /**
     * 权限验证是否通过
     * @param session       当前会话
     * @param permissionIds 用户所有的权限
     * @param checkId       待认证权限
     * @return
     */
    boolean pass(HttpSession session, Set<T> permissionIds, T checkId);
}
