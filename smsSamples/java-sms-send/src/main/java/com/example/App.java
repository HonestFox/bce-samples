package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SendMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * 使用 SMS Java SDK 发送短信
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // 配置AK/SK, 创建SmsClient。
        final String AK = "";
        final String SK = "";

        SmsClientConfiguration configuration = new SmsClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SmsClient client = new SmsClient(configuration);

        // 配置短信模板, 模板中参数的值, 接收端手机号码。
        final String templateId = "smsTpl:e7476122a1c24e37b3b0de19d04ae900";  // 模板内容: 您的验证码是${code}
        final String contentVar = "{\"code\": \"12345\"}";  // 配置模板参数的值, JSON格式。
        final List<String> phoneNumbers = Arrays.asList("手机号码1", "手机号码2");  // 支持多个接收端手机号码

        // 创建"发送短信"的请求
        SendMessageRequest request = new SendMessageRequest();
        request.setTemplateId(templateId);
        request.setContentVar(contentVar);
        request.setReceiver(phoneNumbers);

        // 发送短信
        SendMessageResponse response = client.sendMessage(request);
        logger.info(response.getMessageId());
    }
}
