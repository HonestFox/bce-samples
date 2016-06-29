package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.CreateTemplateRequest;
import com.baidubce.services.sms.model.CreateTemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建短信模板
 *
 * 所用接口:
 *      SmsClient
 *      CreateTemplateResponse createTemplate(CreateTemplateRequest);
 *
 * 返回结果:
 *      templateId -- 短信模板ID
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

        // 填写模板名称和内容
        CreateTemplateRequest request = new CreateTemplateRequest();
        request.setName("模板名称");
        request.setContent("模板内容");

        // 创建模板
        CreateTemplateResponse response = client.createTemplate(request);
        logger.info(response.toString());
        logger.info("模板ID: " + response.getTemplateId());
    }
}
