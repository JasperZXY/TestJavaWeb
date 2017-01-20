package zxy.web.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zxy.common.JsonResult;
import zxy.common.PrivilegeCode;
import zxy.common.ResultCode;
import zxy.constants.EntityStatus;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.Role;
import zxy.permission.entity.RoleResourceRelation;
import zxy.permission.entity.UserRoleRelation;
import zxy.permission.entity.UserRoleRelationExample;
import zxy.permission.support.PrivilegeAnnotation;
import zxy.permission.support.PrivilegeService;
import zxy.utils.Utils;
import zxy.web.SessionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限相关
 */
@Controller("apiPermissionController")
@RequestMapping("/api/permission")
public class PermissionController extends BaseApiController {
    private static final Logger logger = LoggerFactory.getLogger(zxy.web.controller.page.UserController.class);

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private PrivilegeService privilegeService;

//    public JsonResult check(Resource resource) {
//        if (resource == null) {
//            return JsonResult.buildFail(ResultCode.FAIL);
//        }
//        if (!Utils.validateId(resource.getId())) {
//            return JsonResult.buildFail("ID不合法");
//        }
//        if (StringUtils.isBlank(resource.getName())) {
//            return JsonResult.buildFail("名称不能为空");
//        }
//        if (ResourceType.valueOf(resource.getType()) == null) {
//            return JsonResult.buildFail("类型有误");
//        }
//        return null;
//    }
//
//    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_ADD)
//    @RequestMapping(path="/resource/add")
//    @ResponseBody
//    public Object addResource(Resource resource) {
//        JsonResult jsonResult = check(resource);
//        if (jsonResult != null) {
//            return jsonResult;
//        }
//
//        try {
//            resource.setStatus(EntityStatus.VALID);
//            resourceMapper.insert(resource);
//        } catch (DuplicateKeyException e) {
//            return JsonResult.buildFail("ID已经存在");
//        }
//
//        return JsonResult.buildSuccess(null);
//    }
//
//    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_UPDATE)
//    @RequestMapping(path="/resource/update")
//    @ResponseBody
//    public Object updateResource(Resource resource) {
//        JsonResult jsonResult = check(resource);
//        if (jsonResult != null) {
//            return jsonResult;
//        }
//
//        resourceMapper.updateByPrimaryKeySelective(resource);
//        return JsonResult.buildSuccess(null);
//    }
//
//    private void updateResourceStatus(int id, int status) {
//        Resource resouce = new Resource();
//        resouce.setId(id);
//        resouce.setStatus(status);
//        resourceMapper.updateByPrimaryKeySelective(resouce);
//    }
//
//    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_LOCK)
//    @RequestMapping(path="/resource/lock/{id}")
//    @ResponseBody
//    public Object lockResource(@PathVariable int id) {
//        updateResourceStatus(id, EntityStatus.FORBIDDEN);
//        return JsonResult.buildSuccess(null);
//    }
//
//    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_UNLOCK)
//    @RequestMapping(path="/resource/unlock/{id}")
//    @ResponseBody
//    public Object unlockResource(@PathVariable int id) {
//        updateResourceStatus(id, EntityStatus.VALID);
//        return JsonResult.buildSuccess(null);
//    }

    public JsonResult check(Role role) {
        if (role == null) {
            return JsonResult.buildFail(ResultCode.FAIL);
        }
        if (StringUtils.isBlank(role.getName())) {
            return JsonResult.buildFail("名称不能为空");
        }
        return null;
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_ADD)
    @RequestMapping(path="/role/add")
    @ResponseBody
    public Object addRole(Role role) {
        JsonResult jsonResult = check(role);
        if (jsonResult != null) {
            return jsonResult;
        }

        role.setStatus(EntityStatus.VALID);
        roleMapper.insert(role);

        return JsonResult.buildSuccess(role.getId());
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_UPDATE)
    @RequestMapping(path="/role/update")
    @ResponseBody
    public Object updateRole(Role role) {
        JsonResult jsonResult = check(role);
        if (jsonResult != null) {
            return jsonResult;
        }
        if (!Utils.validateId(role.getId())) {
            return JsonResult.buildFail("ID不能为空");
        }

        roleMapper.updateByPrimaryKeySelective(role);
        return JsonResult.buildSuccess(null);
    }

    private void updateRoleStatus(int id, int status) {
        Role role = new Role();
        role.setId(id);
        role.setStatus(status);
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_LOCK_UNLOCK)
    @RequestMapping(path="/role/lock/{id}")
    @ResponseBody
    public Object lockRole(@PathVariable int id) {
        updateRoleStatus(id, EntityStatus.FORBIDDEN);
        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_LOCK_UNLOCK)
    @RequestMapping(path="/role/unlock/{id}")
    @ResponseBody
    public Object unlockRole(@PathVariable int id) {
        updateRoleStatus(id, EntityStatus.VALID);
        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_ALLOCATE_RESOURCE)
    @RequestMapping(path="/role/allocate/resource/{id}")
    @ResponseBody
    public Object roleAllocateResource(HttpServletRequest request, @PathVariable int id, String resourceIds) {
        RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
        roleResourceRelation.setCreateUid(SessionManager.getCurrentUserId(request.getSession()));
        roleResourceRelation.setRoleId(id);
        roleResourceRelation.setResourceIds(resourceIds);
        privilegeService.addRoleResourceRelation(roleResourceRelation);
        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_ASSIGN_USER_ROLE)
    @RequestMapping(path="/role/assign/foruser/{uid}")
    @ResponseBody
    public Object assignRoleForUser(HttpServletRequest request, @PathVariable int uid, String roleIds) {
        // 这里简单处理，先删除后新增
        List<Integer> roleList = Utils.stringToIntegerList(roleIds);
        UserRoleRelationExample example = new UserRoleRelationExample();
        example.createCriteria().andUserIdEqualTo(uid);
        userRoleRelationMapper.deleteByExample(example);

        for (Integer roleId : roleList) {
            UserRoleRelation relation = new UserRoleRelation();
            relation.setRoleId(roleId);
            relation.setUserId(uid);
            userRoleRelationMapper.insert(relation);
        }

        return JsonResult.buildSuccess(null);
    }

}
