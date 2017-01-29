package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalController {
    @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
