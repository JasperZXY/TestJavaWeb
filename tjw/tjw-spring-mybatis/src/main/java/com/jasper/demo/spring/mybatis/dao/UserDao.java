package com.jasper.demo.spring.mybatis.dao;

import com.jasper.demo.spring.mybatis.entity.User;

public interface UserDao {
	public User getUserById(int id);
	public int addUser(User user);
	
}
