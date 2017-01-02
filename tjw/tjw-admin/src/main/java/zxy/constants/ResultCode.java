package zxy.constants;

/**
 * 结果状态码
 */
public enum ResultCode {
    Success(10000, "success", "成功"),
    SystemError(10001, "system error", "系统错误"),
    ErrorTip(10002, "error tip", "未知错误"),   // 这里应该有自己的错误提示
    ;

    int code;
    String description;
    String descriptionCh;

    ResultCode(int code, String description, String descriptionCh) {
        this.code = code;
        this.description = description;
        this.descriptionCh = descriptionCh;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionCh() {
        return descriptionCh;
    }
}
