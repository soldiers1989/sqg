package com.soft.tbk.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * byte[]转换成文件
 * 
 * @author shengwen.yang
 */

public class CreateFileUtils {

    private final static Logger logger = LoggerFactory.getLogger(CreateFileUtils.class);

    public static void createFile(byte[] bt, String filePath, String fileName) {

        FileOutputStream out = null;
        File filePathName = new File(filePath + File.separator + fileName);
        //判断文件夹是否存在，不存在则创建
        File isFilePath = new File(filePath);
        if (!isFilePath.isDirectory()) {
            isFilePath.mkdirs();
        }
        if (filePathName.exists()) {
            if (!filePathName.delete()) {
                
            }
        }
        try {
            if(filePathName.createNewFile()){
                out = new FileOutputStream(filePathName);
                out.write(bt, 0, bt.length);
                out.flush();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
    }
}
