package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.QueryQuotaResponse;
import com.baidubce.services.sms.model.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        // 查询配额
        QueryQuotaResponse quota = client.queryQuota(new SmsRequest());
        logger.info(quota.toString());
        logger.info("单日发送配额: " + quota.getMaxSendPerDay());
        logger.info("单日单终端接收配额: " + quota.getMaxReceivePerPhoneNumberDay());
        logger.info("当日已发送: " + quota.getSentToday());
    }
}
