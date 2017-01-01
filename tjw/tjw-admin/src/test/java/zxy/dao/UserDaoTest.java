package zxy.dao;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zxy.JsonPrettyUtils;
import zxy.constants.EntityStatus;
import zxy.entity.User;

import java.io.File;
import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class UserDaoTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void initAdmin() {
        User user = new User();
        user.setId(1);
        user.setAccount("admin");
        user.setPassword("123456");
        user.setName("管理员");
        user.setCreatetime(new Date());
        user.setStatus(EntityStatus.VALID);
        userMapper.insert(user);
    }

    @Test
    public void initData() throws Exception {
        long account = 10000;
        String password = "123456";
        String[] familyNames = {"李", "张", "王", "钟", "赵", "欧阳", "乔", "金", "司马"};
        File namesFile = new File(this.getClass().getClassLoader().getResource("names").getFile());
        String allNams = FileUtils.readFileToString(namesFile);
        String[] names = allNams.split(",");
        Random random = new Random();
        // 2000-01-01 00:00:00 对应的时间戳 946656000
        long baseTime = 946656000000L;
        for (int i=0; i<200; i++) {
            account ++;
            User user = new User();
            user.setAccount(Long.toString(account));
            user.setPassword(password);
            user.setName(familyNames[random.nextInt(familyNames.length)]
                    + names[random.nextInt(names.length)]);
            user.setBirthday(new Date(baseTime + random.nextInt() * 100L));
            user.setCreatetime(new Date());
            user.setStatus(EntityStatus.VALID);
            userMapper.insert(user);
        }
    }

    @Test
    public void get() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(JsonPrettyUtils.toString(user));
        Assert.assertNotNull(user);
        Assert.assertEquals("admin", user.getAccount());
    }

}
