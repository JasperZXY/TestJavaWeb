package zxy.web.controller.page;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.constants.JspConfig;
import zxy.entity.User;
import zxy.permission.support.PrivilegeContext;
import zxy.service.AccountService;
import zxy.service.UserService;
import zxy.utils.Utils;
import zxy.web.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeContext privilegeContext;

    @RequestMapping(path="login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, String account, String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/login");
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            if (accountService.login(account, password)) {
                User user = userService.getUserByAccount(account);
                HttpSession session = request.getSession();
                SessionManager.setCurrentUser(session, user);
                privilegeContext.initUserPrivilege(session, user.getId());

                Utils.requestRedirect(response, JspConfig.INDEX_URL);
                return null;
            }
            else {
                modelAndView.addObject("error", true);
            }
        }
        return modelAndView;
    }

    @RequestMapping(path="logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        SessionManager.setCurrentUser(session, null);
        session.invalidate();

        Utils.requestRedirect(response, JspConfig.LOGIN_URL);
        return null;
    }

}
