package zxy.commons;

import org.apache.commons.lang3.StringUtils;

public class ServiceException extends RuntimeException {
    private ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public ResultCode getCode() {
        return resultCode;
    }

    @Override
    public String getMessage() {
        String superMessage = super.getMessage();
        if (StringUtils.isBlank(superMessage)) {
            return resultCode.getDesc();
        }
        return superMessage + " and " + resultCode.getDesc();
    }
}
