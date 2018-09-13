package com.soft.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.soft.wechat.model.TextMessage;
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
    public static String textMessageToXml(TextMessage textMessage) {

        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage).replace(" ", "");

    }
    
    public static void main(String[] args) {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent("asdasd");
        System.out.println(textMessageToXml(textMessage));
    }
}
