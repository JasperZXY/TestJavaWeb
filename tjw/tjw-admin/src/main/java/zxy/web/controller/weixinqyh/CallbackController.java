package zxy.web.controller.weixinqyh;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zxy.common.utils.JsonUtils;
import zxy.weixin.qyh.domain.receive.BaseReceiveObject;
import zxy.weixin.qyh.support.IAppConfig;
import zxy.weixin.qyh.support.ICallbackConfig;
import zxy.weixin.qyh.utils.WeixinXmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * <a href="http://qydev.weixin.qq.com/wiki/index.php?title=回调模式">微信回调模式</a><br/>
 * <p>
 * <a href="http://qydev.weixin.qq.com/wiki/index.php?title=加解密库下载与返回码">加解密库下载与返回码</a><br/>
 * 异常java.security.InvalidKeyException:illegal Key Size的解决方案：<br/>
 * 在官方网站下载JCE无限制权限策略文件（请到官网下载对应的版本， 例如JDK7的下载地址：
 * http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html )：
 * 下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt。
 * 如果安装了JRE，将两个jar文件放到%JRE_HOME% \lib\security目录下覆盖原来的文件，
 * 如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件。
 */
@Controller("weixinqyhCallbackController")
@RequestMapping("/weixin/qyh/callback")
public class CallbackController {
    private static final Logger logger = LoggerFactory.getLogger(CallbackController.class);
    @Autowired
    private ICallbackConfig callbackConfig;
    @Autowired
    private IAppConfig appConfig;

    // 在微信的后台配置回调模式时，会发送一个GET请求来验证，配置一次调用一次，之后就不调用了，配置项数据需要实时
    @RequestMapping(value = "/{myappid}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(HttpServletRequest request, HttpServletResponse response,
                    @PathVariable("myappid") String myappid,
                    String msg_signature, String timestamp, String nonce,
                    String echostr) throws Exception {
        String cropId = appConfig.getCropId(myappid);
        String callbackToken = callbackConfig.getToken(myappid);
        String callbackEncodingAESKey = callbackConfig.getEncodingAESKey(myappid);

        logger.debug("get myappid:{}, corpId:{}, msg_signature:{}, timestamp:{}, nonce:{}, echostr:{} encodingAESKey:{}",
                myappid, cropId, msg_signature, timestamp, nonce, echostr, callbackEncodingAESKey);
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(callbackToken, callbackEncodingAESKey, cropId);
        String echoStr = StringUtils.EMPTY; //需要返回的明文
        try {
            echoStr = wxcpt.VerifyURL(msg_signature, timestamp,
                    nonce, echostr);
            logger.debug("get verifyurl echostr: " + echoStr);
            // 验证URL成功，将sEchoStr返回
//            BaseServletUtil.sendText(response, echoStr);
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            logger.debug("get error.", e);
        }
        // 验证URL成功，将echoStr返回
        return echoStr;
    }

    /**
     * 真正的事件回调，POST请求
     * <br/>
     * 注意：加上了回调模式后，原有的菜单点击自动回复消息将失效，要自己实现
     */
    @RequestMapping(value = "/{myappid}", method = RequestMethod.POST)
    @ResponseBody
    public Object post(HttpServletRequest request, HttpServletResponse response,
                     @PathVariable("myappid") String myappid,
                     String msg_signature, String timestamp, String nonce) throws Exception {
        String cropId = appConfig.getCropId(myappid);
        String callbackToken = callbackConfig.getToken(myappid);
        String callbackEncodingAESKey = callbackConfig.getEncodingAESKey(myappid);

        try {
            String requestData = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(callbackToken, callbackEncodingAESKey, cropId);
            String sourceMsg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, requestData);
            logger.debug("post myappid:{}, corpId:{}, msg_signature:{}, timestamp:{}, nonce:{}, requestData:{}, sMsg:{}",
                    myappid,cropId, msg_signature, timestamp, nonce, requestData, sourceMsg);

            BaseReceiveObject baseReceiveObject = WeixinXmlUtil.xmlToObject(sourceMsg);
            logger.debug("post " + JsonUtils.toString(baseReceiveObject));
        } catch (Exception e) {
            logger.error("post error:", e);
        }
        return null;
    }
}
