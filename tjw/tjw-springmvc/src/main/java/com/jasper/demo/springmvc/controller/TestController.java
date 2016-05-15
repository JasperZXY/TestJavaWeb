package com.jasper.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("")
	public String error(){
	    int[] a = {1};
	    System.out.println(a[1]);
	    return "hello";
	}
}
