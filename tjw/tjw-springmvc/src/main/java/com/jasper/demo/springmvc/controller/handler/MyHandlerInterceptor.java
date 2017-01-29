package com.jasper.demo.springmvc.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class MyHandlerInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(MyHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle request:{} response:{} handler:{}", request, response, handler);
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            RequestMapping requestMapping = method.getMethodAnnotation(RequestMapping.class);
            // 这里可以拿到方法上的注解，还可以进行权限拦截
            logger.debug("path:{}", Arrays.toString(requestMapping.path()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}