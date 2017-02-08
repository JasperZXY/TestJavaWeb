package zxy.weixin.qyh.support;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zxy.common.utils.HttpUtils;
import zxy.common.utils.JsonUtils;
import zxy.common.utils.MyThreadFactory;
import zxy.commons.LockDelegate;
import zxy.weixin.WeixinException;
import zxy.weixin.qyh.utils.WeixinReturnCode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 基本的微信调用方法，获取access_token、发送请求
 */
@Component
public class ApiBaseDelegate {
    private static final Logger logger = LoggerFactory.getLogger(ApiBaseDelegate.class);

    // TODO 这个参数后续改为可配置，在有多个微信应用的情况，启动会比较慢，启动一个线程来初始化，让程序能快速启动完成
    public boolean initOnNewThread = true;
    public static final String ERRCODE = "errcode";
    public static final String KEY_ACCESS_TOKEN = "access_token";

    public static final String urlGettokenFormat = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
    
    private volatile ConcurrentMap<String, String> accessTokenMap = new ConcurrentHashMap<>();  // key为myappid
    /**
     * 这里设置刷新的最小间隔时间为2分钟
     */
    private static final long MIN_INTERVAL_REFRESH = 2 * 60 * 1000;
    private volatile ConcurrentMap<String, Long> lastTimeGetAccessTokenMap = new ConcurrentHashMap<>();
    private LockDelegate<String> accessTokenLockDelegate = new LockDelegate<>();

    private ExecutorService accessTokenThreadPool = Executors.newSingleThreadExecutor(new MyThreadFactory("AccessToken"));

    @Autowired
    private IAppConfig appConfig;

    @PostConstruct
    public void init() {
        if (initOnNewThread) {
            accessTokenThreadPool.execute(() -> doInitData());
        }
        else {
            doInitData();
        }
    }

    @PreDestroy
    public void destroy() {
        logger.info("preDestroy isShutdown:{}", accessTokenThreadPool.isShutdown());
        accessTokenThreadPool.shutdown();
    }

    public void doInitData() {
        appConfig.listAllMyappid().forEach(this::initAccessToken);
        logger.info("initAccessToken accessTokenMap:" + accessTokenMap);
    }

    /**
     * <a href="http://qydev.weixin.qq.com/wiki/index.php?title=AccessToken">AccessToken</a>
     * AccessToken是企业号的全局唯一票据，调用接口时需携带AccessToken。
     * AccessToken需要用CorpID和Secret来换取，正常情况下AccessToken有效期为7200秒，有效期内重复获取返回相同结果，并自动续期。
     * 由于获取access_token的api调用次数非常有限，建议应用存储与更新access_token，频繁刷新access_token会导致api调用受限，影响自身业务。
     *
     * @param myappid
     */
    public void initAccessToken(String myappid) {
        String corpId = appConfig.getCropId(myappid);
        if (StringUtils.isBlank(corpId)) {
            throw new WeixinException("[initAccessToken] corpId is blank. myappid:" + myappid);
        }

        String secret = appConfig.getSecret(myappid);
        if (StringUtils.isBlank(secret)) {
            throw new WeixinException("[initAccessToken] secret is blank. myappid:" + myappid);
        }

        String data = httpGet(myappid, String.format(urlGettokenFormat, corpId, secret));
        @SuppressWarnings("unchecked")
        Map<String, String> map = JsonUtils.toObject(data, Map.class);
        String token = map.get(KEY_ACCESS_TOKEN);
        if (StringUtils.isBlank(token)) {
            logger.error("[initAccessToken] error. return access_token is null. return data:" + data);
            throw new WeixinException("[initAccessToken] error. return access_token is null. return data:" + data);
        } else {
            accessTokenMap.put(myappid, token);
            logger.info("[getAccessTokenTask] success, accessToken:" + token);
        }
    }

    public final boolean isSuccess(Integer retcode) {
        return retcode == null || WeixinReturnCode.SUCCESS.equals(retcode);
    }

