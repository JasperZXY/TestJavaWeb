package zxy.permission.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Component
public class PermissionContext {
    private static final Logger logger = LoggerFactory.getLogger(PermissionContext.class);
    public static final String RESOURCE = PermissionContext.class.getName() + ".RESOURCE";
    @Autowired
    private PermissionService permissionService;

    public void initUserPermission(HttpSession session, Integer uid) {
        Set<Integer> resourceIds = permissionService.getResourceIdsForUser(uid);
        session.setAttribute(RESOURCE, resourceIds);
    }

    public boolean pass(HttpSession session, Integer permissionCode) {
        if (session == null) {
            return false;
        }
        Set<Integer> resourceIds = (Set<Integer>) session.getAttribute(RESOURCE);
        return permissionService.pass(resourceIds, permissionCode);
    }


}
