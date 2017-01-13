package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("")
    public String error() {
        int[] a = {1};
        System.out.println(a[1]);
        return "hello";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(HttpServletRequest request, Integer a, Integer b) {
        return Collections.singletonMap("result", a + b);
    }
}
