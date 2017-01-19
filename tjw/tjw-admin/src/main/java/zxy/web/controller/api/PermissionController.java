package zxy.web.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zxy.common.JsonResult;
import zxy.common.PrivilegeCode;
import zxy.common.ResultCode;
import zxy.constants.EntityStatus;
import zxy.permission.dao.ResourceMapper;
import zxy.permission.dao.RoleMapper;
import zxy.permission.dao.RoleResourceRelationMapper;
import zxy.permission.dao.UserRoleRelationMapper;
import zxy.permission.entity.Resource;
import zxy.permission.entity.ResourceExample;
import zxy.permission.support.PrivilegeAnnotation;
import zxy.permission.support.ResourceType;
import zxy.utils.Utils;
import zxy.web.controller.page.*;

/**
 * 权限相关
 */
@Controller("apiPermissionController")
@RequestMapping("/api/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(zxy.web.controller.page.UserController.class);

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    public JsonResult check(Resource resource) {
        if (!Utils.validateId(resource.getId())) {
            return JsonResult.buildFail("ID不合法");
        }
        if (StringUtils.isBlank(resource.getName())) {
            return JsonResult.buildFail("名称不能为空");
        }
        if (ResourceType.valueOf(resource.getType()) == null) {
            return JsonResult.buildFail("类型有误");
        }
        return null;
    }

    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_ADD)
    @RequestMapping(path="/resource/add")
    @ResponseBody
    public Object addResource(Resource resource) {
        JsonResult jsonResult = check(resource);
        if (jsonResult != null) {
            return jsonResult;
        }

        try {
            resource.setStatus(EntityStatus.VALID);
            resourceMapper.insert(resource);
        } catch (DuplicateKeyException e) {
            return JsonResult.buildFail("ID已经存在");
        }

        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_UPDATE)
    @RequestMapping(path="/resource/update")
    @ResponseBody
    public Object updateResource(Resource resource) {
        JsonResult jsonResult = check(resource);
        if (jsonResult != null) {
            return jsonResult;
        }

        resourceMapper.updateByPrimaryKeySelective(resource);
        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.RESOURCE_DELETE)
    @RequestMapping(path="/resource/delete/{id}")
    @ResponseBody
    public Object deleteResource(@PathVariable int id) {
        Resource resource = new Resource();
        resource.setId(id);
        resource.setStatus(EntityStatus.DELETE);
        resourceMapper.updateByPrimaryKeySelective(resource);
        return JsonResult.buildSuccess(null);
    }


}
