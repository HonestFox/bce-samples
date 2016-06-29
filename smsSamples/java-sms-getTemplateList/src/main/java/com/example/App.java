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
 * 获取短信模板列表
 *
 * 所用接口:
 *      SmsClient
 *      List<GetTemplateDetailResponse> getTemplateList();
 *
 * 查询结果:
 *      一个列表, 其中每个元素代表一个短信模板。
 *      每个短信模板中包含以下信息:
 *          templateId -- 模板ID
 *          name -- 模板名
 *          content -- 模板内容
 *          status -- 模板状态(VALID/INVALID)
 *          createTime -- 模板的创建时间
 *          updateTime -- 模板的更新时间
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
