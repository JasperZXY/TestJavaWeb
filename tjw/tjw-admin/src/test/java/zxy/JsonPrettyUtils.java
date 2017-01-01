package zxy;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonPrettyUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonPrettyUtils.class);
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		// SerializationFeature for changing how JSON is written

		// to enable standard indentation ("pretty-printing"):
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
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
		try {
			return mapper.readValue(content, valueType);
		} catch (Exception e) {
			logger.error("Convert json string to object error! json=" + content, e);
		}
		return null;
	}
	
	public static String toString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("Convert object to json string error!", e);
		}
		return "{}";
	}
	
	public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
		try {
			return mapper.convertValue(fromValue, toValueType);
		} catch (Exception e) {
			logger.error("Convert object to target type error!", e);
		}
		return null;
	}
	
}