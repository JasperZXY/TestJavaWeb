package com.jasper.demo.spring.mybatis.dao;

import java.util.List;

import com.jasper.demo.spring.mybatis.entity.User;

public interface UserDao {
	public User getUserById(int id);
	public List<User> getUsersByIds(List<Integer> uids);
	// 模糊查找
	public List<User> selectByName(String name);
	public int addUser(User user);
	
}
