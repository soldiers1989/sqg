package com.soft.tbk.service.channel.aliyunsms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Service
public class AliyunSmsService {

    Logger logger = LoggerFactory.getLogger(AliyunSmsService.class);

    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";

    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    @Value("tbk.sms.AccessKeyId")
    private String accessKeyId;

    @Value("tbk.sms.AccessKeySecret")
    private String accessKeySecret;

    @Value("tbk.sms.SignName")
    private String signName;

    @Value("tbk.sms.TempCode")
    private String tempCode;

    /**
     * 短信发送
     * 
     * @param phoneNo
     * @param content
     * @return
     * @throws ClientException
     */
    public SendSmsResponse sendSms(String phoneNo, String content) {

        SendSmsResponse sendSmsResponse = null;
        try {
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phoneNo);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(tempCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + content + "\"}");
            sendSmsResponse = acsClient.getAcsResponse(request);
            if (!"OK".equals(sendSmsResponse.getCode())) {
                logger.error("发送失败," + sendSmsResponse.getMessage());
            }
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return sendSmsResponse;
    }

    public static void main(String[] args) throws ClientException,InterruptedException {

        AliyunSmsService aliyunSmsService = new AliyunSmsService();
        //发短信
        SendSmsResponse response = aliyunSmsService.sendSms("18817902816", "21345");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

    }

}
