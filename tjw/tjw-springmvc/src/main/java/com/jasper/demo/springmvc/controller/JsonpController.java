package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

@Controller
@RequestMapping("/jsonp")
public class JsonpController {

    // 特别注意，要使用ResponseBody，在有callback参数的情况下，会以jsonp的形式返回，没有则直接以json的形式返回。
    @RequestMapping("")
    @ResponseBody
    public Object index() {
        return Collections.singletonMap("hello", "你好");
    }
}
