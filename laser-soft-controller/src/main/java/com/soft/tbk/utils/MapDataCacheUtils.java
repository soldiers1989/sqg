package com.soft.tbk.utils;

import java.util.HashMap;
import java.util.Map;

public class MapDataCacheUtils {
	private static Map<String, String> cacheMap;

	public static String getCache(String key, String defaultValue) {
		String obj = getCacheMap().get(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, String value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}

	public static Map<String, String> getCacheMap() {
		if (cacheMap == null) {
			cacheMap = new HashMap<String, String>();
		}
		return cacheMap;
	}
}
