package zxy.web.controller.weixinqyh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zxy.common.utils.DateUtils;
import zxy.commons.JsonResult;
import zxy.weixin.qyh.domain.WeixinUser;
import zxy.weixin.qyh.support.ApiContactDelegate;
import zxy.weixin.qyh.support.IAppConfig;
import zxy.weixin.qyh.support.ICallbackConfig;

import javax.servlet.http.HttpServletRequest;

@Controller("weixinqyhClientController")
@RequestMapping("/weixin/qyh/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ICallbackConfig callbackConfig;
    @Autowired
    private IAppConfig appConfig;
    @Autowired
    private ApiContactDelegate apiContactDelegate;

    @RequestMapping(value = "/page")
    public ModelAndView page() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weixin/qyh/client");
        return modelAndView;
    }

    @RequestMapping("/date")
    @ResponseBody
    public Object date() {
        return JsonResult.buildSuccess(DateUtils.currentDateTimeStr());
    }

    @RequestMapping("/test/{num}")
    @ResponseBody
    public Object test(@PathVariable String num) {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            // ignore
        }
        return JsonResult.buildSuccess("你好" + num);
    }

    @RequestMapping("/userinfo")
    @ResponseBody
    public Object userinfo(HttpServletRequest request, String myappid) {
        String userid = SessionManager.getLoginUserid(request);
        WeixinUser weixinUser = apiContactDelegate.getUserDetail(myappid, userid);
        return JsonResult.buildSuccess(weixinUser);
    }

}
