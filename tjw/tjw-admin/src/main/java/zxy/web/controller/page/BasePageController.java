package zxy.web.controller.page;

import org.springframework.web.servlet.ModelAndView;
import zxy.common.JsonResult;
import zxy.common.ResultCode;
import zxy.constants.JspConfig;

public class BasePageController {
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
