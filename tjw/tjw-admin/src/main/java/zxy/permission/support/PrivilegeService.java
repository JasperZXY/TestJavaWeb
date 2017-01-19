package zxy.permission.support;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zxy.constants.EntityStatus;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.*;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class PrivilegeService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    private Map<Integer, Resource> resourceCache = Collections.emptyMap();

    @PostConstruct
    @Scheduled(cron="3 0/10 * * * ? ") //间隔10分钟执行
    public void task() {
        List<Resource> resources = listAllResources();
        Map<Integer, Resource> map = new HashMap<>();
        for (Resource resource : resources) {
            map.put(resource.getId(), resource);
        }
        resourceCache = map;
    }

    /**
     * 根据用户已有的权限判断是否该权限可以通过验证
     * @param userResources     用户已有权限
     * @param checkResourceId   需要判断的权限
     * @return
     */
    public boolean pass(Set<Integer> userResources, Integer checkResourceId) {
        if (CollectionUtils.isEmpty(userResources)) {
            return false;
        }

        if (userResources.contains(checkResourceId)) {
            return true;
        }

        Resource resource = resourceCache.get(checkResourceId);
        if (relateToSubResource(resource)) {
            for (Resource resourceInCache : resourceCache.values()) {
                if (resource.getId().equals(resourceInCache.getParentId()) && userResources.contains(resourceInCache.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean relateToSubResource(Resource resource) {
        return resource != null && ResourceType.nav.equals(ResourceType.valueOf(resource.getType()));
    }

    public List<Resource> listAllResources() {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andStatusEqualTo(EntityStatus.VALID);
        return resourceMapper.selectByExample(resourceExample);
    }

    public List<Resource> getResourcesForUser(Integer uid) {
        List<Integer> userRoleIds = getRoleIdsForUser(uid);
        if (CollectionUtils.isEmpty(userRoleIds)) {
            return Collections.emptyList();
        }

        RoleResourceRelationExample roleResourceRelationExample = new RoleResourceRelationExample();
        roleResourceRelationExample.createCriteria().andRoleIdIn(userRoleIds);
        List<RoleResourceRelation> roleResourceRelations = roleResourceRelationMapper.selectByExample(roleResourceRelationExample);
        if (CollectionUtils.isEmpty(roleResourceRelations)) {
            return Collections.emptyList();
        }

        Set<Integer> resourceIds = new HashSet<>();
        for (RoleResourceRelation relation : roleResourceRelations) {
            resourceIds.add(relation.getResourceId());
        }
        return getResources(new ArrayList<>(resourceIds));
    }

    public Set<Integer> getResourceIdsForUser(Integer uid) {
        List<Resource> resources = getResourcesForUser(uid);
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptySet();
        }

        Set<Integer> ids = new HashSet<>();
        for (Resource resource : resources) {
            ids.add(resource.getId());
        }
        return ids;
    }

    public List<Integer> getRoleIdsForUser(Integer uid) {
        UserRoleRelationExample userRoleRelationExample = new UserRoleRelationExample();
        userRoleRelationExample.createCriteria().andUserIdEqualTo(uid);
        List<UserRoleRelation> relations = userRoleRelationMapper.selectByExample(userRoleRelationExample);
        if (CollectionUtils.isEmpty(relations)) {
            return Collections.emptyList();
        }

        Set<Integer> roleIds = new HashSet<>();
        for (UserRoleRelation relation : relations) {
            roleIds.add(relation.getRoleId());
        }

        return new ArrayList<>(roleIds);
    }

    public List<Role> getRolesForUser(Integer uid) {
        return getRoles(getRoleIdsForUser(uid));
    }

    public List<Role> getRoles(List<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIds);
        return roleMapper.selectByExample(roleExample);
    }

    public List<Resource> getResources(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andIdIn(ids).andStatusEqualTo(EntityStatus.VALID);
        return resourceMapper.selectByExample(resourceExample);
    }

}
