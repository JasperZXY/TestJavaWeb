package zxy.web.handler;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.LockDelegate;
import zxy.web.controller.weixinqyh.SessionManager;
import zxy.weixin.base.WeixinException;
import zxy.weixin.qyh.support.ApiContactDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 微信企业号登录拦截，约定必带参数myappid与code
 */
public class WeixinQYHLoginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(WeixinQYHLoginInterceptor.class);
    private static final String MYAPPID = "myappid";
    private static final String CODE = "code";

    // key:code value:userid
    private final static Cache<String, String> useridCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();
    private LockDelegate<String> lockDelegate = new LockDelegate<>();

    @Autowired
    private ApiContactDelegate apiContactDelegate;

    /**
     * 多个请求可能一起过来，并且“code”参数有效性只有一次，会导致getUserId失败。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (SessionManager.isLogin(request)) {
            return true;
        }

        String code = request.getParameter(CODE);
        String myapid = request.getParameter(MYAPPID);
        if (StringUtils.isBlank(code)) {
            throw new WeixinException(WeixinException.PARAM_ERROR, "参数【code】不能为空");
        }
        if (StringUtils.isBlank(myapid)) {
            throw new WeixinException(WeixinException.PARAM_ERROR, "参数【myapid】不能为空");
        }

        logger.debug("go to get from cache. request:" + request);
        String userid = useridCache.getIfPresent(code);
        if (StringUtils.isNotBlank(userid)) {
            SessionManager.setLoginUserid(request, userid);
            logger.debug("get from cache. request:" + request);
            return true;
        }

        synchronized (lockDelegate.getLockObject(code)) {
            if (SessionManager.isLogin(request)) {
                logger.debug("get from session again. success. request:" + request);
                return true;
            }
            userid = useridCache.getIfPresent(code);
            if (StringUtils.isNotBlank(userid)) {
                SessionManager.setLoginUserid(request, userid);
                logger.debug("get from cache. success. request:" + request);
                return true;
            }
            logger.debug("get from api. request:" + request);

            userid = apiContactDelegate.getUserId(myapid, code);
            SessionManager.setLoginUserid(request, userid);
            useridCache.put(code, userid);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
