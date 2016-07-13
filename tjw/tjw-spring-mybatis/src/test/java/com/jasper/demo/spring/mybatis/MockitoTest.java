package com.jasper.demo.spring.mybatis;

import com.jasper.demo.spring.mybatis.dao.UserDao;
import com.jasper.demo.spring.mybatis.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoTest {
    private UserDao userDao;

    @Before
    public void before() {
        userDao = mock(UserDao.class);
        User user = new User();
        user.setId(1);
        user.setName("嘻嘻");
        user.setBirthday(new Date());
        when(userDao.getUserById(1)).thenReturn(user);
    }

    @Test
    public void testList() {
        //创建mock对象，参数可以是类，也可以是接口
        List<String> list = mock(List.class);

        //设置方法的预期返回值
        when(list.get(0)).thenReturn("helloworld");

        String result = list.get(0);

        //验证方法调用(是否调用了get(0))
        verify(list).get(0);

        when(list.get(1)).thenThrow(new RuntimeException("list.get(1) test"));
        try {
            list.get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 与上面效果一样
        doThrow(new RuntimeException("list.get(2) test")).when(list).get(2);
        try {
            list.get(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //junit测试
        Assert.assertEquals("helloworld", result);
    }

    @Test
    public void testGetUser() {
        User user = userDao.getUserById(1);
        System.out.println(user);
        Assert.assertNotNull(user);

        user = userDao.getUserById(3);
        System.out.println(user);
        Assert.assertNull(user);
    }
}
