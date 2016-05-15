package com.jasper.demo.springmvc.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public String login(String username, String password) {
		if ("admin".equals(username) && "123456".equals(password)) {
			return "success 成功";
		} else {
			return "fail 失败";
		}
	}

}
