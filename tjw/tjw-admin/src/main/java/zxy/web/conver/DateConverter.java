package zxy.web.conver;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING);
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_STRING);

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }

        if (DATE_FORMAT_STRING.length() == source.length()) {
            try {
                return DATE_FORMAT.parse(source);
            } catch (ParseException e) {
                logger.error("convert source:{} error.", source, e);
            }
        }
        if (DATE_TIME_FORMAT_STRING.length() == source.length()) {
            try {
                return DATE_TIME_FORMAT.parse(source);
            } catch (ParseException e) {
                logger.error("convert source:{} error.", source, e);
            }
        }
        return null;
    }
}