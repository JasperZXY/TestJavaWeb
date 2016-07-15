package com.jasper.demo.spring.mybatis.transaction;

import com.jasper.demo.spring.mybatis.dao.UserDao;
import com.jasper.demo.spring.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserTransactionService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTransactionAssistService userTransactionAssistService;

    // 未受检异常（运行时异常），会回滚
    @Transactional
    public void unCheckedException() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("test");

        userDao.addUser(user);
        System.out.println("addUser id:" + user.getId());

        System.out.println(userDao.getUserById(user.getId()));

        throw new RuntimeException();
    }

    // 受检异常，不会回滚
    @Transactional
    public void checkedException() throws Exception {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("test");

        userDao.addUser(user);
        System.out.println("addUser id:" + user.getId());

        System.out.println(userDao.getUserById(user.getId()));

        throw new Exception();
    }

    private void innerMethod_() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("test");

        userDao.addUser(user);
        System.out.println("addUser id:" + user.getId());

        System.out.println(userDao.getUserById(user.getId()));

        throw new RuntimeException();
    }

    // 会回滚
    @Transactional
    public void innerMethod() {
        innerMethod_();
    }

    // 调用其他类的声明了事务的方法并捕获异常，会回滚
    public void otherClassMethod() {
        try {
            userTransactionAssistService.m();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 调用当前类的声明了事务的方法并捕获异常，不会回滚
    public void currentClassMethod() {
        try {
            unCheckedException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 超时，时间是秒，超时设置无效
    @Transactional(timeout = 1)
    public void timeout() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("test");

        userDao.addUser(user);
        System.out.println("addUser id:" + user.getId());

        System.out.println(userDao.getUserById(user.getId()));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        user.setId(null);
        userDao.addUser(user);
        System.out.println("addUser id:" + user.getId());
    }

}
