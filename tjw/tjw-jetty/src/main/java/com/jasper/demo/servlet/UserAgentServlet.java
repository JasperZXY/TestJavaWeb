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
 * @author Jasper.Zhong
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
        userAgentInfoBuilder.append("浏览器类型：").append(userAgentInfo.getBrowser().getBrowserType()).append("<br/>")
                .append("浏览器版本：").append(userAgentInfo.getBrowserVersion()).append("<br/>")
                .append("浏览器版本：").append(userAgentInfo.getBrowser().getBrowserType().getName()).append("<br/>")
                .append("设备类型：").append(userAgentInfo.getOperatingSystem().getDeviceType().getName()).append("<br/>")
                .append("操作系统：").append(userAgentInfo.getOperatingSystem().getGroup().getName()).append("<br/>")
                .append("操作系统跟版本：").append(userAgentInfo.getOperatingSystem().getName()).append("<br/>")
                .append("制造商：").append(userAgentInfo.getOperatingSystem().getManufacturer().getName()).append("<br/>");
        if (UserAgentUtil.isMobile(userAgentInfo)) {
            userAgentInfoBuilder.append("手机型号：").append(UserAgentUtil.getPhoneModel(userAgentString)).append("<br/>");
        }
        resp.getWriter().write(userAgentInfoBuilder.toString());
    }

}
