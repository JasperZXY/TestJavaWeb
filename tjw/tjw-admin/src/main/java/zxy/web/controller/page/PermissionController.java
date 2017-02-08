package zxy.web.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.PermissionCode;
import zxy.commons.ResultCode;
import zxy.commons.EntityStatus;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.*;
import zxy.permission.support.PermissionAnnotation;
import zxy.permission.PermissionService;
import zxy.permission.ResourceType;
import zxy.service.InitService;
import zxy.service.UserService;
import zxy.utils.Utils;

import java.util.*;

/**
 * 权限相关
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BasePageController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    @Autowired
    private InitService initService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;

    @PermissionAnnotation(code = PermissionCode.RESOURCE_ACCESS)
    @RequestMapping(path="/resource/list/all")
    public ModelAndView listAllResources() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/resource/list");
        modelAndView.addObject("list", resourceMapper.selectByExample(new ResourceExample()));
        return modelAndView;
    }

//    @PermissionAnnotation(code = PermissionCode.RESOURCE_ADD)
//    @RequestMapping(path="/resource/to_add")
//    public ModelAndView toAddResource() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("permission/resource/add");
//        return modelAndView;
//    }
//
//    @PermissionAnnotation(code = PermissionCode.RESOURCE_UPDATE)
//    @RequestMapping(path="/resource/to_update/{id}")
//    public ModelAndView toUpdateResource(@PathVariable int id) {
//        Resource resource = resourceMapper.selectByPrimaryKey(id);
//        if (resource == null) {
//            return toErrorView(ResultCode.DATA_NO_FOUND);
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("permission/resource/update");
//        modelAndView.addObject("resource", resource);
//        return modelAndView;
//    }

    @PermissionAnnotation(code = PermissionCode.ROLE_ACCESS)
    @RequestMapping(path="/role/list/all")
    public ModelAndView listAllRoles() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/list");
        modelAndView.addObject("list", roleMapper.selectByExample(new RoleExample()));
        return modelAndView;
    }

    @PermissionAnnotation(code = PermissionCode.ROLE_ADD)
    @RequestMapping(path="/role/to_add")
    public ModelAndView toAddRole() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/add");
        return modelAndView;
    }

    @PermissionAnnotation(code = PermissionCode.ROLE_UPDATE)
    @RequestMapping(path="/role/to_update/{id}")
    public ModelAndView toUpdateRole(@PathVariable int id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            return toErrorView(ResultCode.DATA_NO_FOUND);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/update");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    // 给角色分配资源
    @PermissionAnnotation(code = PermissionCode.ROLE_ALLOCATE_RESOURCE)
    @RequestMapping(path="/role/to_allocate/resource/{id}")
    public ModelAndView roleAllocationResource(@PathVariable int id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            return toErrorView(ResultCode.DATA_NO_FOUND);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/resource");
        modelAndView.addObject("role", role);
        modelAndView.addObject("resourceIdsForRole", Utils.toString(permissionService.getResourceIdsForRole(id)));

        List<Resource> allResources = new ArrayList<>(initService.getAllResources());
        Collections.sort(allResources, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return o1.getId() - o2.getId();
            }
        });

        List<List<Resource>> retResourceList = new ArrayList<>();
        List<Resource> curList = new ArrayList<>();
        int curModule = 0;

        for (Resource resource : allResources) {
            if (resource.getStatus() != EntityStatus.VALID) {
                continue;
            }
            if (ResourceType.nav.name().equals(resource.getName())) {
                continue;
            }
            if (resource.getId() / 100 != curModule) {
                curModule = resource.getId() / 100;
                curList = new ArrayList<>();
                retResourceList.add(curList);
            }
            curList.add(resource);
        }

        modelAndView.addObject("allResources", retResourceList);
        return modelAndView;
    }

    // 给用户指定角色
    @PermissionAnnotation(code = PermissionCode.ROLE_ASSIGN_USER_ROLE)
    @RequestMapping(path="/role/to_assign/foruser/{uid}")
    public ModelAndView toAssignRoleForUser(@PathVariable int uid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/foruser");
        modelAndView.addObject("user", userService.getValidUser(uid));
        modelAndView.addObject("userRoleIds", Utils.toString(permissionService.getRoleIdsForUser(uid)));
        modelAndView.addObject("allRoles", permissionService.getAllValidRoles());
        return modelAndView;
    }

}
