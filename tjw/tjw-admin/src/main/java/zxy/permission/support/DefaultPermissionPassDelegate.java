package zxy.permission.support;

import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class DefaultPermissionPassDelegate<T> implements PermissionPass<T> {
    @Override
    public boolean pass(HttpSession session, Set<T> permissionIds, T checkId) {
        if (CollectionUtils.isNotEmpty(permissionIds)) {
            return permissionIds.contains(checkId);
        }
        return false;
    }
}
