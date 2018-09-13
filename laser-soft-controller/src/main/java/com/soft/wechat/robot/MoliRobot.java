package com.soft.wechat.robot;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.soft.wechat.handle.AbstractMessageHandler;
import com.soft.wechat.model.Environment;
import com.soft.wechat.util.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 茉莉机器人实现
 * 
 * @author zjx
 */
public class MoliRobot extends AbstractMessageHandler {

    private String baseUrl = "http://i.itpk.cn/api.php";

    public MoliRobot(Environment environment) {
        String apiKey = environment.get("moli.api_key");
        String apiSecret = environment.get("moli.api_secret");
        if (Utils.isNotBlank(apiKey) && Utils.isNotBlank(apiSecret)) {
            baseUrl += "?api_key=" + apiKey + "&api_secret=" + apiSecret + "&";
        } else {
            baseUrl += "?";
        }
    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

    public String getResult(String question) {

        String url = baseUrl + "question=" + question;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
