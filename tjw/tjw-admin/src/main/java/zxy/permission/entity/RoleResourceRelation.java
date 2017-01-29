package zxy.permission.entity;

import java.util.Date;

/**
 * 角色与资源的关系
 */
public class RoleResourceRelation {
    private Integer id;

    /**
     * 角色对应ID
     */
    private Integer roleId;

    /**
     * 资源对应ID集合
     */
    private String resourceIds;

    /**
     * 创建人id
     */
    private Integer createUid;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}