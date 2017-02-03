package zxy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void send() {
        mailService.send("测试1", "test test test test", "zhong_xianyao@sina.com");
        mailService.send("测试2", "test test test test", "zhong_xianyao@sina.com");
    }
}
