package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/same/test")
public class Same1Controller {

    @RequestMapping("/1")
    @ResponseBody
    public Object m() {
        return "same 1";
    }
}
