package com.jasper.demo.spring.mybatis.aspect;

import org.springframework.stereotype.Component;

public class UserManagerXml implements UserManager {

    @Override
    public String findUserById(int userId) {
        System.out.println("---------UserManagerXml.findUserById()--------");
        if (userId <= 0) {
            throw new IllegalArgumentException("该用户不存在!");
        }
        return "张三";
    }
}
