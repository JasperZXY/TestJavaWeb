package com.jasper.demo.springmvc.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *  异常处理器
 *
 *  可惜不能根据不同情况返回数据，ResponseBody与ModelAndView
 */
@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("msg", ex);
        return mv;
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Object exception(Exception ex, HttpServletRequest request) {
//        printLog(ex, request);
//        Map<String, Object> retMap = new HashMap<>();
//        retMap.put("status", "Exception");
//        retMap.put("msg", ex.getMessage());
//        return retMap;
//    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object nullPointerException(NullPointerException ex, HttpServletRequest request) {
        printLog(ex, request);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("status", "NullPointerException");
        retMap.put("msg", ex.getMessage());
        return retMap;
    }

    private void printLog(Exception ex, HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (queryString != null && !"".equals(queryString)) {
            urlBuilder.append("?").append(queryString);
        }
        logger.error("Exception url:{}", urlBuilder.toString(), ex);
    }
}
