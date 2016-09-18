package com.jasper.demo.utils;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentUtil {
	/**
	 * 是否手机
	 * @param userAgent
	 * @return
     */
	public static boolean isMobile(UserAgent userAgent) {
		return userAgent != null && userAgent.getOperatingSystem().getDeviceType() == DeviceType.MOBILE;
	}

	// Mozilla/5.0 (Linux; Android 5.1.1; ATH-AL00 Build/HONORATH-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036824 Safari/537.36 MicroMessenger/6.3.25.861 NetType/WIFI Language/zh_CN

	/**
	 * 获取手机型号
	 * @param useragent
	 * @return
     */
	public static String getPhoneModel(String useragent) {
		int indexLeft = useragent.indexOf("(");
		int indexRight = useragent.indexOf(")");
		if (indexLeft > 10 && indexRight > 10) {
			String deviceInfo = useragent.substring(indexLeft, indexRight);
			String[] strings = deviceInfo.split(";");
			for (String str : strings) {
				str = str.trim();
				if (str.indexOf("Build") > 0) {
					int index = str.indexOf(" ");
					if (index > -1) {
						return str.substring(0, index);
					}
				}
			}
		}
		return null;
	}

}