package zxy.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jasper.Zhong
 */
public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static final String HTTP_HEADER_XFF = "X-Forwarded-For";
    public static final String HTTP_HEADER_XRP = "X-Real-IP";

    public static String getRemoteIP(HttpServletRequest request) {

        if (request == null)
            return null;

        logger.debug("[getRemortIP] source remote ip. XFF: {}, Real: {}, remote: {}",
                request.getHeader(HTTP_HEADER_XFF), request.getHeader(HTTP_HEADER_XRP), request.getRemoteAddr());

        // 优先级1：从X-Forwarded-For中取，有被伪造的风险
        String xff = request.getHeader(HTTP_HEADER_XFF);
        if (StringUtils.isNotBlank(xff) && !"unknown".equalsIgnoreCase(xff)) {
            if (xff.indexOf(",") < 0) {
                return xff;
            }
            return StringUtils.substringBefore(xff, ",");
        }

        // 优先级2：从X-Real-IP中取，有多重代理时，这个地址可能不准确，但一般不会被伪造
        String ip = request.getHeader(HTTP_HEADER_XRP);
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        // 优先级3：直接获取RemoteAddr，有反向代理时这个地址会不准确
        return request.getRemoteAddr();
    }

    public static final boolean validateId(Integer id) {
        return id != null && id > 0;
    }
}