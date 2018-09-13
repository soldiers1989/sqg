package com.soft.tbk.core.cache;

import java.util.Date;

public interface IRedisClientKValue<VType> {

    /**
     * 设置缓存
     * 
     * @param appkey
     * @param key
     * @param value
     */
    void set(String appkey, String key, VType value);

    /**
     * 设置缓存(有时效性)
     * 
     * @param appkey
     * @param key
     * @param value
     * @param timeout 有效时间
     */
    void set(String appkey, String key, VType value, long timeout);

    /**
     * 删除缓存
     * 
     * @param appkey
     * @param key
     */
    void delete(String appkey, String key);

    /**
     * 获取缓存
     * 
     * @param appkey
     * @param key
     * @param requireClass
     * @return
     */
    VType get(String appkey, String key, Class<? extends VType> requireClass);

    /**
     * 原子操作
     * 
     * @param appkey
     * @param key
     * @param delta
     * @return
     */
    Long increment(String appkey, String key, long delta);

    /**
     * 原子操作
     * 
     * @param appkey
     * @param key
     * @param delta
     * @return
     */
    Double increment(String appkey, String key, double delta);

    /**
     * 生存时间
     * 
     * @param appkey
     * @param key
     * @param timeout
     * @return
     */
    Boolean expire(String appkey, String key, final long timeout);

    /**
     * 生存时间
     * 
     * @param appkey
     * @param key
     * @param date
     * @return
     */
    Boolean expireAt(String appkey, String key, final Date date);

}
