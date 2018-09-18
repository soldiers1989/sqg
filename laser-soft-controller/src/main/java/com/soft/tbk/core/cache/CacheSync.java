package com.soft.tbk.core.cache;

/**
 * 缓存同步
 * 
 */
public interface CacheSync<T> {

    T invoke();

    /**
     * 获取泛型class
     * 
     * @return
     */
    Class<?> getGenericClass();
}
