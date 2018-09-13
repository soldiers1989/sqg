package com.soft.tbk.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 读写文件工具类
 * 
 * @author junxian.zhao
 *
 */
public class FileUtil {

    /**
     * 创建单个文件
     * 
     * @param destFileName 目标文件名
     * @return 创建成功，返回true，否则返回false
     */
    public static boolean createFile(String destFileName) {

        File file = new File(destFileName);
        if (file.exists()) {
            return true;
        }
        if (destFileName.endsWith(File.separator)) {
            return false;
        }
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            if (!file.getParentFile().mkdirs()) {
                return false;
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 创建目录
     * 
     * @param destDirName 目标目录名
     * @return 目录创建成功放回true，否则返回false
     */
    public static boolean createDir(String destDirName) {

        File dir = new File(destDirName);
        if (dir.exists()) {
            return true;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目标目录
        if (dir.mkdir()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建临时文件
     * 
     * @param prefix 临时文件名的前缀
     * @param suffix 临时文件名的后缀
     * @param dirName 临时文件所在的目录，如果输入null，则在用户的文档目录下创建临时文件
     * @return 临时文件创建成功返回true，否则返回false
     */
    public static String createTempFile(String prefix, String suffix, String dirName) {

        File tempFile = null;
        if (dirName == null) {
            try {
                // 在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                // 返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                return null;
            }
        } else {
            File dir = new File(dirName);
            // 如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (!FileUtil.createDir(dirName)) {
                    return null;
                }
            }
            try {
                // 在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                return null;
            }
        }
    }

    /**
     * 写文件
     * 
     * @param content 文件流
     * @param filePath 文件全路径
     */
    public static void writeFile(byte[] content, String filePath) {

        FileUtil.createFile(filePath);
        OutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
            os.write(content);// 输出文件
        } catch (IOException e) {

        } finally {
            //如果stream被实例化
            if (os != null) {
                try {
                    //关闭字节流
                    os.close();
                } catch (IOException e) {}
            }
        }
    }

    public static void main(String[] args) {

        // 创建目录
        String dirName = "d:/temp/aaa";
        //        FileUtil.createDir(dirName);
        String uuid = "11";
        // 创建文件
        String fileName = dirName + "/temp2/" + uuid + ".txt";
        FileUtil.createFile(fileName);
    }

}
