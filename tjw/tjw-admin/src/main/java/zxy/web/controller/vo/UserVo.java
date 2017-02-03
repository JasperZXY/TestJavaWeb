package zxy.web.controller.vo;

import zxy.entity.User;

public class UserVo extends User {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
