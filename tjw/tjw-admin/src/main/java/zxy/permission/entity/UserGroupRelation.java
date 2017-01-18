package zxy.permission.entity;

/**
 * 用户与分组的关系
 */
public class UserGroupRelation {
    private Integer id;

    /**
     * 用户对应的ID
     */
    private Integer userId;

    /**
     * 分组对应的ID
     */
    private Integer groupId;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}