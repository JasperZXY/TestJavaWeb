package com.jasper.demo.springmvc.webutil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

public class LogInterceptor implements HandlerInterceptor {
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        // to enable standard indentation ("pretty-printing"):
        // mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 注意这里，如果返回false，则请求被拦截
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj, ModelAndView modelAndView) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(httpServletRequest.getRequestURI()).append("?");
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null && !"".equals(queryString)) {
            url.append(queryString);
        }
        System.out.println(String.format("postHandle \trequest:%s\n \tuser-agent:%s\n \tobj:%s\n \tmodelAndView:%s",
                url.toString(), httpServletRequest.getHeader("user-agent"), mapper.writeValueAsString(obj), modelAndView));
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
