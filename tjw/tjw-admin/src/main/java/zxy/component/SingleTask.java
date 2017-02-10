package zxy.component;

/**
 * 考虑多台机器运行的情况。
 * 多台服务器一起部署时，只需要一台机器运行的task
 */
public interface SingleTask {
    /**
     * @return 是否允许允许
     */
    boolean toRun();
}
