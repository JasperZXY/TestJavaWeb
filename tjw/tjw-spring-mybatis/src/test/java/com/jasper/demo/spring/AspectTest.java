package com.jasper.demo.spring;

import com.jasper.demo.spring.mybatis.aspect.UserManager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

//@Ignore
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class AspectTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private UserManager userManagerXml;
    @Autowired
    private UserManager userManagerAnnounce;

    @Test
    public void aspectXml() {
        userManagerXml.findUserById(1);

        System.out.println("======================");

        try {
            // 查不到数据，会抛异常，异常会被AfterThrowingAdvice捕获
            userManagerXml.findUserById(0);
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void aspectAnnounce() {
        userManagerAnnounce.findUserById(1);

        System.out.println("======================");

        try {
            // 查不到数据，会抛异常，异常会被AfterThrowingAdvice捕获
            userManagerAnnounce.findUserById(0);
        } catch (IllegalArgumentException e) {
        }
    }
}
