package zxy.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public abstract class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static final String ALL_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String ALL_NUMBER = "0123456789";
    public static final String ALL_CHAR = ALL_LETTER + ALL_NUMBER;
    public static final String SPLIT = ",";
    public static final String EMPTY_STRING = "";

    /**
     * 验证是否符合ID的要求
     * @param id
     * @return
     */
    public static final boolean validateId(Integer id) {
        return id != null && id > 0;
    }

    /**
     * 生成随机字符串
     * @param count
     * @return
     */
    public static String randomString(int count) {
        if (count <= 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i=0; i<count; i++) {
            builder.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return builder.toString();
    }

    public static void requestRedirect(HttpServletResponse response, String url) {
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            // ignore
        }
    }

    public static void requestForward(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            // ignore
        }
    }

    public static <T> String toString(Collection<T> collection) {
        return toString(collection, SPLIT);
    }

    public static <T> String toString(Collection<T> collection, String split) {
        if (collection == null || collection.isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : collection) {
            stringBuilder.append(t).append(split);
        }
        stringBuilder.replace(stringBuilder.length() - split.length(), stringBuilder.length(), EMPTY_STRING);
        return stringBuilder.toString();
    }

    public static List<Integer> stringToIntegerList(String str) {
        if (StringUtils.isBlank(str)) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        for (String s : str.split(SPLIT)) {
            if (StringUtils.isNoneBlank(s)) {
                list.add(NumberUtils.toInt(s));
            }
        }

        return list;
    }

    /**
     * 获取最后一条数据，若list为空，返回null
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLast(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }

}
