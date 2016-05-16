package com.jasper.demo.springmvc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Value("${admin.username}")
	private String username;
	@Value("${admin.password}")
	private String password;
	
	public UserService() {
		System.out.println("UserService username:" + username + " password:" + password);
	}
	
	public String login(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password)) {
			return "success 成功";
		} else {
			return "fail 失败";
		}
	}

}
