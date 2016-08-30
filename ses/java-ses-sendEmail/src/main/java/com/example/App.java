package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.SendEmailResponse;

/**
 * 发送邮件。
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

        String fromAddress = "";  // 发信邮箱
        String[] toAddresses = {"收信邮箱1", "收信邮箱2"};  // 收信邮箱
        String subject = "";  // 邮件标题
        String body = "";  // 邮件正文
        SendEmailResponse response = client.sendEmail(fromAddress, toAddresses, subject, body);
        System.out.println(String.format("邮件流水号: %s", response.getMessageId()));
    }
}
