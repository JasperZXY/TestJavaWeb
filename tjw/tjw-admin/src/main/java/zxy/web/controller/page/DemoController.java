package zxy.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping(path="/{subName}")
    public ModelAndView demo(@PathVariable String subName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo/" + subName);
        return mv;
    }

    @RequestMapping(path="/demo2string")
    public String demo2string() {
        return "demo/2";
    }
}
