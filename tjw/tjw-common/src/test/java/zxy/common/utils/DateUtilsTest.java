package zxy.common.utils;

import org.junit.Test;

import java.util.Date;

/**
 * @author Jasper.Zhong
 */
public class DateUtilsTest {
    @Test
    public void dateToString() {
        System.out.println(DateUtils.dateToString(new Date()));
        System.out.println(DateUtils.dateToString(new Date(), DateUtils.DATE_COMPACT_FORMAT));
    }

    @Test
    public void dateStrToObject() {
        System.out.println(DateUtils.dateStrToObject("2016-12-30 20:01:02"));
        System.out.println(DateUtils.dateStrToObject("2016-12-30 20:01:02", DateUtils.DATE_TIME_FORMAT));
    }
}
