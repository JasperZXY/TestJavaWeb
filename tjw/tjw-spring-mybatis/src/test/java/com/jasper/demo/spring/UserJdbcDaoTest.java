package com.jasper.demo.spring;

import com.jasper.demo.spring.mybatis.dao.UserJdbcDao;
import com.jasper.demo.spring.mybatis.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Calendar;
import java.util.Date;

@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class UserJdbcDaoTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private UserJdbcDao userJdbcDao;

    @Test
    public void testGet() {
        System.out.println(userJdbcDao.getUserById(2));
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("test");
        System.out.println("insert:" + user);
        int id = userJdbcDao.addUser(user);
        System.out.println("id:" + id);

        System.out.println(userJdbcDao.getUserById(id));
    }
}
