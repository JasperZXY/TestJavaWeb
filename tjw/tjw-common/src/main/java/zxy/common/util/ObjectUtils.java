package zxy.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jasper (zhongxianyao)
 */
public class ObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static <T> T convert(Object fromValue, Class<T> toValueType) {
        if (fromValue == null) {
            return null;
        }

        try {
            return mapper.convertValue(fromValue, toValueType);
        } catch (Exception e) {
            logger.error("Convert object to target type error!", e);
        }
        return null;
    }
}
