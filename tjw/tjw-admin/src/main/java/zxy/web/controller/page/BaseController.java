package zxy.web.controller.page;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    public ModelAndView toErrorView(String msg) {
        ModelAndView modelAndView = new ModelAndView("admin/error");
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }
}
