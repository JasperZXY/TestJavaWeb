package com.jasper.demo.springmvc.controller;

import com.jasper.demo.springmvc.bean.DateObject;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

//    @InitBinder
//    public void initDate(WebDataBinder binder) {
//        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd--HH:mm"));
//    }


    // 主要测试时间转换
    @RequestMapping("/now")
    @ResponseBody
    public Object now(DateObject dateObject) {
        Map<String, Object> retMap = new HashMap<>();
        DateObject object = new DateObject();
        object.setDate(new Date());

        retMap.put("old", dateObject);
        retMap.put("new", object);
        return retMap;
    }

    /*
    测试的时候，需要配置host
        127.0.0.1 java.zxy
        127.0.0.1 html.zxy
    对应的页面  http://html.zxy:8083/tjw-springmvc/static/uploadtest.html
     */
    @RequestMapping(path="/file/upload", method= RequestMethod.POST)
    @ResponseBody
//    @CrossOrigin(origins = "${web.origin}")
//    @CrossOrigin(origins = {"http://html.zxy:8083", "http://html1.zxy:8083"})
    public Object uploadFile(MultipartFile file) throws Exception {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("Name", file.getName());
        retMap.put("OriginalFilename", file.getOriginalFilename());
        retMap.put("size", file.getSize());
        return retMap;
    }


}
