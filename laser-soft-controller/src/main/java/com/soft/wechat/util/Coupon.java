package com.soft.wechat.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class Coupon {

    public static final String tklparseURL = "http://api.chaozhi.hk/tb/tklParse";

    public static final String tklGenURL = "http://api.chaozhi.hk/tb/linkTkl";

    public static final String tbkHighURL = "http://open.jxb001.cn/openApi/high/api";
    
    public static final String unulandURL = "http://open.jxb001.cn/openApi/unuland/api";
    

    public static JSONObject getHighTBK(String itemId, String pid) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appkey", "1822410736221674");
        params.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        params.put("pid", pid);
        params.put("itemId", itemId);
        params.put("platform", "2");
        JSONObject object = new JSONObject();
        try {
            String result = WebUtils.doGet(tbkHighURL, params);
            if (StringUtils.isNotBlank(result)) {
                object = JSONObject.parseObject(result);
            }
            String errorCode = object.getString("errcode");
            if ("15".equals(errorCode)) {
                return null;
            }
            if (object != null) {
                object = object.getJSONObject("data");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return object;
    }
    public static JSONObject getunuland(String url) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appkey", "1822410736221674");
        params.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        params.put("url", url);
        JSONObject object = new JSONObject();
        try {
            String result = WebUtils.doGet(unulandURL, params);
            if (StringUtils.isNotBlank(result)) {
                object = JSONObject.parseObject(result);
            }
            String errorCode = object.getString("errcode");
            if ("15".equals(errorCode)) {
                return null;
            }
            if (object != null) {
                object = object.getJSONObject("data");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return object;
    }

    public static String getTKL(String title, String url, String image) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("link", url);
        params.put("image", image);
        params.put("text", title);
        params.put("type", "B");
        JSONObject object = new JSONObject();
        try {
            String result = WebUtils.doPost(tklGenURL, params);
            if (StringUtils.isNotBlank(result)) {
                object = JSONObject.parseObject(result);
            }
            if (object != null) {
                object = object.getJSONObject("data");
                return object.getString("tkl");
            }
        } catch (IOException e) {
            
        }
        return null;
    }
    
    
    public static JSONObject getTBKItemByToken(String tkl) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tkl", tkl);
        JSONObject object = new JSONObject();
        try {
            String result = WebUtils.doPost(tklparseURL, params);
            if (StringUtils.isNotBlank(result)) {
                object = JSONObject.parseObject(result);
            }
            if (object != null) {
                object = object.getJSONObject("data");
            }
        } catch (IOException e) {

        }
        return object;
    }

    public static String parseItemId(String url, String tkl) {

        String itemId = "";
        
        Map<String, String> map = URLRequest(url);
        if (map.containsKey("id")) {
            return map.get("id");
        }
        if (url.indexOf("s.click") > 0) {
            TKURL tkurl = new TKURL();
            
            try {
                String jumpURL = tkurl.getRedirectUrl(url);
                
                String jiemiUrl2 = tkurl.unescape(jumpURL.substring(jumpURL.indexOf("tu=") + 3));
                
                String tbrul = "";
                tbrul = tkurl.getBaseUrl(jiemiUrl2, url);
                
                return parseItemId(tbrul, tkl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (url.indexOf("uland.taobao.com") > 0) {
            JSONObject object = getunuland(url);
            if (object != null) {
                return object.getString("itemId");
            }
        }else {
            itemId = url.substring(url.indexOf("/i") + 2, url.indexOf(".htm"));
        }

        return itemId;
    }

    public static String getHighObject(String tkl) {

        JSONObject object = Coupon.getTBKItemByToken(tkl);
        String itemId = parseItemId(object.getString("url"), tkl);
        String pid = "mm_47328993_112850356_23198400420";
        JSONObject highObject = getHighTBK(itemId, pid);
        
        if (highObject == null) {
            return "卖家没设置这款商品优惠券";
        }
        
        String title = object.getString("content");
        
        String url = highObject.getString("coupon_click_url");
        
        String image = object.getString("pic_url");
        
        String newtkl = getTKL(title, url, image);
        
        return newtkl;
    }

    public static String getReturnContent(String tkl) {

        String object = getHighObject(tkl);
        if (object != null) {
            // TODO 
        }
        return object;
    }

    public static void main(String[] args) {

        System.out.println(getHighObject("￥vIVobe9gcvv￥"));
        
//        getunuland("http://zmnxbc.com/s/cXpHV?tm=bc501b");
    }
    
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
 
        String[] arrSplit = null;
 
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
 
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
 
            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;

    }
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
 
        strURL = strURL.trim();
 
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
 
        return strAllParam;
    }
}
