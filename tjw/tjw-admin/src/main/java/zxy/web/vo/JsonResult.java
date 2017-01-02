package zxy.web.vo;

import zxy.constants.ResultCode;

public class JsonResult<T> {
    private int status;
    private String msg;
    private T result;

    public JsonResult() {
    }

    public JsonResult(int status, String msg, T result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> JsonResult<T> buildSuccess(T t) {
        return new JsonResult<>(ResultCode.Success.getCode(), ResultCode.Success.getDescription(), t);
    }

    public static <T> JsonResult<T> buildsystemError() {
        return new JsonResult<>(ResultCode.SystemError.getCode(), ResultCode.SystemError.getDescription(), null);
    }

    public static <T> JsonResult<T> buildErrorTip(String msg) {
        return new JsonResult<>(ResultCode.ErrorTip.getCode(), msg, null);
    }
}
