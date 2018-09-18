package com.soft.wechat.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.model.TbkCoupon;
import com.soft.wechat.domain.CouponContext;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;

public class Coupon {

    public static final String tklparseURL = "http://api.chaozhi.hk/tb/tklParse";

    public static final String tklGenURL = "http://api.chaozhi.hk/tb/linkTkl";

    public static final String tbkHighURL = "http://open.jxb001.cn/openApi/high/api";

    public static final String unulandURL = "http://open.jxb001.cn/openApi/unuland/api";
    
    
    public static TbkItemInfoGetResponse getItemDetail(String itemId) {

        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "25073090", "4a0d538064e190a89ae4cfa10e5bf393");
        
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids(itemId);
        //req.setPlatform(1L);
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

      TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "25073090", "4a0d538064e190a89ae4cfa10e5bf393");
        
       TbkTpwdCreateRequest request = new TbkTpwdCreateRequest();
       request.setUrl(url);
       request.setLogo(image);
       request.setText(title);
        try {
            TbkTpwdCreateResponse response = client.execute(request);
            if (response != null) {
                return response.getData().getModel();
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("link", url);
            params.put("image", image);
            params.put("text", title);
            params.put("type", "B");
            JSONObject object = new JSONObject();
            String result = WebUtils.doPost(tklGenURL, params);
            if (StringUtils.isNotBlank(result)) {
                object = JSONObject.parseObject(result);
            }
            if (object != null) {
                object = object.getJSONObject("data");
                return object.getString("tkl");
            }
        } catch (Exception e) {

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

    public static TbkCoupon getHighObject(String tkl, String pid) {

        JSONObject object = Coupon.getTBKItemByToken(tkl);
        String itemId = parseItemId(object.getString("url"), tkl);
        
        TbkItemInfoGetResponse itemObject = getItemDetail(itemId);
        
        TbkCoupon tbkCoupon = new TbkCoupon();

        if (itemObject == null || !itemObject.isSuccess()) {
            //未设置优惠券
            tbkCoupon.setTkl("该商品暂不参与优惠活动◕╭╮◕试试其它商品，也许会有意外的惊喜哦！！~~");
            return tbkCoupon;
        }
        
        CouponContext context = new CouponContext();
        context.setItemId(itemId);
        context.setPid(pid);
        context.setObject(object);
        context.setTkl(tkl);
        context.setItemObject(itemObject);
        context.setTbkCoupon(tbkCoupon);
        context.setUserRate(null);
        
        getHaveCouponItem(context);
        
        return tbkCoupon;
    }

    public static void getHaveCouponItem(CouponContext context) {
        
        
        String tkl = context.getTkl();
        String pid = context.getPid();
        JSONObject object = context.getObject();
        String itemId = context.getItemId();
        TbkItemInfoGetResponse itemObject = context.getItemObject();
        TbkCoupon tbkCoupon = context.getTbkCoupon();
        
        object.put("itemId", itemId);
        object.put("pid", pid);
        JSONObject highObject = getHighTBK(itemId, pid);

        String title = itemObject.getResults().get(0).getTitle();

        String url = highObject.getString("coupon_click_url");

        String image = object.getString("pic_url");

        String newtkl = getTKL(title, url, image);
        
        String commissionRate = highObject.getString("max_commission_rate");
        
        String coupon_info = highObject.getString("coupon_info");
        
        BigDecimal rate = new BigDecimal(commissionRate);
        
        
        String content = title + "\n";
        content = content + "【原价】" + itemObject.getResults().get(0).getZkFinalPrice() + "元\n";
        String couponAmount = "0";
        BigDecimal itemPrice = new BigDecimal(itemObject.getResults().get(0).getZkFinalPrice());
        BigDecimal zkMoney = itemPrice;
        if (coupon_info != null) {
            couponAmount = coupon_info.substring(coupon_info.indexOf("减") + 1, coupon_info.lastIndexOf("元"));
            content = content + "【优惠券】" + couponAmount + "元\n";
            zkMoney = itemPrice.subtract(new BigDecimal(couponAmount));
            content = content + "【券后价】" + zkMoney + "元\n";
        }
        BigDecimal jljMoney = zkMoney.multiply(rate).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        
        BigDecimal userRate = context.getUserRate();
        
        if (userRate == null) {
            userRate = TbkConstants.DEFAULT_RATE;
        }
        
        BigDecimal userJljMoney  = jljMoney.multiply(userRate).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        
        content = content + "【预计成交奖励金】" + userJljMoney + "元\n";
        content = content + "--------------------------\n";
        content = content + "复制这条信息，"+newtkl+"，打开【手机淘宝】即可查看";

        if (highObject != null) {
            tbkCoupon.setPid(object.getString("pid"));
            tbkCoupon.setItemId(StringUtils.isBlank(itemId) ? null : Long.parseLong(itemId));
            tbkCoupon.setItemTitle(title);
            tbkCoupon.setItemImage(image);
            tbkCoupon.setInputTkl(tkl);
            tbkCoupon.setTkl(content);
            tbkCoupon.setItemPrice(new BigDecimal(itemObject.getResults().get(0).getZkFinalPrice())); 
            tbkCoupon.setMaxCommission(jljMoney);
            tbkCoupon.setCommission(userJljMoney); 
            tbkCoupon.setCouponAmout(new BigDecimal(couponAmount)); 
            
            if (tbkCoupon.getCouponAmout().compareTo(BigDecimal.ZERO) > 0) {
                tbkCoupon.setCouponExist("1");
            }else {
                tbkCoupon.setCouponExist("0");
            }
        }
    }
    
    
    
    public static void main(String[] args) {

        System.out.println(getHighObject("￥dHJvbVbORt8￥","mm_47328993_112850356_23198400420").getTkl());

        
      //TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "25073090", "4a0d538064e190a89ae4cfa10e5bf393");
     
/*     TbkItemGetRequest req = new TbkItemGetRequest();

     req.setQ("武夷山特级正山小种红茶散装正山小种红茶桂圆香正品新茶500g");
     req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
     
     TbkItemGetResponse rsp = null;
     
     TbkCouponGetRequest r = new TbkCouponGetRequest();
     try {
         rsp = client.execute(req);
         System.out.println(rsp);
     } catch (ApiException e) {

     }*/
    /* TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
     req.setAdzoneId(23198400420L);
     req.setPlatform(1L);
     req.setCat("16,18");
     req.setPageSize(1L);
     req.setQ("女装");
     req.setPageNo(1L);
     
     try {
         TbkDgItemCouponGetResponse rsp = client.execute(req);
         System.out.println(rsp.getBody());
         //req.setQ("正山小种红茶茶叶浓香型正宗武夷山桐木关2018春茶散装罐装礼袋装");
         req.setAdzoneId(23198400420L);
         
         rsp = client.execute(req);
         System.out.println(rsp);
     } catch (ApiException e) {

     }*/
     
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
