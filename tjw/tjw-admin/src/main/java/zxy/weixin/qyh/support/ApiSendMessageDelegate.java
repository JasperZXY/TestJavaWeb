package zxy.weixin.qyh.support;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zxy.common.utils.JsonUtils;
import zxy.utils.Utils;
import zxy.weixin.base.Constants;

import java.util.*;

/**
 * 发送消息
 */
@Component
public class ApiSendMessageDelegate {
    private static final Logger logger = LoggerFactory.getLogger(ApiSendMessageDelegate.class);

    private static final String urlSendMessageFormat = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

    @Autowired
    private ApiBaseDelegate apiBaseDelegate;

    public List<String> toAllUserForSendMessage() {
        return Arrays.asList("@all");
    }

    private boolean sendMessage(String myappid, Map<String, Object> param) {
        String url = urlSendMessageFormat;
        try {

            String data = apiBaseDelegate.httpPost(myappid, urlSendMessageFormat, param);
            if (StringUtils.isBlank(data)) {
                return false;
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> map = JsonUtils.toObject(data, Map.class);
            Integer retcode = (Integer) map.get(Constants.ERRCODE);
            if (apiBaseDelegate.isSuccess(retcode)) {
                return true;
            }
            logger.error("[sendMessage] error url:{} param:{} retcode:{} return:{}", url, param, retcode, data);
        } catch (Exception e) {
            logger.error("[sendMessage] error. url:{} param:{}", url, param, e);
        }
        return false;
    }

    private String listToString(List<String> list) {
        return Utils.toString(list, "|");
    }

    /**
     * 发送文本消息
     *
     * @param myappid
     * @param touser   成员ID列表，跟totag至少有一个不为空
     * @param totag    标签ID列表
     * @param agentid  企业应用的id
     * @param content  消息内容，最长不超过2048个字节，注意：主页型应用推送的文本消息在微信端最多只显示20个字（包含中英文）
     * @return 是否成功
     */
    public boolean sendText(String myappid, List<String> touser, List<String> totag, Integer agentid, String content) {
        if (agentid == null) {
            return false;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("touser", listToString(touser));
        param.put("toparty", StringUtils.EMPTY);
        param.put("totag", listToString(totag));
        param.put("msgtype", "text");
        param.put("agentid", agentid);
        param.put("text", Collections.singletonMap("content", content));
        param.put("safe", 0);
        return sendMessage(myappid, param);
    }

    /**
     * 发送news消息
     *
     * @param myappid
     * @param touser      发送的用户
     * @param totag       发送的用户标签
     * @param agentid     应用id
     * @param title       标题，不超过128个字节，超过会自动截断，可空
     * @param description 描述，不超过512个字节，超过会自动截断，可空
     * @param url         点击后跳转的链接，可空
     * @param picurl      图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片
     * @return 是否成功
     */
    public boolean sendNews(String myappid, List<String> touser, List<String> totag, Integer agentid, String title, String description, String url, String picurl) {
        if (agentid == null) {
            return false;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("touser", listToString(touser));
        param.put("toparty", StringUtils.EMPTY);
        param.put("totag", listToString(totag));
        param.put("msgtype", "news");
        param.put("agentid", agentid);
        param.put("safe", 0);

        Map<String, Object> articleMap = new HashMap<>();
        articleMap.put("title", title);
        articleMap.put("description", description);
        articleMap.put("url", url);
        articleMap.put("picurl", picurl);
        param.put("news", Collections.singletonMap("articles", Collections.singletonList(articleMap)));

        return sendMessage(myappid, param);
    }

    /**
     * 发送mpnews消息，
     * mpnews消息与news消息类似，不同的是图文消息内容存储在微信后台，并且支持保密选项。每个应用每天最多可以发送100次。
     *
     * @param myappid
     * @param touser       发送的用户
     * @param totag        发送的用户标签
     * @param agentid      应用id
     * @param author       作者，不超过64个字节，可空
     * @param title        标题
     * @param thumbMediaId 图文消息缩略图的media_id
     * @param content      图文消息的内容，支持html标签，不超过666 K个字节
     * @return 是否成功
     */
    public boolean sendMpnews(String myappid, List<String> touser, List<String> totag, Integer agentid, String author, String title, String thumbMediaId, String content, String digest) {
        if (agentid == null) {
            return false;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("touser", listToString(touser));
        param.put("toparty", StringUtils.EMPTY);
        param.put("totag", listToString(totag));
        param.put("msgtype", "mpnews");
        param.put("agentid", agentid);
        param.put("safe", 0);

        Map<String, Object> articleMap = new HashMap<>();
        articleMap.put("title", title);
        articleMap.put("digest", digest);
        articleMap.put("thumb_media_id", thumbMediaId);
        articleMap.put("author", author);
        articleMap.put("content", content);
        articleMap.put("show_cover_pic", "1");
        param.put("mpnews", Collections.singletonMap("articles", Collections.singletonList(articleMap)));

        return sendMessage(myappid, param);
    }
}
