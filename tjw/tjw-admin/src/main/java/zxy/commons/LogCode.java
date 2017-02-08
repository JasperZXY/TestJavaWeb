package zxy.commons;

/**
 * 操作日志的编码
 */
public interface LogCode {
    String ACCOUNT_PRE = "account_";
    String USER_PRE = "user_";
    String ROLE_PRE = "role_";

    String ROLE_ADD = ROLE_PRE + "add";
    String ROLE_UPDATE = ROLE_PRE + "update";
    String ROLE_LOCK = ROLE_PRE + "lock";
    String ROLE_UNLOCK = ROLE_PRE + "unlock";
    String ROLE_ALLOCATE_RESOURCE = ROLE_PRE + "allocate_resource";
    String ROLE_ASSIGN_USER_ROLE = ROLE_PRE + "assign_for_user";

    String USER_ADD = USER_PRE + "add";
    String USER_UPDATE = USER_PRE + "update";
    String USER_DELETE = USER_PRE + "delte";

    String ACCOUNT_LOGIN = ACCOUNT_PRE + "login";
    String ACCOUNT_LOGOUT = ACCOUNT_PRE + "logout";
    String ACCOUNT_LOCK = ACCOUNT_PRE + "lock";
    String ACCOUNT_UNLOCK = ACCOUNT_PRE + "unlock";
    String ACCOUNT_HELP_CHANGE_PASSWORD = ACCOUNT_PRE + "help_change_password";
    String ACCOUNT_HELP_CHANGE_EMAIL = ACCOUNT_PRE + "help_change_email";
    String ACCOUNT_RESET_PASSWORD = ACCOUNT_PRE + "reset_password";

}
