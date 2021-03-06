package zxy.commons;

/**
 * JSON结果
 */
public class JsonResult<T> {

    public static final JsonResult SYSTEM_ERROR_RESULT =
        new JsonResult(ResultCode.FAIL.getCode(), ResultCode.FAIL.getCndesc(), null);

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
        return new JsonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getCndesc(), t);
    }

    public static <T> JsonResult<T> buildNoLogin() {
        return new JsonResult<>(ResultCode.NO_LOGIN.getCode(), ResultCode.NO_LOGIN.getCndesc(), null);
    }

    public static <T> JsonResult<T> buildFail(String msg) {
        return new JsonResult<>(ResultCode.FAIL.getCode(), msg, null);
    }

    public static <T> JsonResult<T> buildFail(ResultCode resultCode) {
        return new JsonResult<>(ResultCode.FAIL.getCode(), resultCode.getCndesc(), null);
    }

    public static <T> JsonResult<T> build(ResultCode resultCode) {
        if (resultCode == null || ResultCode.SUCCESS == resultCode) {
            return buildSuccess(null);
        }
        return buildFail(resultCode);
    }

}
