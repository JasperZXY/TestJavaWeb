package zxy.web.controller.page;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.common.LogCode;
import zxy.constants.JspConfig;
import zxy.entity.User;
import zxy.permission.PermissionService;
import zxy.permission.support.PermissionSessionUtils;
import zxy.service.AccountService;
import zxy.service.LoginfoService;
import zxy.service.UserService;
import zxy.utils.Utils;
import zxy.web.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController extends BasePageController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping(path = "login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, String account, String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/login");
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            int result = accountService.login(account, password);
            if (result == 0) {
                User user = userService.getUserByAccount(account);
                HttpSession session = request.getSession();
                SessionManager.setCurrentUser(session, user);
                PermissionSessionUtils.setSessionUserPermission(session,
                        permissionService.getResourceIdsForUser(user.getId()));

                String redirectUrl = request.getParameter(JspConfig.REDIRECT_URL_KEY);
                if (StringUtils.isNotBlank(redirectUrl)) {
                    Utils.requestRedirect(response, redirectUrl);
                }
                else {
                    Utils.requestRedirect(response, JspConfig.INDEX_URL);
                }
                loginfoService.addLog(request, LogCode.ACCOUNT_LOGIN, "登录", null, "成功");
                return null;
            }
            else {
                String errorMsg = null;
                switch (result) {
                    case 1:
                        errorMsg = "账号不存在";
                        break;
                    case 2:
                        errorMsg = "账号已冻结";
                        break;
                    case 3:
                        errorMsg = "密码有误";
                        break;
                    default:
                        errorMsg = "账号不存在";
                }

                if (StringUtils.isNotBlank(errorMsg)) {
                    modelAndView.addObject(JspConfig.KEY_MSG, errorMsg);
                    loginfoService.addLog(request, LogCode.ACCOUNT_LOGIN, "登录", null, "失败：" + errorMsg);
                }
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        loginfoService.addLog(request, LogCode.ACCOUNT_LOGOUT, "退出登录");
        SessionManager.setCurrentUser(session, null);
        session.invalidate();

        return redirectToLogin(response);
    }

}
