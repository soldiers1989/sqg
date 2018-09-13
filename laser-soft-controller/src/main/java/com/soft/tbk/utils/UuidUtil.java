package com.soft.tbk.utils;

import java.util.UUID;

/**
 * 
 * UUID随机字符串生成工具类
 * 
 * @author sheng.cao
 */
public class UuidUtil {

    public static String getUuid() {

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }
}
