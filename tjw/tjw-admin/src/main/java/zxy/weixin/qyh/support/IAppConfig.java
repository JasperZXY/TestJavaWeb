package zxy.weixin.qyh.support;

/**
 * 应用配置，myappid为自定义的id
 */
public interface IAppConfig {
    /**
     * 获取CropId，企业号唯一标识
     * @param myappid
     * @return
     */
    String getCropId(String myappid);

    /**
     * 获取关注用二维码
     * @param myappid
     * @return
     */
    String getQRcodeUrl(String myappid);

}
