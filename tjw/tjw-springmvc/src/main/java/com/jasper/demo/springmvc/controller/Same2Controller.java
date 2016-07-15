package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/same")
public class Same2Controller {

    // 如果id传的是1，则调用Same1Controller中的方法
    @RequestMapping("/test/{id}")
    @ResponseBody
    public Object m(@PathVariable("id") Integer id) {
        return "same 2:" + id;
    }

    // 会跟Same1Controller中的方法冲突，运行失败，上面的则可以运行
//    @RequestMapping("/test/1")
//    @ResponseBody
//    public Object m() {
//        return "same 2";
//    }
}
