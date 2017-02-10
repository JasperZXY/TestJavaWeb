package zxy.commons;

/**
 * 权限编码
 */
public interface PermissionCode {
    int PERMISSIONS = 1;

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
    int ROLE_ALLOCATE_RESOURCE = 2006;
    int ROLE_ASSIGN_USER_ROLE = 2007;

    // 用户相关
    int USER_ACCESS = 3001;
    int USER_ADD = 3002;
    int USER_UPDATE = 3003;
    int USER_DELETE = 3004;
    int USER_LOCK_UNLOCK = 3005;
    int USER_HELP_CHANGE_PASSWORD = 3006;
    int USER_HELP_CHANGE_EMAIL = 3007;

    // 日志相关
    int LOGINFO_ACCESS = 4001;

    // 微信相关
    int WEIXIN = 2;
    int WEIXIN_QYH_MANAGER = 5005;

}
