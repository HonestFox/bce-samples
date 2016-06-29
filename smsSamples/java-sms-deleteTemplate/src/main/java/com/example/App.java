package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.DeleteTemplateRequest;

/**
 * 删除短信模板
 * <p>
 * 所用接口:
 * SmsClient
 * void deleteTemplate(DeleteTemplateRequest);
 */
public class App {
    
    public static void main(String[] args) {
        // 配置AK/SK, 创建SmsClient。
        final String AK = "";
        final String SK = "";

        SmsClientConfiguration configuration = new SmsClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SmsClient client = new SmsClient(configuration);

        // 删除短信模板
        DeleteTemplateRequest request = new DeleteTemplateRequest();
        request.setTemplateId("短信模板ID");
        client.deleteTemplate(request);
    }
}
