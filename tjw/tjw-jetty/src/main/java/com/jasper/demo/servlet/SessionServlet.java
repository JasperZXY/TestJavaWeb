package com.jasper.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = -8294914286887695619L;
	private static final String LASTTIME = "lasttime";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		Object object = session.getAttribute(LASTTIME);
		if (object == null) {
			resp.getWriter().write("无");
		} else {
			resp.getWriter().write(object.toString());
		}
		session.setAttribute(LASTTIME, new Date());
	}
	

}
