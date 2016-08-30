package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;

/**
 * 验证"发信邮箱"。
 *
 * 调用成功后, SES会向指定的邮箱地址发送一封验证邮件, 需要手动点击邮件中的链接来完成验证。
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

        client.verifyEmail("发信邮箱的地址");
    }
}
