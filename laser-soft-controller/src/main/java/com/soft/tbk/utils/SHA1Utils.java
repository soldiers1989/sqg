package com.soft.tbk.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA1Utils {

    // 日志
    private static Logger logger = LoggerFactory.getLogger(SHA1Utils.class);

    public static String getSHA(String spara) {

        byte[] sRtn = null;

        try {
            byte[] plainText = spara.getBytes("UTF-8");
            // 使用getInstance("算法")来获得消息摘要,这里使用SHA-1的160位算法  
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            // 开始使用算法  
            messageDigest.update(plainText);
            // 输出算法运算结果  
            sRtn = Base64.getEncoder().encode(messageDigest.digest());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (sRtn == null) {
            logger.info("加签算法输出为null");
            return null;
        }
        try {
            return new String(sRtn, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getSHA_New(String spara) {

        if (null == spara || 0 == spara.length()) {
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(spara.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            logger.info("加签算法输出为null");
            return null;
        } catch (UnsupportedEncodingException e) {
            logger.info("加签算法输出为null");
            return null;
        }
    }

}
