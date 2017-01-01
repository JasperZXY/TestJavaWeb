package zxy.entity;

import java.util.Date;

/**
 * 用户（后续account跟password要分开）
 */
public class User {
    /**
     * ID
     */
    private Integer id;

    /**
     * 账号名，登录用
     */
    private String account;

    /**
     * 密码，登录用
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 状态（0可用，1删除）
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}