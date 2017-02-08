package zxy.weixin;

public class WeixinException extends RuntimeException {
    private Integer code = -1;

    public Integer getCode() {
        return code;
    }

    public WeixinException() {
    }

    public WeixinException(Integer code) {
        super();
        this.code = code;
    }

    public WeixinException(String message) {
        super(message);
    }

    public WeixinException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeixinException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
