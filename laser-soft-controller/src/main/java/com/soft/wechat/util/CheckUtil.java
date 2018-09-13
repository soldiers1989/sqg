package com.soft.wechat.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.mybatis.mapper.util.StringUtil;

public class CheckUtil {

    private static Logger logger = LoggerFactory.getLogger(CheckUtil.class);

    // 配置微信公众号时填写的Token
    public static boolean checkSignature(String signature, String timestamp, String nonce, String tenantCode) {

        String token = "tbk123456";//TODO
        if (StringUtil.isEmpty(token)) {
            logger.error("token cache is null");
            return false;
        }
        // 拼接字符串
        String[] arr = new String[] { token, timestamp, nonce };
        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // SHA1加密
        String tmp = DecriptUtil.SHA1(content.toString());
        return tmp.equals(signature);
        //		return true;
    }
}
