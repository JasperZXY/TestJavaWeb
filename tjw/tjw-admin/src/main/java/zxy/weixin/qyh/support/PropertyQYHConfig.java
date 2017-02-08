package zxy.weixin.qyh.support;

import java.util.Collections;
import java.util.Set;

public class PropertyQYHConfig implements IAppConfig, IAgentIdConfig, ICallbackConfig {
    private String myappid;
    private String cropid;
    private String secret;
    private String qrcodeUrl;
    private Integer agentidJava;
    private Integer agentidAndroid;
    private String callbackToken;
    private String callbackEncodingAESKey;

    public void setMyappid(String myappid) {
        this.myappid = myappid;
    }

    public void setCropid(String cropid) {
        this.cropid = cropid;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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
    public Set<String> listAllMyappid() {
        return Collections.singleton(myappid);
    }

    @Override
    public boolean checkMyappid(String myappid) {
        return this.myappid.equals(myappid);
    }

    @Override
    public String getCropId(String myappid) {
        return cropid;
    }

    @Override
    public String getSecret(String myappid) {
        return secret;
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
