package com.jasper.demo.servlet;

import com.jasper.demo.utils.UserAgentUtil;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 测试结果，获取浏览器类型不准，关于手机型号，只能获取到Android的手机型号
 * @author Jasper.Zhong
 */
/*
iPhone QQ
Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13G36 QQ/6.5.3.410 V1_IPH_SQ_6.5.3_1_APP_A Pixel/750 Core/UIWebView NetType/WIFI Mem/208

win7 Chrome
Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36

华为7i UC浏览器
Mozilla/5.0 (Linux; U; Android 5.1.1; zh-CN; ATH-AL00 Build/HONORATH-AL00) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/11.0.5.841 U3/0.8.0 Mobile Safari/534.30

华为7i 微信
Mozilla/5.0 (Linux; Android 5.1.1; ATH-AL00 Build/HONORATH-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036824 Safari/537.36 MicroMessenger/6.3.25.861 NetType/WIFI Language/zh_CN
*/
public class UserAgentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Enumeration headerNames = req.getHeaderNames();
        System.out.println("header start");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement().toString();
            System.out.println(headerName + "=" + req.getHeader(headerName));
        }
        System.out.println("header end");
        String userAgentString = req.getHeader("User-Agent");
        UserAgent userAgentInfo = UserAgent.parseUserAgentString(userAgentString);
        StringBuilder userAgentInfoBuilder = new StringBuilder();
        userAgentInfoBuilder.append("浏览器：").append(userAgentInfo.getBrowser().getName()).append("<br/>");
        if (userAgentInfo.getBrowserVersion() != null) {
            userAgentInfoBuilder.append("浏览器版本：").append(userAgentInfo.getBrowserVersion().getVersion()).append("<br/>");
        }
        userAgentInfoBuilder.append("设备类型：").append(userAgentInfo.getOperatingSystem().getDeviceType().getName()).append("<br/>")
                .append("操作系统：").append(userAgentInfo.getOperatingSystem().getGroup().getName()).append("<br/>")
                .append("操作系统跟版本：").append(userAgentInfo.getOperatingSystem().getName()).append("<br/>")
                .append("制造商：").append(userAgentInfo.getOperatingSystem().getManufacturer().getName()).append("<br/>");
        if (UserAgentUtil.isMobile(userAgentInfo) && UserAgentUtil.isAndroid(userAgentInfo)) {
            userAgentInfoBuilder.append("手机型号：").append(UserAgentUtil.getPhoneModel(userAgentString)).append("<br/>");
        }
        System.out.println("return:" + userAgentInfoBuilder.toString());
        resp.getWriter().write(userAgentInfoBuilder.toString());
    }

}
