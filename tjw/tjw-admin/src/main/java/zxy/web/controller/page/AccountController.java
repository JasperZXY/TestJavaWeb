package zxy.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.JsonResult;
import zxy.commons.LogCode;
import zxy.commons.ResultCode;
import zxy.commons.JspConfig;
import zxy.service.AccountService;
import zxy.service.LoginfoService;
import zxy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/account")
public class AccountController extends BasePageController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginfoService loginfoService;
    @Autowired
    private UserService userService;

    @RequestMapping(path="password/toreset")
    public ModelAndView toResetPassword() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("account/resetpassword");
        return mv;
    }

    @ResponseBody
    @RequestMapping(path="password/code_for_reset")
    public Object createCodeForRestPassword(String account, String email) {
        ResultCode resultCode = accountService.createCodeForResetPassword(account, email);
        return JsonResult.build(resultCode);
    }

    @RequestMapping(path="password/reset")
    public ModelAndView resetPassword(HttpServletRequest request, HttpServletResponse response,
                                      String account, String email, String code, String password) {
        ResultCode resultCode = accountService.resetPassword(account, email, code, password);
        if (ResultCode.SUCCESS == resultCode) {
            loginfoService.addLog(request, LogCode.ACCOUNT_RESET_PASSWORD, "重置密码", userService.getUserByAccount(account).getId());
            return redirectToLogin(response);
        }
        ModelAndView view = new ModelAndView();
        view.addObject("account", account);
        view.addObject("email", email);
        view.setViewName("account/resetpassword");
        view.addObject(JspConfig.KEY_MSG, resultCode.getCndesc());
        return view;
    }

}
