package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import com.baidubce.services.sms.model.ListTemplateResponse;
import com.baidubce.services.sms.model.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // 配置AK/SK, 并创建SmsClient。
        final String AK = "";
        final String SK = "";

        SmsClientConfiguration configuration = new SmsClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SmsClient client = new SmsClient(configuration);

        // 获得短信模板
        ListTemplateResponse response = client.listTemplate(new SmsRequest());
        List<GetTemplateDetailResponse> templates = response.getTemplateList();

        // 输出每一个短信模板的信息
        if (templates.isEmpty()) {
            logger.error("没有短信模板");
        } else {
            for (GetTemplateDetailResponse template : templates) {
                logger.info(template.toString());
                logger.info("templateId: " + template.getTemplateId());
                logger.info("name: " + template.getName());
                logger.info("content: " + template.getContent());
                logger.info("status: " + template.getStatus());
                logger.info("createTime: " + template.getCreateTime());
                logger.info("updateTime: " + template.getUpdateTime());
            }
        }
    }
}
