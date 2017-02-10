package zxy.weixin.qyh.support;

import java.util.Set;

/**
 * 应用配置，myappid为自定义的appid，本程序管理的应用
 */
public interface IAppConfig {
    /**
     * 加载所有的myappid
     *
     * @return
     */
    Set<String> listAllMyappid();

    /**
     * 检查myappid是否合法
     *
     * @param myappid
     * @return
     */
    boolean checkMyappid(String myappid);

    /**
     * 获取CropId，企业号唯一标识
     *
     * @param myappid
     * @return
     */
    String getCropId(String myappid);

    /**
     * 在设置页面，普通管理组那里新建一个管理组，即可得到Secret，记得需要添加上必要的权限
     *
     * @param myappid
     * @return
     */
    String getSecret(String myappid);

    /**
     * 获取关注用二维码
     *
     * @param myappid
     * @return
     */
    String getQRcodeUrl(String myappid);

}
