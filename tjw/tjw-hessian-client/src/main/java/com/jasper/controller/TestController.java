package com.jasper.controller;

import com.jasper.client.service.MathServiceClient2;
import com.jasper.client.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jasper.Zhong
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MathServiceClient2 mathService;
    @Autowired
    private OtherService otherService;

    @RequestMapping("/add")
    @ResponseBody
    public Object add(int a, int b) {
        return mathService.add(a, b, 0);
    }

    @RequestMapping("/other")
    @ResponseBody
    public Object other() {
        return otherService.hello();
    }
}
