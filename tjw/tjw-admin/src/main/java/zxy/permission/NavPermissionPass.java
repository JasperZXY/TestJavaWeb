package zxy.permission;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxy.permission.entity.Resource;
import zxy.permission.support.PermissionContext;
import zxy.permission.support.PermissionPass;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 考虑nav类型的资源的权限控制
 */
@Service
public class NavPermissionPass implements PermissionPass<Integer> {
    @Autowired
    private PermissionService permissionService;

    @PostConstruct
    public void init() {
        PermissionContext.setPermissionPass(this);
    }

    @Override
    public boolean pass(HttpSession session, Set<Integer> userResources, Integer checkResourceId) {
        if (CollectionUtils.isEmpty(userResources)) {
            return false;
        }

        if (userResources.contains(checkResourceId)) {
            return true;
        }

        Resource resource = permissionService.getResourceCache().get(checkResourceId);
        if (isNav(resource)) {
            for (Resource resourceInCache : permissionService.getResourceCache().values()) {
                if (resource.getId().equals(resourceInCache.getParentId()) && userResources.contains(resourceInCache.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isNav(Resource resource) {
        return resource != null && ResourceType.nav.equals(ResourceType.valueOf(resource.getType()));
    }
}
