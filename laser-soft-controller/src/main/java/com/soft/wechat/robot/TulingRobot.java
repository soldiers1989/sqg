package com.soft.wechat.robot;

import static com.soft.wechat.api.WechatApi.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.druid.util.StringUtils;
import com.soft.wechat.handle.AbstractMessageHandler;
import com.soft.wechat.util.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 图灵机器人实现
 *
 * @author zjx
 */
public class TulingRobot extends AbstractMessageHandler {

    private String baseUrl = "http://www.tuling123.com/openapi/api";

    private String apiKey;

    public TulingRobot(String tenantCode) {
        this.apiKey = "";// TODO 
        if (StringUtils.isEmpty(apiKey)) {
            this.apiKey = "7bec0218910c4352b9a878f636da78b1";
        }
    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

    public String getResult(String question) {

        Map<String, Object> data = new HashMap<String, Object>(2);
        data.put("key", apiKey);
        data.put("info", question);

        RequestBody requestBody = RequestBody.create(JSON, Utils.toJson(data));
        Request request = new Request.Builder().url(baseUrl).post(requestBody).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            TulingRet tulingRet = Utils.fromJson(response.body().string(), TulingRet.class);
            if (tulingRet.code == 100000) {
                return tulingRet.text;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    class TulingRet {

        int code;

        String text;

    }

}
