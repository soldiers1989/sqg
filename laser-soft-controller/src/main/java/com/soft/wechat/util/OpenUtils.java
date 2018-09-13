package com.soft.wechat.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 系统工具类。
 * 
 */
public abstract class OpenUtils {

	private OpenUtils() {
	}

	/**
	 * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes
	 *            文件字节流
	 * @return JPG, GIF, PNG or null
	 */
	public static String getFileSuffix(byte[] bytes) {
		if (bytes == null || bytes.length < 10) {
			return null;
		}

		if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
			return "GIF";
		} else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
			return "PNG";
		} else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
			return "JPG";
		} else if (bytes[0] == 'B' && bytes[1] == 'M') {
			return "BMP";
		} else {
			return null;
		}
	}

	/**
	 * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes
	 *            文件字节流
	 * @return 媒体类型(MEME-TYPE)
	 */
	public static String getMimeType(byte[] bytes) {
		String suffix = getFileSuffix(bytes);
		String mimeType;

		if ("JPG".equals(suffix)) {
			mimeType = "image/jpeg";
		} else if ("GIF".equals(suffix)) {
			mimeType = "image/gif";
		} else if ("PNG".equals(suffix)) {
			mimeType = "image/png";
		} else if ("BMP".equals(suffix)) {
			mimeType = "image/bmp";
		} else {
			mimeType = "application/octet-stream";
		}

		return mimeType;
	}

	@SuppressWarnings("rawtypes")
	public static Class classForName(String classPath) throws ClassNotFoundException {
		if (StringUtils.isBlank(classPath)){
			return null;
		}
		if ("boolean".equals(classPath)) {
			return boolean.class;
		} else if ("byte".equals(classPath)) {
			return byte.class;
		} else if ("char".equals(classPath)) {
			return char.class;
		} else if ("short".equals(classPath)) {
			return short.class;
		} else if ("int".equals(classPath)) {
			return int.class;
		} else if ("long".equals(classPath)) {
			return long.class;
		} else if ("float".equals(classPath)) {
			return float.class;
		} else if ("double".equals(classPath)) {
			return double.class;
		} else if ("[Ljava.lang.String".equals(classPath)) {
			return String[].class;
		} else if ("[Ljava.lang.Byte".equals(classPath)) {
			return Byte[].class;
		} else if ("[Ljava.lang.Double".equals(classPath)) {
			return Double[].class;
		} else if ("[Ljava.lang.Long".equals(classPath)) {
			return Long[].class;
		} else if ("[Ljava.lang.Short".equals(classPath)) {
			return Short[].class;
		} else if ("[Ljava.lang.Date".equals(classPath)) {
			return Date[].class;
		} else if ("[Ljava.lang.Integer".equals(classPath)) {
			return Integer[].class;
		} else if ("[Ljava.lang.Boolean".equals(classPath)) {
			return Boolean[].class;
		} else if ("[Ljava.lang.Number".equals(classPath)) {
			return Number[].class;
		} else if ("[Ljava.lang.Float".equals(classPath)) {
			return Float[].class;
		} else if ("[Ljava.lang.Character".equals(classPath)) {
			return Character[].class;
		} else {
			return Class.forName(classPath);
		}
	}

}
