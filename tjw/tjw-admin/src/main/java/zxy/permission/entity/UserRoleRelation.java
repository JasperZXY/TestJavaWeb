package zxy.permission.entity;

/**
 * 用户与角色的关系
 */
public class UserRoleRelation {
    private Integer id;

    /**
     * 用户对应的ID
     */
    private Integer userId;

    /**
     * 角色对应的ID
     */
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}