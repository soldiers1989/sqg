package com.soft.tbk.utils;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static Map<String, Object> dispatcherObject;// 通过groovy脚本初始进来的对象

	// TODO 实现一个接口，可以远程刷新
	public static Map<String, Object> getDispatcherObject() {
		return null;
	}

	public static void setDispatcherObject(Map<String, Object> dispatcherObject) {
		SpringApplicationContextUtil.dispatcherObject = dispatcherObject;
	}

	public ApplicationContext getApplicationContext() {
		return SpringApplicationContextUtil.applicationContext;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringApplicationContextUtil.applicationContext = applicationContext;
	}

	public static void addDispatcher(String name, Object obj) {
		dispatcherObject.put(name, obj);
	}

	public static Object getDispatcher(String name) {
		if (null == getDispatcherObject()){
			return null;
		}
		return getDispatcherObject().get(name);
	}

	/**
	 * 通过工厂类获取对应的服务
	 * 
	 * @param actionName
	 *            服务类名
	 * @return 服务类
	 */
	public static Object getBean(String name) {
		Object object = null;
		try {
			object = SpringApplicationContextUtil.applicationContext
					.getBean(name);
		} catch (Exception e) {
		}
		return object;
	}

	/**
	 * 通过类型获取 songzhiqiang add by 2015/9/1
	 * 
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType) {
		T object = null;
		try {
			object = SpringApplicationContextUtil.applicationContext
					.getBean(requiredType);
		} catch (Exception e) {
		}
		return object;
	}

	/**
	 * 通过工厂类获取对应的服务
	 * 
	 * @param actionName
	 *            服务类名
	 * @return 服务类
	 */
	public static Object getBeanAndDispatcher(String name) {
		Object object = null;
		try {
			// 先获取脚本初始的
			object = getDispatcher(name);
			if (null == object) {
				object = SpringApplicationContextUtil.applicationContext
						.getBean(name);
			}
		} catch (Exception e) {
		}
		return object;
	}

	/**
	 * 获取指定class类型的服务类
	 * 
	 * @param name
	 *            名称
	 * @param requiredType
	 *            class对象
	 * @return 服务类
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBean(String name, Class requiredType) {
		Object object = null;
		try {
			object = SpringApplicationContextUtil.applicationContext.getBean(
					name, requiredType);
		} catch (NoSuchBeanDefinitionException e) {
		} catch (Exception e) {
		}
		return object;
	}

	/**
	 * 是否存在执行名称的服务类
	 * 
	 * @param name
	 *            名称
	 * @return true 存在 flase 不存在
	 */
	public static boolean containsBean(String name) {
		return SpringApplicationContextUtil.applicationContext
				.containsBean(name);
	}

	/**
	 * 获取注册class类型
	 * 
	 * @param name
	 * @return
	 */
	public static String[] getAliases(String name) {
		String[] s = null;
		try {
			s = SpringApplicationContextUtil.applicationContext
					.getAliases(name);
		} catch (NoSuchBeanDefinitionException e) {

		} catch (Exception e) {
		}
		return s;
	}
}
