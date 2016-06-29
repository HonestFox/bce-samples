package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.GetTemplateDetailRequest;
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取单个短信模板的信息
 *
 * 所用接口:
 *      SmsClient
 *      GetTemplateDetailResponse getTemplateDetail(GetTemplateRequest);
 *
 * 查询结果:
 *      templateId -- 短信模板ID
 *      name -- 短信模板的名字
 *      content -- 短信模板的内容
 *      status -- VALID表示通过审核, INVALID表示未通过审核。
 *      createTime -- 短信模板的创建时间
 *      updateTime -- 短信模板的更新时间
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // 配置AK/SK, 并创建SmsClient。
        final String AK = "";
        final String SK = "";
        final String templateId = "";

        SmsClientConfiguration configuration = new SmsClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SmsClient client = new SmsClient(configuration);

        // 创建请求, 根据templateId获取短信模板信息。
        GetTemplateDetailRequest request = new GetTemplateDetailRequest();
        request.setTemplateId(templateId);
        GetTemplateDetailResponse template = client.getTemplateDetail(request);

        // 输出短信模板信息
        logger.info(template.toString());
        logger.info("templateId: " + template.getTemplateId());
        logger.info("name: " + template.getName());
        logger.info("content: " + template.getContent());
        logger.info("status: " + template.getStatus());
        logger.info("createTime: " + template.getCreateTime());
        logger.info("updateTime: " + template.getUpdateTime());
    }
}
