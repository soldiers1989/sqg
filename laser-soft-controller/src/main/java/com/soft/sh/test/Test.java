package com.soft.sh.test;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @author junxian.zhao
 *
 */
public class Test {

    public static void main(String[] args) {

    }

    @SuppressWarnings("unchecked")
    public static Double test(String str) {

        Map<String, Object> maps = (Map<String, Object>) JSONArray.parse(str);
        String dataStr = maps.get("data").toString();
        System.out.println(dataStr);
        List<Map<String, Object>> list = (List<Map<String, Object>>) JSON.parse(dataStr);
        System.out.println(list.size());
        Double add = 0d;
        for (Map<String, Object> map : list) {
            boolean effectiveFlag = (Boolean) map.get("effectiveFlag");
            Object promoRewardAmount = map.get("promoRewardAmount");
            if (effectiveFlag) {
                Double a = Double.parseDouble(promoRewardAmount.toString()) + add;
                map.put("promoRewardAmount", a);
                add = a;
            }
        }
        System.out.println(JSONArray.toJSON(list));
        return null;
    }

}
