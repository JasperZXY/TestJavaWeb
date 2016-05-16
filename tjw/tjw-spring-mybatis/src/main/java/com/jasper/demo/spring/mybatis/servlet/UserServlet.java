package com.jasper.demo.spring.mybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jasper.demo.spring.mybatis.dao.UserDao;
import com.jasper.demo.spring.mybatis.entity.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = -5129051963403241138L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext wac =   
	            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		UserDao userDao = wac.getBean(UserDao.class);
		String id = req.getParameter("id");
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		User user = userDao.getUserById(Integer.parseInt(id));
		if (user == null) {
			resp.getWriter().write("空".toString());
		} else {
			resp.getWriter().write(user.toString());
		}
	}

}
