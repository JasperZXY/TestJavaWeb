package zxy.web.controller.weixinqyh;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.ImageDelegate;
import zxy.commons.JsonResult;
import zxy.commons.PermissionCode;
import zxy.permission.support.PermissionAnnotation;
import zxy.weixin.qyh.support.ApiContactDelegate;
import zxy.weixin.qyh.support.ApiSendMessageDelegate;
import zxy.weixin.qyh.support.IAgentIdConfig;
import zxy.weixin.qyh.support.IAppConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("weixinqyhManagerController")
@RequestMapping("/weixin/qyh/manager")
public class ManagerController {
    @Autowired
    private IAppConfig appConfig;
    @Autowired
    private IAgentIdConfig agentIdConfig;
    @Autowired
    private ApiContactDelegate apiContactDelegate;
    @Autowired
    private ApiSendMessageDelegate apiSendMessageDelegate;

    @PermissionAnnotation(code = PermissionCode.WEIXIN_QYH_MANAGER)
    @RequestMapping(value = "/list")
    public ModelAndView listQYH() {
        List<Map> list = new ArrayList<>();
        appConfig.listAllMyappid().forEach((myappid) -> {
            Map<String, Object> map = new HashedMap();
            map.put("myappid", myappid);
            map.put("corpId", appConfig.getCropId(myappid));
            map.put("qrCodeUrl", appConfig.getQRcodeUrl(myappid));
            list.add(map);
        });

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weixin/qyh/list");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @PermissionAnnotation(code = PermissionCode.WEIXIN_QYH_MANAGER)
    @RequestMapping("/to_adduser")
    public ModelAndView toAddUser(String myappid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weixin/qyh/adduser");
        modelAndView.addObject("myappid", myappid);
        return modelAndView;
    }

    @PermissionAnnotation(code = PermissionCode.WEIXIN_QYH_MANAGER)
    @RequestMapping("/adduser")
    @ResponseBody
    public Object addUser(String myappid, String userid, String name, String mobile) {
        String result = apiContactDelegate.addUser(myappid, userid, name, null, mobile);
        if (StringUtils.isBlank(result)) {
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildFail(result);
    }

    /**
     * 获取所有的应用，后续可改为从微信那里获取
     */
    @PermissionAnnotation(code = PermissionCode.WEIXIN_QYH_MANAGER)
    @RequestMapping("/agent/list/{myappid}")
    public ModelAndView listAgent(@PathVariable String myappid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weixin/qyh/listAgent");
        modelAndView.addObject("myappid", myappid);

        List<Map> list = new ArrayList<>();
        list.add(toMap("agentId", 0, "name", "企业小助手", "canSendMsg", true));
        list.add(toMap("agentId", agentIdConfig.getJava(myappid), "name", "Java", "canSendMsg", true));
        list.add(toMap("agentId", agentIdConfig.getAndroid(myappid), "name", "Android", "canSendMsg", true));
        list.add(toMap("agentId", agentIdConfig.getPageapp(myappid), "name", "主页型应用", "canSendMsg", false));

        modelAndView.addObject("list", list);
        return modelAndView;
    }

    private Map<String, Object> toMap(Object... items) {
        Map<String, Object> map = new HashedMap();
        for (int i=0; i<items.length; i+=2) {
            map.put((String)items[i], items[i + 1]);
        }
        return map;
    }

    @PermissionAnnotation(code = PermissionCode.WEIXIN_QYH_MANAGER)
    @RequestMapping("/send/{msgType}")
    @ResponseBody
    public Object sendText(@PathVariable String msgType, String myappid, int agentId, String msg) {
        boolean status = false;
        switch (msgType) {
            case "text":
                status = apiSendMessageDelegate.sendText(myappid, apiSendMessageDelegate.toAllUserForSendMessage(), null, agentId, msg);
                break;
            case "news":
                status = apiSendMessageDelegate.sendNews(myappid, apiSendMessageDelegate.toAllUserForSendMessage(), null, agentId, msg,
                        msg + "\n微信企业号接口调试工具", "http://qydev.weixin.qq.com/debug", ImageDelegate.randomImgage());
                break;
            default:
                return JsonResult.buildFail("不识别的消息类型");
        }

        if (status) {
            return JsonResult.buildSuccess(null);
        }
        else {
            return JsonResult.buildFail("发送失败");
        }
    }

    @RequestMapping("/tool/{myappid}")
    public ModelAndView tool(HttpServletRequest request, @PathVariable String myappid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weixin/qyh/tool");
        modelAndView.addObject("myappid", myappid);
        modelAndView.addObject("cropId", appConfig.getCropId(myappid));
        return modelAndView;
    }

}
