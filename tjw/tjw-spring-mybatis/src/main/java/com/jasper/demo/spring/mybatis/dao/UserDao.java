package com.jasper.demo.spring.mybatis.dao;

import java.util.List;

import com.jasper.demo.spring.mybatis.entity.User;

public interface UserDao {
	User getUserById(int id);
	List<User> getUsersByIds(List<Integer> uids);
	// 模糊查找
	List<User> selectByName(String name);
	int addUser(User user);
	int batchAddUser(List<User> list);
	
}
