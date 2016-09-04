package com.jasper.demo.spring;

import com.jasper.demo.spring.mybatis.dao.UserJdbcDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class UserJdbcDaoTest2 {
    @Autowired
    private UserJdbcDao userJdbcDao;

    @Test
    public void testGet() {
        System.out.println(userJdbcDao.getUserById(2));
    }
}
