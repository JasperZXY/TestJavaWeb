package zxy.web.controller.page;

import org.springframework.web.servlet.ModelAndView;
import zxy.commons.ResultCode;
import zxy.commons.JspConfig;
import zxy.utils.Utils;

import javax.servlet.http.HttpServletResponse;

public class BasePageController {
    public ModelAndView toLogin() {
        ModelAndView modelAndView = new ModelAndView("admin/login");
        return modelAndView;
    }

    public ModelAndView redirectToLogin(HttpServletResponse response) {
        Utils.requestRedirect(response, JspConfig.LOGIN_URL);
        return null;
    }

    public ModelAndView toErrorView(String msg) {
        ModelAndView modelAndView = new ModelAndView("admin/error");
        modelAndView.addObject(JspConfig.KEY_MSG, msg);
        return modelAndView;
    }

    public ModelAndView toErrorView(ResultCode resultCode) {
        ModelAndView modelAndView = new ModelAndView("admin/error");
        modelAndView.addObject(JspConfig.KEY_MSG, resultCode.getCndesc());
        return modelAndView;
    }
}
