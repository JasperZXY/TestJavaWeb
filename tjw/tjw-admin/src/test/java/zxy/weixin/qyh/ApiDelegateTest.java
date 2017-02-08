package zxy.weixin.qyh;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zxy.JsonPrettyUtils;
import zxy.common.utils.DateUtils;
import zxy.commons.ImageDelegate;
import zxy.weixin.qyh.domain.WeixinDepartment;
import zxy.weixin.qyh.support.ApiContactDelegate;
import zxy.weixin.qyh.support.IAgentIdConfig;
import zxy.weixin.qyh.support.IAppConfig;
import zxy.weixin.qyh.support.ApiSendMessageDelegate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class ApiDelegateTest {
    private static final Logger logger = LoggerFactory.getLogger(ApiDelegateTest.class);

    @Autowired
    private IAppConfig appConfig;
    @Autowired
    private IAgentIdConfig agentIdConfig;

    @Autowired
    private ApiSendMessageDelegate apiSendMessageDelegate;
    @Autowired
    private ApiContactDelegate apiContactDelegate;

    private String myappid;
    private String userid;

    @Before
    public void before() {
        myappid = appConfig.listAllMyappid().toArray(new String[1])[0];
        userid = "zhongxianyao";
    }

    @Test
    public void sendMessage() {
        boolean status = apiSendMessageDelegate.sendText(myappid, Arrays.asList(userid, "no_this_user"), null,
                agentIdConfig.getJava(myappid), "test now:" + DateUtils.dateToString(new Date()));
        logger.info("sendText status:" + status);

        status = apiSendMessageDelegate.sendNews(myappid, Arrays.asList(userid), null, agentIdConfig.getJava(myappid),
                "测试发一下图文消息", "既然是描述信息，那就长一点，嘻嘻！\n微信企业号接口调试工具",
                "http://qydev.weixin.qq.com/debug",
                ImageDelegate.randomImgage());
        logger.info("sendNews status:" + status);
    }

    @Test
    public void getUserDetail() {
        logger.info("getUserDetail " + JsonPrettyUtils.toString(apiContactDelegate.getUserDetail(myappid, userid)));
    }

    @Test
    public void getDepartmentMember() {
        List<WeixinDepartment> dempartments = apiContactDelegate.listDepartment(myappid);
        logger.info("dempartments " + JsonPrettyUtils.toString(dempartments));
        for (WeixinDepartment department : dempartments) {
            logger.info("department[{}] usrs:{}",
                    department.getName(),
                    JsonPrettyUtils.toString(apiContactDelegate.getDepartmentMember(myappid, department.getId())));
        }

    }

}
