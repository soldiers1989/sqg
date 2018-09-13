package com.soft.wechat.util;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.HttpHeaders;

public class TKURL {
    
    public String getRedirectUrl(String needRedirectUrl) throws Exception {
        URL serverUrl = new URL(needRedirectUrl);
        HttpURLConnection conn = (HttpURLConnection) serverUrl
                .openConnection();
        conn.setRequestMethod("GET");
        // 必须设置false，否则会自动redirect到Location的地址
        conn.setInstanceFollowRedirects(false);

        conn.addRequestProperty("Accept-Charset", "UTF-8;");
        conn.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
        conn.connect();

        /**
         * 递归找到最终的url(包含location)
         */
        if (conn.getHeaderField("location") == null)
            return needRedirectUrl;
        else
            return getRedirectUrl(conn.getHeaderField("location"));
    }
 
    public String getBaseUrl(String redirectUrl, String tu) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(redirectUrl).openConnection();
        conn.setInstanceFollowRedirects(false);
        /**
         * tu为包含location的最终url
         */
        /**
         * 设置请求行
         */
        conn.setRequestProperty(HttpHeaders.REFERER, tu);
        return getRedirectUrl(conn.getHeaderField("Location"));
 
    }
 
    public String unescape(String str) {
 
        try {
            return URLDecoder.decode(str, "GBK");
 
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
    }
 
    public String escape(String str) {
 
        try {
            // 将普通字符创转换成application/x-www-from-urlencoded字符串
            return URLEncoder.encode(str, "GBK");
 
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
    }
}

