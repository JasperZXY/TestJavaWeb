package com.jasper.demo.spring.mybatis.transaction;

import com.jasper.demo.spring.mybatis.dao.UserDao;
import com.jasper.demo.spring.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

// 辅助UserTransactionService用
@Service
public class UserTransactionAssistService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void m() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("test");

        userDao.addUser(user);
        System.out.println("addUser id:" + user.getId());

        System.out.println(userDao.getUserById(user.getId()));

        throw new RuntimeException();
    }

}
