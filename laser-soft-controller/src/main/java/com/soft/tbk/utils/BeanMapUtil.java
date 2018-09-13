package com.soft.tbk.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

public class BeanMapUtil {

    /**
     * bean 转化为实体
     * 
     * @param bean
     * @return
     */
    public static HashMap<String, Object> beanToMap(Object bean) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == bean) {
            return map;
        }
        Class<?> clazz = bean.getClass();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {}
        if (beanInfo == null)
            return map;
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method method = descriptor.getReadMethod();
                Object result;
                try {
                    result = method.invoke(bean);
                    if (null != result) {
                        map.put(propertyName, result);
                    }
                } catch (Exception e) {

                }
            }
        }

        return map;
    }

    /**
     * bean 转化为实体
     * 
     * @param bean
     * @return
     */
    public static HashMap<String, String> beanToStringMap(Object bean) {

        HashMap<String, String> map = new HashMap<String, String>();
        //获取指定类的BeanInfo 对象  
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            //获取所有的属性描述器  
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String key = pd.getName();
                if (!"class".equalsIgnoreCase(key)) {
                    Method getter = pd.getReadMethod();
                    Object value = getter.invoke(bean);
                    if (value != null) {
                        String strValue;
                        if (value instanceof String) {
                            strValue = (String) value;
                        } else if (value instanceof Integer) {
                            strValue = ((Integer) value).toString();
                        } else if (value instanceof Long) {
                            strValue = ((Long) value).toString();
                        } else if (value instanceof Float) {
                            strValue = ((Float) value).toString();
                        } else if (value instanceof Double) {
                            strValue = ((Double) value).toString();
                        } else if (value instanceof Boolean) {
                            strValue = ((Boolean) value).toString();
                        } else if (value instanceof Date) {
                            strValue = DateUtil.getDateString(((Date) value), DateUtil.DATETIMESHOWFORMAT);
                        } else {
                            strValue = JSON.toJSONString(value);
                        }
                        map.put(key, strValue);
                    }
                }
            }
        } catch (IllegalAccessException e) {} catch (IllegalArgumentException e) {} catch (InvocationTargetException e) {} catch (IntrospectionException e) {}
        return map;
    }

    /**
     * map 转化为 bean
     * 
     * @param clazz
     * @param map
     * @return
     */
    public static Object mapToBean(Class<?> clazz, HashMap<String, Object> map) {

        Object object = null;
        try {
            object = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(object, args);
                }
            }

        } catch (Exception e) {

        }
        return object;
    }
}
