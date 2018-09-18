package com.soft.tbk.core.cache.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.core.cache.IRedisClientKValue;

@Component
public class RedisClientKValueImpl<V> implements IRedisClientKValue<V> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    protected Class<V> vClass;

    public RedisClientKValueImpl() {
    }

    @Override
    public void set(String appkey, String key, V value) {

        redisTemplate.opsForValue().set(combineKey(appkey, key), this.serialize(value));
    }

    @Override
    public void set(String appkey, String key, V value, long timeout) {

        redisTemplate.opsForValue().set(combineKey(appkey, key), this.serialize(value), timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(String appkey, String key) {

        redisTemplate.delete(combineKey(appkey, key));
    }

    @Override
    public V get(String appkey, String key, Class<? extends V> requireClass) {

        return this.deserialize(redisTemplate.opsForValue().get(combineKey(appkey, key)), requireClass);
    }

    @Override
    public Long increment(String appkey, String key, long delta) {

        return redisTemplate.opsForValue().increment(combineKey(appkey, key), delta);
    }

    @Override
    public Double increment(String appkey, String key, double delta) {

        return redisTemplate.opsForValue().increment(combineKey(appkey, key), delta);
    }

    @Override
    public Boolean expire(String appkey, String key, final long timeout) {

        return redisTemplate.expire(combineKey(appkey, key), timeout, TimeUnit.SECONDS);
    }

    @Override
    public Boolean expireAt(String appkey, String key, final Date date) {

        return redisTemplate.expireAt(combineKey(appkey, key), date);
    }

    private String combineKey(String appkey, String key) {

        return String.format("%s-%s", appkey, key);
    }

    private String serialize(V value) {

        return JSON.toJSONString(value);
    }

    private V deserialize(String value, Class<? extends V> requireClass) {

        return JSON.parseObject(value, requireClass);
    }

}
