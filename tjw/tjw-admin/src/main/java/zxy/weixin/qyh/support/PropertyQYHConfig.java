package zxy.weixin.qyh.support;

public class PropertyQYHConfig implements IAppConfig, IAgentIdConfig, ICallbackConfig {
    private String cropid;
    private String qrcodeUrl;
    private Integer agentidJava;
    private Integer agentidAndroid;
    private String callbackToken;
    private String callbackEncodingAESKey;

    public void setCropid(String cropid) {
        this.cropid = cropid;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public void setAgentidJava(Integer agentidJava) {
        this.agentidJava = agentidJava;
    }

    public void setAgentidAndroid(Integer agentidAndroid) {
        this.agentidAndroid = agentidAndroid;
    }

    public void setCallbackToken(String callbackToken) {
        this.callbackToken = callbackToken;
    }

    public void setCallbackEncodingAESKey(String callbackEncodingAESKey) {
        this.callbackEncodingAESKey = callbackEncodingAESKey;
    }

    @Override
    public String getCropId(String myappid) {
        return cropid;
    }

    @Override
    public String getQRcodeUrl(String myappid) {
        return qrcodeUrl;
    }

    @Override
    public Integer getJava(String myappid) {
        return agentidJava;
    }

    @Override
    public Integer getAndroid(String myappid) {
        return agentidAndroid;
    }

    @Override
    public String getToken(String myappid) {
        return callbackToken;
    }

    @Override
    public String getEncodingAESKey(String myappid) {
        return callbackEncodingAESKey;
    }
}
