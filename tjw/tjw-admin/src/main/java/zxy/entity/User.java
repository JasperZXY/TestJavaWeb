package zxy.entity;

import java.util.Date;

/**
 * 用户
 */
public class User {
    /**
     * ID
     */
    private Integer id;

    /**
     * 账号对应ID
     */
    private String accountId;

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
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