package zxy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zxy.utils.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(path="/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("subPage", "header");
        mv.addObject("date", new Date());
        return mv;
    }

    @RequestMapping(path="/demo1")
    public ModelAndView demo1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo1");
        return mv;
    }

    @RequestMapping(path="/demo2")
    public ModelAndView demo2() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo2");
        return mv;
    }

    @RequestMapping(path="/demo2string")
    public String demo2string() {
        return "demo2";
    }

    @RequestMapping(path="/api")
    @ResponseBody
    public Object api() {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("system_name", Constants.SYSTEM_NAME);
        retMap.put("now", new Date());
        return retMap;
    }

}
