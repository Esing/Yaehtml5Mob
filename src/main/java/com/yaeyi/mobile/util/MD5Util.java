package com.yaeyi.mobile.util;

import java.security.MessageDigest;

public class MD5Util {

	/**
	 * 将字符串加密为MD5的结果
	 * @param source 源字符串
	 * @return 加密后的字符串
	 */
	public static String MD5(String source) {
		try {
			// 对密码进行ＭD5加密处理
			byte byteEncrypt[] = null;
			byteEncrypt = source.getBytes("UTF8");
			MessageDigest mdInstance = MessageDigest.getInstance("MD5");
			mdInstance.update(byteEncrypt);
			byte resultEncrypt[] = mdInstance.digest();

			// MD5转换编码
			StringBuffer strPasswordBuffer = new StringBuffer();
			for (int i = 0; i < resultEncrypt.length; i++) {
				strPasswordBuffer.append(Integer.toHexString(0xff & resultEncrypt[i]));
			}

			return strPasswordBuffer.toString();
		} catch (Exception e) {
		}

		return null;
	}
}
