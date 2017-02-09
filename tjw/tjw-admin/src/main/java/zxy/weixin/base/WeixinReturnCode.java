package zxy.weixin.base;

/**
 * 企业号、服务号、订阅号，绝大部分的返回码都是一致的。<br/>
 * <a href="http://qydev.weixin.qq.com/wiki/index.php?title=全局返回码说明">http://qydev.weixin.qq.com/wiki/index.php?title=全局返回码说明</a><br/>
 * <a href="http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html">http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html</a>
 *
 */
public class WeixinReturnCode {
	
	public static final Integer SUCCESS = 0;
	/** 系统繁忙 */
	public static final Integer SYSTEM_BUSY = -1;
	/** 无效的code */
	public static final Integer INVALID_CODE = 91011;
	/** 不合法的oauth_code */
	public static final Integer ILLEGAL_OAUTH_CODE = 40029;
	/** access_token过期 */
	public static final Integer OVERDUE_ACCESS_TOKEN = 42001;
	/** 不合法的access_token */
	public static final Integer INVALID_ACCESS_TOKEN = 40014;
	/** 添加用户时，userid已经存在 */
	public static final Integer USERID_EXISTED = 60102;

}
