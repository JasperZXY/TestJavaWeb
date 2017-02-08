package zxy.weixin.qyh.utils;

/**
 * 微信返回码 http://qydev.weixin.qq.com/wiki/index.php?title=全局返回码说明
 *
 */
public class WeixinReturnCode {
	/**
	 * 微信发送请求失败但没有返回“返回码”
	 */
	public static final int NO_CODE = -10000;
	
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
