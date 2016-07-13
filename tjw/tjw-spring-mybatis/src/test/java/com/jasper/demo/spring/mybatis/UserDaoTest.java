package com.jasper.demo.spring.mybatis;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.jasper.demo.spring.mybatis.dao.UserDao;
import com.jasper.demo.spring.mybatis.entity.User;

//@Ignore
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class UserDaoTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testGetById() {
		System.out.println(userDao);
		System.out.println(userDao.getUserById(1));
		System.out.println(userDao.getUserById(2));
	}
	
	// 测试返回的结果是，有数据的则返回，没有的不返回，并且被默认安装主键排序了
	@Test
	public void testGetByIds() {
		List<Integer> uids = Arrays.asList(43, 41, 45, 100000, 42, 45);
		System.out.println(uids);
		System.out.println(userDao.getUsersByIds(uids));
	}
	
	@Test
	public void testSelectByName() {
		System.out.println(userDao.selectByName("1"));
	}
	
	@Test
	public void testAdd() {
		Random random = new Random();
		for (int i=0; i<30; i++) {
			User user = new User();
			Calendar calendar = Calendar.getInstance();
			calendar.set(1950 + random.nextInt(50), random.nextInt(12), random.nextInt(27) + 1);
			user.setBirthday(calendar.getTime());
			user.setName("test" + i);
			userDao.addUser(user);
			System.out.println(i + " id:" + user.getId());
		}
	}

}
