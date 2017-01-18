package zxy.common;

/**
 * 结果状态码
 */
public enum ResultCode {
    SUCCESS(10000, "success", "成功"),
    FAIL(10001, "fail", "系统错误"),
    ACCOUNT_EXIST(20001, "account exist", "账号已存在"),
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
