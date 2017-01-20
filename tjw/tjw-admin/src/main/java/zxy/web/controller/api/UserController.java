package zxy.web.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zxy.common.PrivilegeCode;
import zxy.constants.EntityStatus;
import zxy.entity.User;
import zxy.permission.support.PrivilegeAnnotation;
import zxy.service.UserService;
import zxy.common.JsonResult;

@Controller("apiUserController")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PrivilegeAnnotation(code = PrivilegeCode.USER_ADD)
    @RequestMapping(path="/add")
    @ResponseBody
    public Object add(User user, String password) {
        String result = userService.add(user, password);
        if (StringUtils.isBlank(result)) {
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildFail(result);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.USER_UPDATE)
    @RequestMapping(path="/update")
    @ResponseBody
    public Object update(User user) {
        String result = userService.update(user);
        if (StringUtils.isBlank(result)) {
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildFail(result);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.USER_DELETE)
    @RequestMapping(path="/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable int id) {
        userService.updateStatus(id, EntityStatus.DELETE);
        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.USER_LOCK_UNLOCK)
    @RequestMapping(path="/lock/{id}")
    @ResponseBody
    public Object lock(@PathVariable int id) {
        userService.updateStatus(id, EntityStatus.FORBIDDEN);
        return JsonResult.buildSuccess(null);
    }

    @PrivilegeAnnotation(code = PrivilegeCode.USER_LOCK_UNLOCK)
    @RequestMapping(path="/unlock/{id}")
    @ResponseBody
    public Object unlock(@PathVariable int id) {
        userService.updateStatus(id, EntityStatus.VALID);
        return JsonResult.buildSuccess(null);
    }

}
