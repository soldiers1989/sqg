package com.soft.tbk.utils;

import java.util.Random;

/**
 * 随机码
 * 
 * @author junxian.zhao
 *
 */
public class RandomUtil {

    public static String getRandom(int length) {

        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
