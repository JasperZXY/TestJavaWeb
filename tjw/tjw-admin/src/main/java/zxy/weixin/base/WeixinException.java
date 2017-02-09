package zxy.weixin.base;

public class WeixinException extends RuntimeException {
    public static final Integer WEIXIN_ERROR = -10000;
    public static final Integer PARAM_ERROR = -10001;
    public static final Integer NO_INIT_CONFIG = -10002;
    public static final Integer HTTP_ERROR = -10003;

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

    public WeixinException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public WeixinException(Integer code, String message, Throwable cause) {
        super(message, cause);
    }

}
