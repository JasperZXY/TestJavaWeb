package zxy.common.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpUtilsTest {
    @Test
    public void get() throws Exception {
        System.err.println("1:" + HttpUtils.httpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=id&corpsecret=secrect"));
        Map<String, Object> params2 = new HashMap<>();
        params2.put("corpid", "todo");
        params2.put("corpsecret", "todo");
        System.err.println("2:" + HttpUtils.httpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken", params2));
    }

    @Test
    public void post() throws Exception {
        System.err.println("1:" + HttpUtils.httpPost("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=id&corpsecret=secrect"));
        Map<String, Object> params2 = new HashMap<>();
        params2.put("corpid", "todo");
        params2.put("corpsecret", "todo");
        System.err.println("2:" + HttpUtils.httpPost("https://qyapi.weixin.qq.com/cgi-bin/gettoken", params2));
        System.err.println("3:" + HttpUtils.httpPost("https://qyapi.weixin.qq.com/cgi-bin/gettoken", "corpid=id&corpsecret=secrect"));
    }
}
