package zxy.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/")
public class AdminController {

    @RequestMapping(path = "/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");
        mv.addObject("date", new Date());
        return mv;
    }

    @RequestMapping(path = "/404")
    public String _404() {
        return "admin/404";
    }

    @RequestMapping(path = "/500")
    public String _500() {
        return "admin/500";
    }

    @RequestMapping(path = "/error")
    public String error() {
        return "admin/error";
    }


}
