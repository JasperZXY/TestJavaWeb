package zxy.web.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zxy.common.LogCode;
import zxy.common.PermissionCode;
import zxy.constants.EntityStatus;
import zxy.entity.User;
import zxy.permission.support.PermissionAnnotation;
import zxy.service.AccountService;
import zxy.service.LoginfoService;
import zxy.service.UserService;
import zxy.common.JsonResult;

import javax.servlet.http.HttpServletRequest;

@Controller("apiUserController")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginfoService loginfoService;

    @PermissionAnnotation(code = PermissionCode.USER_ADD)
    @RequestMapping(path="/add")
    @ResponseBody
    public Object add(HttpServletRequest request, User user, String password) {
        String result = userService.add(user, password);
        if (StringUtils.isBlank(result)) {
            return JsonResult.buildSuccess(null);
        }
        loginfoService.addLog(request, LogCode.USER_ADD, "添加用户", Integer.toString(user.getId()));
        return JsonResult.buildFail(result);
    }

    @PermissionAnnotation(code = PermissionCode.USER_UPDATE)
    @RequestMapping(path="/update")
    @ResponseBody
    public Object update(HttpServletRequest request, User user) {
        String result = userService.update(user);
        if (StringUtils.isBlank(result)) {
            loginfoService.addLog(request, LogCode.USER_UPDATE, "修改用户", user.getId());
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildFail(result);
    }

    @PermissionAnnotation(code = PermissionCode.USER_DELETE)
    @RequestMapping(path="/delete/{id}")
    @ResponseBody
    public Object delete(HttpServletRequest request, @PathVariable int id) {
        userService.updateStatus(id, EntityStatus.DELETE);
        loginfoService.addLog(request, LogCode.USER_DELETE, "删除用户", id);
        return JsonResult.buildSuccess(null);
    }

    @PermissionAnnotation(code = PermissionCode.USER_LOCK_UNLOCK)
    @RequestMapping(path="/lock/{id}")
    @ResponseBody
    public Object lock(HttpServletRequest request, @PathVariable int id) {
        userService.updateStatus(id, EntityStatus.FORBIDDEN);
        loginfoService.addLog(request, LogCode.ACCOUNT_LOCK, "锁定账号", id);
        return JsonResult.buildSuccess(null);
    }

    @PermissionAnnotation(code = PermissionCode.USER_LOCK_UNLOCK)
    @RequestMapping(path="/unlock/{id}")
    @ResponseBody
    public Object unlock(HttpServletRequest request, @PathVariable int id) {
        userService.updateStatus(id, EntityStatus.VALID);
        loginfoService.addLog(request, LogCode.ACCOUNT_UNLOCK, "解锁账号", id);
        return JsonResult.buildSuccess(null);
    }

    @PermissionAnnotation(code = PermissionCode.USER_LOCK_UNLOCK)
    @RequestMapping(path="/help/change/password")
    @ResponseBody
    public Object helpChangePassowd(HttpServletRequest request, String accountId, String password) {
        accountService.changePassword(accountId, password);
        loginfoService.addLog(request, LogCode.ACCOUNT_HELP_CHANGE_PASSWORD, "协助修改用户密码",
                userService.getUserByAccount(accountId).getId());
        return JsonResult.buildSuccess(null);
    }

}
