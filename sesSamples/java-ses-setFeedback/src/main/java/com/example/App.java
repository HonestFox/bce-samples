package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.SetFeedbackRequest;

/**
 * 设置退信通知的接收邮箱。
 *
 * 注意: 设置了退信通知的接收邮箱之后, 在"管理控制台"无法查看任何设置信息, 需要调用SDK中的getFeedback接口才能查看。
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

        SetFeedbackRequest request = new SetFeedbackRequest();
        request.setType(2);  // 退信通知
        request.setEmail("");  // 接收通知的邮箱地址, 比如: xyz@abc.com
        request.setEnabled(true);  // 使上面的邮箱地址接收(true)/不接收(false)退信通知
        client.setFeedback(request);
    }
}
