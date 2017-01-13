package com.jasper.demo.springmvc.controller.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * 打印日志
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

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

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (logger.isDebugEnabled() && (serverHttpRequest instanceof ServletServerHttpRequest)) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpServletRequest httpServletRequest = servletServerHttpRequest.getServletRequest();
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(httpServletRequest.getRequestURI());
            String queryString = httpServletRequest.getQueryString();
            if (queryString != null && !"".equals(queryString)) {
                urlBuilder.append("?").append(queryString);
            }
            try {
                logger.debug("beforeBodyWrite user-agent:{}\n \turl:{}\n \tresult:{}",
                        httpServletRequest.getHeader("user-agent"), urlBuilder.toString(), mapper.writeValueAsString(result));
            } catch (JsonProcessingException e) {
                // ignore
            }
        }
        return result;
    }
}