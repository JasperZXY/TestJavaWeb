package zxy.web.handler;

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
import zxy.common.utils.HttpUtils;
import zxy.common.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
            Object loginUser = null;
            logger.debug("beforeBodyWrite IP:{}\n \tloginuser:{}\n \tuser-agent:{}\n \turl:{}\n \tresult:{}",
                HttpUtils.getRemoteIP(httpServletRequest), JsonUtils.toString(loginUser),
                httpServletRequest.getHeader("user-agent"), urlBuilder.toString(), JsonUtils.toString(result));
        }
        return result;
    }
}