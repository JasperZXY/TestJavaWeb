package zxy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zxy.common.PrivilegeCode;
import zxy.constants.EntityStatus;
import zxy.dao.AccountMapper;
import zxy.entity.Account;
import zxy.entity.User;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.*;
import zxy.permission.support.PrivilegeService;
import zxy.permission.support.ResourceType;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class InitService {
    private static final Logger logger = LoggerFactory.getLogger(InitService.class);

    private static final String ROOT_ACCOUNT = "root";
    private static final String ROOT_PASSWORD = "zxy@123";
    private static final String ROOT_ROLE = "Root";

    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    private List<Resource> allResources = new ArrayList<>();
    private Map<Integer, Resource> resourceMap = new HashMap<>();

    public List<Resource> getAllResources() {
        return allResources;
    }

    public Map<Integer, Resource> getResourceMap() {
        return resourceMap;
    }

    @PostConstruct
    public void init() {
        // 下面大部分初始化代码可以换成SQL脚本
        initRootUser();
        initResource();
        initRootPrivilege();
        privilegeService.setResourceCache(resourceMap);
    }

    /**
     * 初始化root用户
     */
    private void initRootUser() {
        Account account = accountMapper.selectByPrimaryKey(ROOT_ACCOUNT);
        if (account == null) {
            logger.info("initRootUser create root user");
            User user = new User();
            user.setAccountId(ROOT_ACCOUNT);
            user.setName(ROOT_ACCOUNT);
            userService.add(user, ROOT_PASSWORD);
        }
    }

    /**
     * 初始化权限资源
     */
    private void initResource() {
        int root = 0;
        allResources.add(newResource(PrivilegeCode.PRIVILEGES, "权限相关", ResourceType.nav, root));
        allResources.add(newResource(PrivilegeCode.RESOURCE_ACCESS, "资源访问", ResourceType.menu, PrivilegeCode.PRIVILEGES));
        allResources.add(newResource(PrivilegeCode.RESOURCE_ADD, "资源新增", ResourceType.button, PrivilegeCode.RESOURCE_ACCESS));
        allResources.add(newResource(PrivilegeCode.RESOURCE_UPDATE, "资源更新", ResourceType.button, PrivilegeCode.RESOURCE_ACCESS, EntityStatus.FORBIDDEN));
        allResources.add(newResource(PrivilegeCode.RESOURCE_LOCK_UNLOCK, "资源禁用/解禁", ResourceType.button, PrivilegeCode.RESOURCE_ACCESS, EntityStatus.FORBIDDEN));
        allResources.add(newResource(PrivilegeCode.ROLE_ACCESS, "角色访问", ResourceType.menu, PrivilegeCode.PRIVILEGES));
        allResources.add(newResource(PrivilegeCode.ROLE_ADD, "角色新增", ResourceType.button, PrivilegeCode.ROLE_ACCESS));
        allResources.add(newResource(PrivilegeCode.ROLE_UPDATE, "角色更新", ResourceType.button, PrivilegeCode.ROLE_ACCESS));
        allResources.add(newResource(PrivilegeCode.ROLE_LOCK_UNLOCK, "角色禁用/解禁", ResourceType.button, PrivilegeCode.ROLE_ACCESS));
        allResources.add(newResource(PrivilegeCode.ROLE_ALLOCATE_RESOURCE, "给角色分配资源", ResourceType.button, PrivilegeCode.ROLE_ACCESS));
        allResources.add(newResource(PrivilegeCode.ROLE_ASSIGN_USER_ROLE, "给用户指定角色", ResourceType.menu, PrivilegeCode.USER_ACCESS));
        allResources.add(newResource(PrivilegeCode.USER_ACCESS, "用户管理", ResourceType.menu, root));
        allResources.add(newResource(PrivilegeCode.USER_ADD, "用户新增", ResourceType.button, PrivilegeCode.USER_ACCESS));
        allResources.add(newResource(PrivilegeCode.USER_UPDATE, "用户修改", ResourceType.button, PrivilegeCode.USER_ACCESS));
        allResources.add(newResource(PrivilegeCode.USER_DELETE, "用户删除", ResourceType.button, PrivilegeCode.USER_ACCESS));
        allResources.add(newResource(PrivilegeCode.USER_LOCK_UNLOCK, "用户冻结/解冻", ResourceType.button, PrivilegeCode.USER_ACCESS));

        for (Resource resource : allResources) {
            resourceMap.put(resource.getId(), resource);
        }

        List<Resource> resourcesInDB = resourceMapper.selectByExample(new ResourceExample());
        Map<Integer, Resource> tmpMap = new HashMap<>();
        for (Resource resource : resourcesInDB) {
            tmpMap.put(resource.getId(), resource);
        }

        for (Resource resource : allResources) {
            Resource resourceInDB = tmpMap.get(resource.getId());
            if (resourceInDB == null) {
                resourceMapper.insert(resource);
            }
            else if (needUpdate(resource, resourceInDB)) {
                resourceMapper.updateByPrimaryKey(resource);
            }
        }
    }

    private Resource newResource(Integer id, String name, ResourceType type, Integer parentId) {
        return newResource(id, name, type, parentId, EntityStatus.VALID);
    }

    private Resource newResource(Integer id, String name, ResourceType type, Integer parentId, Integer status) {
        Resource resource = new Resource();
        resource.setId(id);
        resource.setName(name);
        resource.setType(type.name());
        resource.setParentId(parentId);
        resource.setStatus(status);
        return resource;
    }

    private boolean needUpdate(Resource resource1, Resource resource2) {
        if (resource1 == resource2) {
            return false;
        }
        if (!resource1.getName().equals(resource2.getName())) {
            return true;
        }
        if (!resource1.getType().equals(resource2.getType())) {
            return true;
        }
        if (!resource1.getParentId().equals(resource2.getParentId())) {
            return true;
        }
        if (!resource1.getStatus().equals(resource2.getStatus())) {
            return true;
        }
        return false;
    }

    private void initRootPrivilege() {
        User rootUser = userService.getUserByAccount(ROOT_ACCOUNT);

        UserRoleRelationExample userRoleRelationExample = new UserRoleRelationExample();
        userRoleRelationExample.createCriteria().andUserIdEqualTo(rootUser.getId());
        if (userRoleRelationMapper.countByExample(userRoleRelationExample) > 0) {
            return;
        }

        Role role = new Role();
        role.setStatus(EntityStatus.VALID);
        role.setName(ROOT_ROLE);
        roleMapper.insert(role);

        RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
        roleResourceRelation.setRoleId(role.getId());
        roleResourceRelation.setCreateUid(rootUser.getId());
        roleResourceRelation.setCreateTime(new Date());
        StringBuilder resourceIdBuilder = new StringBuilder();
        for (Resource resource : allResources) {
            resourceIdBuilder.append(resource.getId()).append(PrivilegeService.SPLIT);
        }
        resourceIdBuilder.replace(resourceIdBuilder.length() - PrivilegeService.SPLIT.length(), resourceIdBuilder.length(), "");
        roleResourceRelation.setResourceIds(resourceIdBuilder.toString());
        roleResourceRelationMapper.insert(roleResourceRelation);

        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setRoleId(role.getId());
        userRoleRelation.setUserId(rootUser.getId());
        userRoleRelationMapper.insert(userRoleRelation);
    }

}
