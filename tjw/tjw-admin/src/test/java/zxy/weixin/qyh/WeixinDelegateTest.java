package zxy.weixin.qyh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zxy.common.utils.DateUtils;
import zxy.commons.ImageDelegate;
import zxy.weixin.qyh.support.IAgentIdConfig;
import zxy.weixin.qyh.support.IAppConfig;
import zxy.weixin.qyh.support.SendMessageDelegate;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class WeixinDelegateTest {
    private static final Logger logger = LoggerFactory.getLogger(WeixinDelegateTest.class);

    @Autowired
    private SendMessageDelegate sendMessageDelegate;
    @Autowired
    private IAppConfig appConfig;
    @Autowired
    private IAgentIdConfig agentIdConfig;

    @Test
    public void sendMessage() {
        String myappid = appConfig.listAllMyappid().toArray(new String[1])[0];
        String user = "zhongxianyao";

        boolean status = sendMessageDelegate.sendText(myappid, Arrays.asList(user, "no_this_user"), null,
                agentIdConfig.getJava(myappid), "test now:" + DateUtils.dateToString(new Date()));
        logger.info("sendText status:" + status);

        status = sendMessageDelegate.sendNews(myappid, Arrays.asList(user), null, agentIdConfig.getJava(myappid),
                "测试发一下图文消息", "既然是描述信息，那就长一点，嘻嘻！\n微信企业号接口调试工具",
                "http://qydev.weixin.qq.com/debug",
                ImageDelegate.randomImgage());
        logger.info("sendNews status:" + status);
    }
}
