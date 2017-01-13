package zxy.common;

/**
 * JSON结果
 */
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
        return new JsonResult<>(Status.SUCCESS, Message.SUCCESS, t);
    }

    public static <T> JsonResult<T> buildsystemError() {
        return new JsonResult<>(Status.ERROR_TIP, Message.SYSTEM_ERROR, null);
    }

    public static <T> JsonResult<T> buildErrorTip(String msg) {
        return new JsonResult<>(Status.ERROR_TIP, msg, null);
    }

    /**
     * 结果状态码常量
     */
    public interface Status {
        int SUCCESS = 10000;
//        int SYSTEM_ERROR = 10001;
        int ERROR_TIP = 10002;
    }

    /**
     * 结果信息提示
     */
    public interface Message {
        String SUCCESS = "成功";
        String SYSTEM_ERROR = "系统错误";
        String ERROR_TIP = SYSTEM_ERROR;
    }
}
