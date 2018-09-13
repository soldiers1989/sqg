package com.soft.tbk.utils;

public class ApplicationContextUtil {
	public static final String SPRING = "SPRING";
	public static final String JAVA = "JAVA";

	/**
	 * 通过工厂类获取对应的服务
	 * 
	 * @param serviceName
	 *            服务类名
	 * @return 服务类
	 */
	public static Object getService(String serviceName) {
		return getService(serviceName, SPRING);
	}

	/**
	 * 通过工厂类获取对应的服务
	 * 
	 * @param serviceName
	 *            服务类名
	 * @param applicationType
	 *            工厂类型
	 * @return 服务类
	 */
	public static Object getService(String serviceName, String applicationType) {
		Object object = null;
		if (StringUtils.isBlank(applicationType)){
			applicationType = SPRING;
		}
		applicationType = applicationType.toUpperCase();
		if (SPRING.equals(applicationType)) {
			object = SpringApplicationContextUtil.getBean(serviceName);
			if (object == null) {
				try {
					Class<?> cls = Class.forName(serviceName);
					object = SpringApplicationContextUtil.getBean(cls);
				} catch (ClassNotFoundException e) {
				}
			}
			
		} else if (JAVA.equals(applicationType)) {
			try {
				object = Class.forName(serviceName).newInstance();
			} catch (Exception e) {
			}
		}
		return object;
	}
}
