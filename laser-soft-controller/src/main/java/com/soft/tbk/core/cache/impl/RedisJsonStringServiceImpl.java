package com.soft.tbk.core.cache.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.constants.RedisConstants;
import com.soft.tbk.core.cache.CacheSync;
import com.soft.tbk.core.cache.IRedisClientKValue;
import com.soft.tbk.core.cache.IRedisJsonStringService;

/**
 * @author min.he
 * @date 2018年5月22日
 * 
 */
@Service
public class RedisJsonStringServiceImpl implements IRedisJsonStringService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRedisClientKValue<String> redisClientKValue;

    private String appkey = "tbk";

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getObject(String key, Class<T> clazz, String methodName, Object obj, Object[] args) {

        String value = null;
        try {
            value = redisClientKValue.get(appkey, key, String.class);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (value == null) {
            return (T) invokeMethod(key, methodName, obj, args);
        }

        if (clazz == String.class) {
            return (T) value;
        }
        return JSON.parseObject(value, clazz);
    }

    private Object invokeMethod(String key, String methodName, Object obj, Object[] args) {

        if (methodName == null || methodName.trim().length() == 0 || obj == null) {
            return null;
        }
        Object resultObject = null;
        Class<?>[] parameterTypes = null;
        if (args != null) {
            parameterTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }
        }
        try {
            resultObject = obj.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(obj, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                        | SecurityException e) {
            logger.error(e.getMessage(), e);
        }
        if (resultObject == null) {
            return null;
        }
        set(key, resultObject, RedisConstants.defaultTimeout);
        return resultObject;
    }

    @Override
    public void set(String key, Object value) {

        try {
            String jsonStr = null;
            if (value instanceof String) {
                jsonStr = (String) value;
            } else {
                jsonStr = JSON.toJSONString(value);
            }
            redisClientKValue.set(appkey, key, jsonStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void set(String key, Object value, long timeout) {

        try {
            String jsonStr = null;
            if (value instanceof String) {
                jsonStr = (String) value;
            } else {
                jsonStr = JSON.toJSONString(value);
            }
            redisClientKValue.set(appkey, key, jsonStr, timeout);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void delete(String key) {

        try {
            redisClientKValue.delete(appkey, key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getObject(String key, CacheSync<T> cacheSync, long timeout) {

        String value = null;
        try {
            value = redisClientKValue.get(appkey, key, String.class);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        T result = null;
        if (value == null) {
            if (cacheSync == null) {
                return null;
            }
            result = cacheSync.invoke();
            if (result != null) {
                set(key, result, timeout);
            }
        } else {
            if (String.class.equals(cacheSync.getGenericClass())) {
                return (T) value;
            }
            result = (T) JSON.parseObject(value, cacheSync.getGenericClass());
        }
        return result;
    }

    @Override
    public <T> T getObject(String key, CacheSync<T> cacheSync) {

        return getObject(key, cacheSync, RedisConstants.defaultTimeout);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> List<E> getList(String key, CacheSync<List<E>> cacheSync) {

        String value = null;
        try {
            value = redisClientKValue.get(appkey, key, String.class);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        List<E> result = null;
        if (value == null) {
            if (cacheSync == null) {
                return null;
            }
            result = cacheSync.invoke();
            if (result != null && !result.isEmpty()) {
                set(key, result, RedisConstants.defaultTimeout);
            }
        } else {
            result = (List<E>) JSON.parseArray(value, cacheSync.getGenericClass());
        }
        return result;
    }
}
