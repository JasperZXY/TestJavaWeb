package zxy.weixin.qyh.domain;

import zxy.weixin.qyh.utils.WeixinReturnCode;

/**
 * 微信返回数据的结果
 */
public class WeixinResult {
	private Integer errcode = WeixinReturnCode.SUCCESS;
	private String errmsg;
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
