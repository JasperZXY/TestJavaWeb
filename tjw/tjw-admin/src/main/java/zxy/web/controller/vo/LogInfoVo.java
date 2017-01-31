package zxy.web.controller.vo;

import zxy.entity.Loginfo;

public class LogInfoVo extends Loginfo {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
