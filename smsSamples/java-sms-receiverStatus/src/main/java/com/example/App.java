package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.StatReceiverRequest;
import com.baidubce.services.sms.model.StatReceiverResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 查询单终端用户接收的短信信息
 *
 * 查询结果:
 *      maxReceivePerPhoneNumberDay -- 单终端每日接收短信的配额
 *      receivedToday -- 某个手机终端今日已经收到的短信数量
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

        // 查询单终端用户接收的短信信息
        StatReceiverRequest request = new StatReceiverRequest();
        request.setPhoneNumber("手机号码");  // 填写要查询的手机号码
        StatReceiverResponse response = client.statReceiver(request);

        // 输出该用户的每日接收配额, 以及今天已经接收的短信数量。
        logger.info(response.toString());
        logger.info("每日接收配额: " + response.getMaxReceivePerPhoneNumberDay());
        logger.info("今日已接收短信: " + response.getReceivedToday());
    }
}
