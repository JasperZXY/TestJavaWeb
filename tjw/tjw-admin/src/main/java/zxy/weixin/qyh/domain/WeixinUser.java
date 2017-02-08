package zxy.weixin.qyh.domain;

/**
 * 微信用户信息
 */
public class WeixinUser extends WeixinResult {
	private String userid;
	private String name;
	private String mobile;
	/** 0表示未定义，1表示男性，2表示女性 */
	private int gender;
	private String avatar;
	/** 关注状态: 1=已关注，2=已禁用，4=未关注 */
	private String status;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
