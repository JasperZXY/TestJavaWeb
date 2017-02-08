package zxy.commons;

/**
 * 实体状态
 */
public interface EntityStatus {
    /** 有效 */
    int VALID = 0;
    /** 删除 */
    int DELETE = 1;
    /** 禁用 */
    int FORBIDDEN = 2;
}
