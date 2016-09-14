package com.jasper.demo.servlet;

import com.jasper.demo.entity.UserAgentInfo;
import com.jasper.demo.utils.UserAgentUtil;

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
//        UserAgentInfo userAgentInfo = UserAgentUtil.getUserAgent(req.getHeader("User-Agent"));
//        StringBuilder userAgentInfoBuilder = new StringBuilder();
//        userAgentInfoBuilder.append("浏览器类型：").append(userAgentInfo.getBrowserType()).append("<br/>")
//                .append("浏览器版本：").append(userAgentInfo.getBrowserVersion()).append("<br/>")
//                .append("平台类型：").append(userAgentInfo.getPlatformType()).append("<br/>")
//                .append("平台系列：").append(userAgentInfo.getPlatformSeries()).append("<br/>")
//                .append("平台版本：").append(userAgentInfo.getPlatformVersion());
//        resp.getWriter().write(userAgentInfoBuilder.toString());
        resp.getWriter().write(req.getHeader("User-Agent"));
    }
}