    /**
     * access_token是否合法，若不合法，将重新从微信那里请求获取
     * @param myappid
     * @param retCode
     * @return
     */
    private boolean accessTokenCheckAndReset(String myappid, Integer retCode) {
        // 只有这些情况下不需要更新AccessToken，其他的都要
        if (retCode == null) {
            return true;
        }
        if (!WeixinReturnCode.OVERDUE_ACCESS_TOKEN.equals(retCode) && !WeixinReturnCode.INVALID_ACCESS_TOKEN.equals(retCode)) {
            return true;
        }

        String key = myappid;
        if (lastTimeGetAccessTokenMap.get(myappid) != null &&
                System.currentTimeMillis() - lastTimeGetAccessTokenMap.get(key) < MIN_INTERVAL_REFRESH) {
            return false;
        }

        synchronized (accessTokenLockDelegate.getLockObject(key)) {
            if (lastTimeGetAccessTokenMap.get(key) != null &&
                    System.currentTimeMillis() - lastTimeGetAccessTokenMap.get(key) < MIN_INTERVAL_REFRESH) {
                return false;
            }
            lastTimeGetAccessTokenMap.put(key, System.currentTimeMillis());
            try {
                initAccessToken(myappid);
            } catch (Exception e) {
                logger.error("[accessTokenCheckAndReset] error.", e);
                lastTimeGetAccessTokenMap.put(key, System.currentTimeMillis() - MIN_INTERVAL_REFRESH);
                throw e;
            }
        }
        return false;
    }

    private String httpGet(String myappid, String url) throws WeixinException {
        String retData;
        // 只在Http GET 返回结果不为空，且AccessToken过期了才会调第二次
        for (int i = 0; i < 2; i++) {
            try {
                retData = HttpUtils.httpGet(url);
            } catch (Exception e) {
                logger.error("[httpGet] Http GET error. i:{} url:{}", i, url, e);
                throw new WeixinException("Http GET error.", e);
            }
            logger.debug("[httpGet] url:{} return:{}", url, retData);

            if (StringUtils.isBlank(retData)) {
                logger.error("[httpGet] error. Http GET return null. i:{} url:{}", i, url);
                throw new WeixinException("Http GET return null.");
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> map = JsonUtils.toObject(retData, Map.class);
            if (accessTokenCheckAndReset(myappid, (Integer) map.get(ERRCODE))) {
                return retData;
            }

            url = rebuildUrlForInvalidAccesstoken(myappid, url);
            if (StringUtils.isBlank(url)) {
                break;
            }
        }
        logger.error("[httpGet] error. url:" + url);
        throw new WeixinException("httpGet error");
    }

    public String httpPost(String myappid, String url, Map<String, Object> param) {
        return httpPost(myappid, url, param, Collections.<String, String>emptyMap());
    }

    public String httpPost(String myappid, String url, Map<String, Object> param, Map<String, String> postHeader) {
        String retData;
        // 只在Http POST 返回结果不为空，且AccessToken过期了才会调第二次
        for (int i = 0; i < 2; i++) {
            try {
                retData = HttpUtils.httpPost(url, JsonUtils.toString(param), postHeader);
            } catch (Exception e) {
                logger.error("[httpPost] Http POST error. i:{} url:{}", i, url, e);
                return null;
            }
            logger.debug("[httpPost] url:{} param:{} return:{}", url, param, retData);

            if (StringUtils.isBlank(retData)) {
                logger.error("[httpPost] error. Http POST return null. i:{} url:{}", i, url);
                return null;
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> map = JsonUtils.toObject(retData, Map.class);
            if (accessTokenCheckAndReset(myappid, (Integer) map.get(ERRCODE))) {
                return retData;
            }

            url = rebuildUrlForInvalidAccesstoken(myappid, url);
            if (StringUtils.isBlank(url)) {
                break;
            }
        }

        logger.error("[httpPost] error. url:" + url);
        return null;
    }

    /**
     * 把url中access_token对应的值换掉
     *
     * @param myappid
     * @param url
     * @return
     */
    private String rebuildUrlForInvalidAccesstoken(String myappid, String url) {
        String[] urls = url.split(KEY_ACCESS_TOKEN + "=");
        if (urls == null || urls.length != 2) {
            logger.error("[rebuildUrlForInvalidAccesstoken] url error. url:" + url);
            return StringUtils.EMPTY;
        }
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(urls[0]).append(KEY_ACCESS_TOKEN).append("=").append(getAccessToken(myappid));
        int index = urls[1].indexOf("&");
        if (index >= 0) {
            urlBuilder.append(urls[1].substring(index));
        }
        return urlBuilder.toString();
    }

    public String getAccessToken(String myappid) {
        return accessTokenMap.get(myappid);
    }
}
