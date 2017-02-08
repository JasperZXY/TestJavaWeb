package zxy.commons;

/**
 * 结果状态码
 */
public enum ResultCode {
    SUCCESS(10000, "success", "成功"),
    FAIL(10001, "fail", "系统错误"),
    NO_LOGIN(10002, "no login", "没有登录"),
    NO_PERMISSION(10003, "no permission", "没有操作权限"),
    DATA_NO_FOUND(10004, "data no found", "没找到相应的数据"),
    ACCOUNT_EXIST(20001, "account exist", "账号已存在"),
    EMAIL_EXIST(20002, "email exist", "邮箱已存在"),
    RESET_PASSWORD_ACCOUNT_EMAIL_NOT_MATCH(20003, "reset_password_account_email_not_match", "账号跟邮箱不匹配"),
    RESET_PASSWORD_CODE_ERROR(20004, "reset_password_code_error", "验证码错误"),
    ;

    private int code;
    private String desc;
    private String cndesc;

    ResultCode(int code, String desc, String cndesc) {
        this.code = code;
        this.desc = desc;
        this.cndesc = cndesc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getCndesc() {
        return cndesc;
    }
}
