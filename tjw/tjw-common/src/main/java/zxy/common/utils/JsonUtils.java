package zxy.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // SerializationFeature for changing how JSON is written

        // to enable standard indentation ("pretty-printing"):
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static <T> T toObject(String content, Class<T> valueType) {
        if (StringUtils.isNotBlank(content)) {
            try {
                return mapper.readValue(content, valueType);
            }
            catch (Exception e) {
                logger.error("Convert json string to object error! json=" + content, e);
            }
        }
        return null;
    }

    public static String toString(Object obj) {
        if (obj != null) {
            try {
                return mapper.writeValueAsString(obj);
            }
            catch (Exception e) {
                logger.error("Convert object to json string error!", e);
            }
        }
        return "{}";
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        if (fromValue == null) {
            return null;
        }

        try {
            return mapper.convertValue(fromValue, toValueType);
        }
        catch (Exception e) {
            logger.error("Convert object to target type error!", e);
        }
        return null;
    }

}
