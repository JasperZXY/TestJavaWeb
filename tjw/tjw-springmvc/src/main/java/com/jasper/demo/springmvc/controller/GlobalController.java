package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class GlobalController {
    @RequestMapping("/error")
    public ModelAndView error(String msg) {
        return new ModelAndView("error", Collections.singletonMap("msg", msg));
    }
}
