package zxy.service;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zxy.JunitUtils;
import zxy.common.ResultCode;
import zxy.common.ServiceException;
import zxy.entity.User;
import zxy.utils.Utils;

import java.io.File;
import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void initRootUser() {
        User user = new User();
        user.setAccountId("root");
        user.setName("root用户");
        String password = "zxy@123";

        String result = userService.add(user, password, null);
        System.out.println("result :" + result);
    }

    @Test
    public void addAgain() {
        User user = new User();
        user.setAccountId("root");
        user.setName("root用户");
        String password = "zxy@123";

        try {
            String result = userService.add(user, password, null);
            System.out.println("result:" + result);
            JunitUtils.shouldNotHappen();
        } catch (ServiceException e) {
            System.out.println("ServiceException code:" + e.getCode() + " message:" + e.getMessage());
            Assert.assertEquals(ResultCode.ACCOUNT_EXIST, e.getCode());
        }
    }

    @Test
    public void initData() throws Exception {
        String password = "123456";
        String[] familyNames = {"李", "张", "王", "钟", "赵", "欧阳", "乔", "金", "司马"};
        File namesFile = new File(this.getClass().getClassLoader().getResource("names").getFile());
        String allNams = FileUtils.readFileToString(namesFile);
        String[] names = allNams.split(",");
        Random random = new Random();
        // 2000-01-01 00:00:00 对应的时间戳 946656000
        long baseTime = 946656000000L;
        for (int i = 0; i < 200; i++) {
            User user = new User();
            user.setName(familyNames[random.nextInt(familyNames.length)]
                    + names[random.nextInt(names.length)]);
            user.setAccountId(PinyinHelper.convertToPinyinString(user.getName(), "", PinyinFormat.WITHOUT_TONE) + Utils.randomString(6));
            user.setBirthday(new Date(baseTime + random.nextInt() * 100L));
            user.setCreatetime(new Date());
            try {
                userService.add(user, password, user.getAccountId() + "@zxy.com");
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}
