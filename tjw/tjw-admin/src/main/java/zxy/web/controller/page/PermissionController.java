package zxy.web.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.common.PrivilegeCode;
import zxy.common.ResultCode;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.Resource;
import zxy.permission.entity.ResourceExample;
import zxy.permission.entity.Role;
import zxy.permission.entity.RoleExample;
import zxy.permission.support.PrivilegeAnnotation;

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

    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_ACCESS)
    @RequestMapping(path="/resource/list/all")
    public ModelAndView listAllResources() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/resource/list");
        modelAndView.addObject("list", resourceMapper.selectByExample(new ResourceExample()));
        return modelAndView;
    }

//    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_ADD)
//    @RequestMapping(path="/resource/to_add")
//    public ModelAndView toAddResource() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("permission/resource/add");
//        return modelAndView;
//    }
//
//    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_UPDATE)
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

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_ACCESS)
    @RequestMapping(path="/role/list/all")
    public ModelAndView listAllRoles() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/list");
        modelAndView.addObject("list", roleMapper.selectByExample(new RoleExample()));
        return modelAndView;
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_ADD)
    @RequestMapping(path="/role/to_add")
    public ModelAndView toAddRole() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/role/add");
        return modelAndView;
    }

    @PrivilegeAnnotation(code = PrivilegeCode.ROLE_UPDATE)
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

}
