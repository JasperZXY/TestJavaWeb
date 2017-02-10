package zxy.permission;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxy.commons.EntityStatus;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.*;

import java.util.*;

@Service
public class PermissionService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    private Map<Integer, Resource> resourceCache = Collections.emptyMap();
    public static final String SPLIT = ",";

    public void setResourceCache(Map<Integer, Resource> resourceCache) {
        this.resourceCache = resourceCache;
    }

    public Map<Integer, Resource> getResourceCache() {
        return resourceCache;
    }

    public List<Resource> getResourcesForUser(Integer uid) {
        List<Integer> userRoleIds = getRoleIdsForUser(uid);
        if (CollectionUtils.isEmpty(userRoleIds)) {
            return Collections.emptyList();
        }

        Set<Integer> resourceIds = getResourceIdsForRole(userRoleIds);
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

    public Set<Integer> getResourceIdsForRole(List<Integer> roleIds) {
        RoleResourceRelationExample roleResourceRelationExample = new RoleResourceRelationExample();
        roleResourceRelationExample.createCriteria().andRoleIdIn(roleIds);
        roleResourceRelationExample.setOrderByClause(" id desc");
        List<RoleResourceRelation> roleResourceRelations =
            roleResourceRelationMapper.selectByExampleWithRowbounds(roleResourceRelationExample, new RowBounds(0, 1));
        if (CollectionUtils.isEmpty(roleResourceRelations)) {
            return Collections.emptySet();
        }

        RoleResourceRelation relation = roleResourceRelations.get(0);
        if (StringUtils.isBlank(relation.getResourceIds())) {
            return Collections.emptySet();
        }

        Set<Integer> resourceIds = new HashSet<>();
        for (String rid : relation.getResourceIds().split(SPLIT)) {
            resourceIds.add(NumberUtils.toInt(rid));
        }
        return resourceIds;
    }

    public Set<Integer> getResourceIdsForRole(Integer roleId) {
        return getResourceIdsForRole(Collections.singletonList(roleId));
    }

    public void addRoleResourceRelation(RoleResourceRelation roleResourceRelation) {
        roleResourceRelation.setCreateTime(new Date());
        roleResourceRelationMapper.insert(roleResourceRelation);
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

    public List<Role> getAllValidRoles() {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andStatusEqualTo(EntityStatus.VALID);
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
