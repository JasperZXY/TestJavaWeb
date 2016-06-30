import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        System.out.println(logger.isDebugEnabled());
        logger.debug("this is debug log");
        logger.info("this is info log");
        logger.warn("this is warn log");
        try {
            int i=0;
            i = i / i;
            System.out.println(i);
        } catch (Exception e) {
            logger.error("this error log:", e);
        }
    }
}
