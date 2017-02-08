package zxy.weixin.qyh.support;

/**
 * 回调模式用的参数
 */
public interface ICallbackConfig {
    /**
     * token
     * @param myappid
     * @return
     */
    String getToken(String myappid);

    /**
     * EncodingAESKey
     * @param myappid
     * @return
     */
    String getEncodingAESKey(String myappid);
}
