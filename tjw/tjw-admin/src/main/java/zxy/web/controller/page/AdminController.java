package zxy.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zxy.constants.JspConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(path="/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("date", new Date());
        return mv;
    }

    @RequestMapping(path="/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("date", new Date());
        return mv;
    }

    @RequestMapping(path="/demo/{subName}")
    public ModelAndView demo(@PathVariable String subName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo/" + subName);
        return mv;
    }

    @RequestMapping(path="/demo2string")
    public String demo2string() {
        return "demo/2";
    }

    @RequestMapping(path="/api")
    @ResponseBody
    public Object api() {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("system_name", JspConfig.SYSTEM_NAME);
        retMap.put("now", new Date());
        return retMap;
    }

}
