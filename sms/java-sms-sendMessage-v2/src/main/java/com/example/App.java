package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV2Request;
import com.baidubce.services.sms.model.SendMessageV2Response;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 发送短信（接口版本：v2）
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String endpoint = "sms.bj.baidubce.com";  // 如果是gz区域的BCC，请使用 sms.gz.baidubce.com

        SmsClientConfiguration config = new SmsClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        SmsClient client = new SmsClient(config);

        String phoneNumber = "";  // 接收者的手机号码（每次只能发送一个号码）
        String invokeId = "";  // 签名调用ID
        String templateId = "smsTpl:e7476122a1c24e37b3b0de19d04ae900";  // 短信模板ID
        Map<String, String> contentVars = new HashMap<String, String>();  // 模板参数
        contentVars.put("code", "12345");

        SendMessageV2Request request = new SendMessageV2Request()
                .withPhoneNumber(phoneNumber)
                .withInvokeId(invokeId)
                .withTemplateCode(templateId)
                .withContentVar(contentVars);
        SendMessageV2Response response = client.sendMessage(request);
        logger.info(response.toString());
    }
}
