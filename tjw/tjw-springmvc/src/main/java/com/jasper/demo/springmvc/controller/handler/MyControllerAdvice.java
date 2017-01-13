package com.jasper.demo.springmvc.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *  处理全局异常
 *  
 *  或者用配置文件的形式，配置appContext.xml，
 *  见org.springframework.web.servlet.handler.SimpleMappingExceptionResolver
 *  
 *  还是用@ControllerAdvice方便些，可以获取到异常类型，配置文件的形式不知怎么获取
 *
 *  整合到了MyExceptionHandler
 */
//@ControllerAdvice
public class MyControllerAdvice {
	@ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("msg", ex);
        return mv;
    }
}
