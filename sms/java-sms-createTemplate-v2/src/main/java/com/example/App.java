package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.CreateTemplateRequest;
import com.baidubce.services.sms.model.CreateTemplateResponse;

import java.util.logging.Logger;

/**
 * 创建短信模板（接口版本：v2）
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String endpoint = "sms.bj.baidubce.com";  // 如果是gz区域的BCC，要使用 sms.gz.baidubce.com

        SmsClientConfiguration config = new SmsClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        SmsClient client = new SmsClient(config);

        String invokeId = "";  // 签名调用ID
        String templateName = "";  // 短信模板的名称
        String templateContent = "";  // 短信模板的内容，可通过 ${参数名称} 来指定模板参数
        CreateTemplateRequest request = new CreateTemplateRequest()
                .withInvokeId(invokeId)
                .withName(templateName)
                .withContent(templateContent);

        CreateTemplateResponse response = client.createTemplate(request);
        logger.info(response.toString());
    }
}
