package zxy.weixin.qyh.support;

/**
 * agentId配置，myappid为自定义的id
 */
public interface IAgentIdConfig {

    /**
     * Agentid：Java
     * @return
     */
    Integer getJava(String myappid);

    /**
     * Agentid:Android
     * @return
     */
    Integer getAndroid(String myappid);

    Integer getPageapp(String myappid);
}
