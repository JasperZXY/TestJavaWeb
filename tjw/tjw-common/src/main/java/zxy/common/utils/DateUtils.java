package zxy.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /* 日期格式常量  */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_COMPACT_FORMAT = "yyyyMMdd";
    public static final String DATE_COMPACT_YM_FORMAT = "yyyyMM";
    public static final String DATE_TIME_COMPACT_FORMAT = "yyyyMMddHHmmss";

    /* 时间常量 */
    public static final int SECONDS_FOR_MINUTE = 60;
    public static final int SECONDS_FOR_DAY = 86400;

    /**
     * 当前时间UTC秒数
     *
     * @return
     */
    public static int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * n天前的秒数
     *
     * @param days
     * @return
     */
    public static int daysBeforeSeconds(int days) {
        return currentTimeSeconds() - SECONDS_FOR_DAY * days;
    }

    /**
     * 当前日期
     *
     * @return
     */
    public static String currentDateStr() {
        return dateToCompactString(new Date());
    }

    public static String currentDateTimeStr() {
        return dateToString(new Date(), DATE_TIME_FORMAT);
    }

    /**
     * 当前日期紧凑格式
     *
     * @return
     */
    public static String currentCompactDateStr() {
        return dateToCompactString(new Date());
    }

    /**
     * 当前日期时间紧凑格式
     *
     * @return
     */
    public static String compactDateTime() {
        return dateToString(new Date(), DATE_TIME_COMPACT_FORMAT);
    }

    /**
     * 日期对象转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE_TIME_FORMAT);
    }

    /**
     * 日期对象转换为字符串紧凑格式
     *
     * @param date
     * @return
     */
    public static String dateToCompactString(Date date) {
        return dateToString(date, DATE_COMPACT_FORMAT);
    }

    /**
     * 根据格式将日期对象转换为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("Format date to string error! date={}, format={}", date, format, e);
        }
        return null;
    }

    /**
     * 日期字符串转换为日期对象
     *
     * @param dateStr
     * @return
     */
    public static Date dateStrToObject(String dateStr) {
        return dateStrToObject(dateStr, DATE_FORMAT);
    }

    /**
     * 根据格式将日期字符串转换为日期对象
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date dateStrToObject(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Parse date str to object error! dateStr={}, format={}", dateStr, format, e);
        }
        return null;
    }

    /**
     * 当前剩余秒数
     *
     * @return
     */
    public static int remainSeconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        int elapsedSeconds = (int) ((System.currentTimeMillis() - calendar.getTimeInMillis()) / 1000);
        return SECONDS_FOR_DAY - elapsedSeconds;
    }

    /**
     * 指定时间至今渡过的秒数
     *
     * @param timeSeconds
     * @return
     */
    public static int elapseSeconds(int timeSeconds) {
        return currentTimeSeconds() - timeSeconds;
    }
}
