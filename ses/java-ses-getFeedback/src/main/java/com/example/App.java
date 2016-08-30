package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.GetFeedbackResponse;

/**
 * 获取通知方式。
 *
 * 通知类型:
 *  1 - 退信或投诉
 *  2 - 退信
 *  3 - 投诉
 */
public class App 
{
    public static void main( String[] args )
    {
        String AK = "";  // AccessKeyId
        String SK = "";  // SecretAccessKey

        SesClientConfiguration configuration = new SesClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SesClient client = new SesClient(configuration);

        GetFeedbackResponse response = client.getFeedback();
        System.out.println(response);
    }
}
