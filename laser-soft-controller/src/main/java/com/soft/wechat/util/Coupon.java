package com.soft.wechat.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.model.TbkCoupon;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.response.TbkItemInfoGetResponse;

public class Coupon {

    public static final String tklparseURL = "http://api.chaozhi.hk/tb/tklParse";

    public static final String tklGenURL = "http://api.chaozhi.hk/tb/linkTkl";

    public static final String tbkHighURL = "http://open.jxb001.cn/openApi/high/api";

    public static final String unulandURL = "http://open.jxb001.cn/openApi/unuland/api";
    
    
    public static TbkItemInfoGetResponse getItemDetail(String itemId) {

        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "25073090", "4a0d538064e190a89ae4cfa10e5bf393");
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids(itemId);
        req.setPlatform(1L);
        TbkItemInfoGetResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {

        }
        return rsp;
    }
    
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
        } else if (url.indexOf("uland.taobao.com") > 0) {
            JSONObject object = getunuland(url);
            if (object != null) {
                return object.getString("itemId");
            }
        } else {
            itemId = url.substring(url.indexOf("/i") + 2, url.indexOf(".htm"));
        }

        return itemId;
    }

    public static TbkCoupon getHighObject(String tkl) {

        
        JSONObject object = Coupon.getTBKItemByToken(tkl);
        String itemId = parseItemId(object.getString("url"), tkl);
        
        TbkItemInfoGetResponse itemObject = getItemDetail(itemId);
        
        String pid = "mm_47328993_112850356_23198400420";
        object.put("itemId", itemId);
        object.put("pid", pid);
        JSONObject highObject = getHighTBK(itemId, pid);

        TbkCoupon tbkCoupon = new TbkCoupon();

        if (highObject == null) {
            //未设置优惠券
            return null;
        }

        String title = object.getString("content");

        String url = highObject.getString("coupon_click_url");

        String image = object.getString("pic_url");

        String newtkl = getTKL(title, url, image);
        
        String commissionRate = highObject.getString("max_commission_rate");
        
        String coupon_info = highObject.getString("coupon_info");
        
        BigDecimal rate = new BigDecimal(commissionRate);
        
        BigDecimal jljMoney = new BigDecimal(itemObject.getResults().get(0).getZkFinalPrice()).multiply(rate).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        
        String content = title + "\n";
        content = content + "[原价]" + itemObject.getResults().get(0).getZkFinalPrice() + "元\n";
        String couponAmount = "0";
        if (coupon_info != null) {
            couponAmount = coupon_info.substring(coupon_info.indexOf("减") + 1, coupon_info.lastIndexOf("元"));
            content = content + "[优惠券]" + couponAmount + "元\n";
            content = content + "[券后价]" + new BigDecimal(itemObject.getResults().get(0).getZkFinalPrice()).subtract(new BigDecimal(couponAmount)) + "元\n";
        }
        content = content + "[预计成交奖励金]" + jljMoney + "元\n";
        content = content + "--------------------------\n";
        content = content + "复制这条信息，"+newtkl+"，打开【手机淘宝】即可查看";

        if (highObject != null) {
            tbkCoupon.setPid(object.getString("pid"));
            tbkCoupon.setItemId(StringUtils.isBlank(itemId) ? null : Long.parseLong(itemId));
            tbkCoupon.setItemTitle(title);
            tbkCoupon.setItemImage(image);
            tbkCoupon.setInputTkl(tkl);
            tbkCoupon.setTkl(content);
            tbkCoupon.setItemPrice(new BigDecimal(itemObject.getResults().get(0).getZkFinalPrice()));// 商品价格 TODO
            tbkCoupon.setCommission(jljMoney);// 预计佣金 TODO
            tbkCoupon.setCouponAmout(new BigDecimal(couponAmount));// 优惠券金额 TODO
            
            if (tbkCoupon.getCouponAmout().compareTo(BigDecimal.ZERO) > 0) {
                tbkCoupon.setCouponExist("1");
            }else {
                tbkCoupon.setCouponExist("0");
            }
        }
        
        return tbkCoupon;
    }
    
    
    
    public static void main(String[] args) {

        System.out.println(getHighObject("€o4xubedQU1k€").getTkl());

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
