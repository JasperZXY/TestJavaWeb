package zxy.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 结合参数“single.task”使用，如“-Dsingle.task=true”
 */
public class CommandSingleTask implements SingleTask {
    private static final Logger logger = LoggerFactory.getLogger(CommandSingleTask.class);
    public static final String SINGLE_TASK = "single.task";

    private boolean toRun = false;

    public void init() {
        String singleTask = System.getProperty(SINGLE_TASK);
        toRun = Boolean.TRUE.toString().equalsIgnoreCase(singleTask);
        logger.info("init toRun:" + toRun);
    }

    @Override
    public boolean toRun() {
        return toRun;
    }
}
