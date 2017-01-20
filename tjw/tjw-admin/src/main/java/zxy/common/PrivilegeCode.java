package zxy.common;

/**
 * 权限编码
 */
public interface PrivilegeCode {
    int PRIVILEGES = 1;

    // 资源相关
    int RESOURCE_ACCESS = 1001;
    int RESOURCE_ADD = 1002;
    int RESOURCE_UPDATE = 1003;
//    int RESOURCE_DELETE = 1004;
    int RESOURCE_LOCK_UNLOCK = 1005;

    // 角色相关
    int ROLE_ACCESS = 2001;
    int ROLE_ADD = 2002;
    int ROLE_UPDATE = 2003;
//    int ROLE_DELETE = 2004;
    int ROLE_LOCK_UNLOCK = 2005;

    // 用户相关
    int USER_ACCESS = 3001;
    int USER_ADD = 3002;
    int USER_UPDATE = 3003;
    int USER_DELETE = 3004;
    int USER_LOCK_UNLOCK = 3005;

}
