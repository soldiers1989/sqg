package com.soft.wechat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class WechatMessageUtil {

    /**
     * 将xml转化为Map集合
     * 
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        Document doc = null;
        try {
            ins = request.getInputStream();
            doc = reader.read(ins);

            map.put("sourceText", doc.asXML());
            ins.close();
        } catch (DocumentException | IOException e1) {
            e1.printStackTrace();
        }

        Element root = doc.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }

        return map;
    }

    /**
     * 文本消息转化为xml
     * 
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(Object textMessage) {

        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage).replace(" ", "");

    }

    public static String postFile(String url, String filePath) {

        File file = new File(filePath);
        if (!file.exists())
            return null;
        String result = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
            output.write("Content-Type: image/jpeg \r\n\r\n".getBytes());
            byte[] data = new byte[1024];
            int len = 0;
            FileInputStream input = new FileInputStream(file);
            while ((len = input.read(data)) > -1) {
                output.write(data, 0, len);
            }
            output.write(("\r\n--" + boundary + "\r\n\r\n").getBytes());
            output.flush();
            output.close();
            input.close();
            InputStream resp = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            while ((len = resp.read(data)) > -1)
                sb.append(new String(data, 0, len, "utf-8"));
            resp.close();
            result = sb.toString();
            System.out.println(result);
        } catch (ClientProtocolException e) {} catch (IOException e) {}
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {

        String token = "14_JIVD4v0H0a-n-NAgjblzO_rtwROZfDAfvGSsPTRI9xATYyaGkQ4LUr2QCMEszy4Z37gpqQZJ5HojthuBf04jwQ4qJMUP-WiLeQnhVrYi0KPTh3aeLHAWjjiOf2M_di69JRaiaW6K9ltcNAdDMUWjAAAHPR";
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token + "&type=image";
        postFile(url, "d:/test.jpg");
    }
}
