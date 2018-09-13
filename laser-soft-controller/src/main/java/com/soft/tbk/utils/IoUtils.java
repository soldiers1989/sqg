package com.soft.tbk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoUtils {

    private final static Logger logger = LoggerFactory.getLogger(IoUtils.class);

    public static String getFileContext(InputStream inputStream) {

        StringBuilder result = new StringBuilder();
        try {
            /*
             * 先把返回值给res,再去判断res和-1的关系 -1是为了数据完整性检测
             */
            BufferedReader in2 = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            while ((str = in2.readLine()) != null) {
                // 从0读到当前位置
                result.append(str + "\n");
            }

        } catch (IOException e) {
            // 层级异常,必须是平级,不是父子关系
            logger.error(e.getMessage(), e);
        } finally {
            // IOException 本质是NullPointExcrption
            try {
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage(), e);
            }
        }
        return result.toString();
    }
}
