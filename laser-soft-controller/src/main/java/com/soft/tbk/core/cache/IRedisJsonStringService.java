package com.soft.tbk.core.cache;

import java.util.List;

/**
 * 
 * @author junxian.zhao
 *
 */
public interface IRedisJsonStringService {

    /**
     * 获取key对应的value
     * 
     * @param key
     * @param classz
     * @param methodName 获取不到缓存时执行的底层方法
     * @param obj 从中调用底层方法的对象
     * @param args 用于方法调用的参数
     * @return
     */
    <T> T getObject(String key, Class<T> classz, String methodName, Object obj, Object[] args);

    /**
     * 设置key对应的value
     * 
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 设置key对应的value和超时时间
     * 
     * @param key
     * @param value
     * @param timeout 超时时间(单位毫秒)
     * @throws Exception
     */
    void set(String key, Object value, long timeout);

    /**
     * 删除key
     * 
     * @param key
     * @throws Exception
     */
    void delete(String key);

    /**
     * 实现string,list和java bean的， map的未实现
     * 
     * @param key
     * @param cacheSync
     * @return
     */
    <T> T getObject(String key, CacheSync<T> cacheSync);

    /**
     * 实现string,list和java bean的， map的未实现
     * 
     * @param key
     * @param cacheSync
     * @param cacheSync
     * @return
     */
    <T> T getObject(String key, CacheSync<T> cacheSync, long timeout);

    /**
     * 返回list
     * 
     * @param key
     * @param cacheSync
     * @return
     */
    <E> List<E> getList(String key, CacheSync<List<E>> cacheSync);

}
